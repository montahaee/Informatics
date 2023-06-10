package process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



class OrderTest {
    Order order;
    @BeforeEach
    void setUp() {
        order = new Order(new Customer("Ghazanfar", "Ali"));
        order.addItem(new Pipe(1.5),3);
        order.addItem(new Pipe(1.0), 2);
        order.addItem(new Pipe(1.201), 5);
    }

    @Test
    void getCustomer() {
        assertEquals("Ghazanfar", order.getCustomer().getFirstname());
        assertEquals("Ali", order.getCustomer().getLastname());
        assertEquals(new Customer("Ghazanfar", "Ali"), order.getCustomer());
    }

    @Test
    void getOrders() {
        List<Map.Entry<Pipe, Integer>> expected = new ArrayList<>();
        expected.add(Map.entry(new Pipe(1.5),3));
        expected.add(Map.entry(new Pipe(1.0),2));
        expected.add(Map.entry(new Pipe(1.201),5));

//        order.getItems().forEach(e-> assertEquals(e.getKey().getLength(), expected.get(curr++)));
        assertThat(order.getItems().size()).isEqualTo(expected.size());
        for (int i = 0; i < expected.size(); i++) {
            assertThat(order.getItems().get(i).getKey().getLength()).isEqualTo(
                    expected.get(i).getKey().getLength());
///            assertThat(order.getItems().get(i).getKey().getLength()).isEqualTo(
//                    expected.get(i).getKey().getRemaining());
///            assertThat(order.getItems().get(i).getKey().getCuttings()).isEqualTo(
//                    expected.get(i).getKey().getCuttings());

            assertThat(order.getItems().get(i).getValue()).isEqualTo(expected.get(i).getValue());
        }
//        assertEquals(expected, order.getItems());
    }

    @Test
    void testToString() {
        String expected = """
                Ali, Ghazanfar
                3*1,50; 2*1,00; 5*1,20
                """;
        assertThat(order.toString()).isEqualToNormalizingNewlines(expected);
    }
}