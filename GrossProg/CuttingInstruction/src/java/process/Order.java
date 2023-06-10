package process;



import cutting.utility.Truncation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {

    private final Customer customer;
    private final List<Map.Entry<Pipe, Integer>> items;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public Order(Order other) {
        this.customer = other.getCustomer();
        this.items = new ArrayList<>();
        items.addAll(other.getItems());
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public List<Map.Entry<Pipe, Integer>> getItems() {
        return this.items;
    }

    public void addItem(Pipe pipe, int amount) {
        items.add(Map.entry(pipe, amount));
    }

    @Override
    public String toString() {

        StringBuilder stb = new StringBuilder();
        stb.append(customer.toString()).append(System.lineSeparator());
        if (items.isEmpty()) {
            stb.append(System.lineSeparator()).append("No Items ordered!");
        } else {
            AtomicInteger count = new AtomicInteger(0);
            items.forEach(item -> {
                if (count.get() != 0 && count.getAndIncrement() % 10 == 0) {
                    stb.append(System.lineSeparator());
                }
                stb.append(item.getValue()).append("*").append( Truncation.form(
                        "",false, item.getKey().getLength())).append("; ");
                if (count.incrementAndGet() % 10 == 0) {
                    stb.delete(stb.length() - 2, stb.length()); // remove "; " at the end of line
                    stb.append(System.lineSeparator());
                }
            });

            stb.delete(stb.length() - 2, stb.length()); // remove "; " at the end of the item list.
            stb.append(System.lineSeparator());
        }
        return stb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || (this.getClass() != other.getClass())) {
            return false;
        } else {
            Order that = (Order) other;
            boolean equal = this.customer.equals(that.getCustomer());

            equal &= this.items.size() == that.getItems().size();
            List<Pipe> thatItems = that.sortedOrders();
            List<Pipe> thisItems = that.sortedOrders();
            for (int i = 0; (i < this.items.size() && equal); i++) {
                equal = thisItems.get(i) == thatItems.get(i);
            }
            return equal;
        }
    }

    public List<Pipe> sortedOrders() {
        List<Pipe> orderedPipes = new ArrayList<>();
        items.forEach(e -> {
            for (int i = 0; i < e.getValue(); i++) {
                orderedPipes.add(e.getKey());
            }
        });
//        orderedPipes.sort(Comparator.comparingDouble(e-> e.getLength()));
        orderedPipes.sort(Comparator.comparingDouble(Pipe::getLength).reversed());
        return orderedPipes;
    }


}
