package process;


import cutting.utility.MyMath;
import cutting.utility.Truncation;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//public class Cutting extends CompoundPipe{
public class Cutting {

    protected Order order;
    private final List<CompoundPipe> cutouts = new ArrayList<>();

    public Cutting() {
        this(new Order(new Customer()), new ArrayList<>());
    }

    public Cutting(Order that, List<CompoundPipe> others) {
//        super(.1);
        this.order = new Order(that);
///        this.cutouts = new ArrayList<>();
        ///            p.sortCuttings();
        ///            this.cutouts.add(new CompoundPipe(p));
        //            this.cutouts.add(p);
        others.forEach(this::addCutout);
        sortPipes(cutouts);
    }

    private void sortPipes(List<CompoundPipe> pipes) {
        pipes.sort(Comparator.comparingDouble(CompoundPipe::getRemaining).reversed().thenComparing(
                Comparator.comparingDouble(CompoundPipe::cuttingCount).reversed()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(order.toString());
        sb.append(System.lineSeparator());
        sb.append(Truncation.form("TotalOffcut: ",true,sumOffcuts()));
        sb.append(Truncation.form("Number of Cuttings: ",true, sumCuttingCount()));

//        sb.delete(sb.length() -  System.lineSeparator().length(), sb.length());
        if (!cutouts.isEmpty()) {
            cutouts.forEach(c -> sb.append(c.toString()).append(
                    System.lineSeparator()));
            sb.delete(sb.length()- 2, sb.length());
        }

        return sb.toString();
    }

    private void addCutout( CompoundPipe pipe) { // TODO change the module of the CompoundPipe as inherited CompoundPipe(Stocke) and Base CompoundPipe as representation of Items
        if (pipe.getRemaining() == pipe.getLength()) {
///            List<Pipe> items =  order.sortedOrders();

///            Order newOrder = new Order(order);

//            newOrder.sortedOrders();
            order.getItems().forEach(e -> {
                for (int i = 0; i < e.getValue(); i++) {
                    pipe.addCutout(new Pipe(e.getKey()));
                }
            });
        }
        pipe.sortCuttings();
        cutouts.add(pipe);
//        pipe.cuttings.forEach(this::addCutout);
    }


    public double sumOffcuts() {
///        double sum = 0.0;
//        cutouts.forEach(c -> sum += c.getRemaining());
        double sum = cutouts.stream().mapToDouble(CompoundPipe::getRemaining).sum();
        return MyMath.round(sum);
    }

    public int sumCuttingCount() {

        return cutouts.stream().mapToInt(CompoundPipe::cuttingCount).sum();
    }

    /**
     *
     * @param that represents other cutting process which is better than current one.
     * @return true if @that cutting was preformed better according some greedy algorithm.
     */
    public boolean isBetterCutting(@Nullable Cutting that) {

        if (that == null || cutouts.isEmpty()) {
            return true;
        } else if (!this.order.equals(that.order)) {
            return false;
        }

        boolean lessCutting = ( that.sumCuttingCount() < this.sumCuttingCount());
        boolean sameOffcut =  MyMath.almostEqual(this.sumOffcuts(), that.sumOffcuts(), 0.001);

        boolean lessOffcuts = (this.sumOffcuts() > that.sumOffcuts());
        return (lessCutting && sameOffcut || lessOffcuts);
    }

    /**
     *
     * @param that represents other cutting process which is better than current one.
     * @return true if @that cutting was preformed better according some greedy algorithm.
     */
    public boolean isPotentiallyBetterCutting(@Nullable Cutting that, double rest) {
        if (that == null || cutouts.isEmpty()) {
            return true;
        } else if (rest > that.sumOffcuts()) {
            return true;
        }
        if (!this.order.equals(that.order)) {
            return false;
        }
        boolean lessCutting = (that.sumCuttingCount() < this.sumCuttingCount());

        boolean sameOffcut =  MyMath.almostEqual(this.sumOffcuts(), that.sumOffcuts(), 0.01);
        sameOffcut &=  MyMath.isBounded(rest, 0.000001);

        boolean lessOffcuts = (this.sumOffcuts() > that.sumOffcuts() - rest);
        return (lessCutting && sameOffcut || lessOffcuts);
    }







//    public boolean currentlyOptimized(List<CompoundPipe> current) {
//        if (current == null) {
//            return true;
//        } else {
//            double offcut =
//        }
//    }


}
