package process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;


class CuttingTest {

    Cutting cutting;

    @BeforeEach
    void setUp() { //TODO add cutout to the stack from pipes in the below orders like original code.
        Order order = new Order(new Customer("Ghazanfar", "Ali"));
        order.addItem(new Pipe(1.5),3);
        order.addItem(new Pipe(1.0), 3);
        CompoundPipe stock_1 = new CompoundPipe(5);
        CompoundPipe stock_2 = new CompoundPipe(3);
        stock_1.addCutout(1.5);
        stock_1.addCutout(1.5);
        stock_1.addCutout(1.5);
        stock_2.addCutout(1);
        stock_2.addCutout(1);
        stock_2.addCutout(1);
//        List<CompoundPipe> list = Arrays.asList(stock_1,stock_2);
//        cutting = new Cutting(order, list);
        cutting = new Cutting(order, Arrays.asList(stock_1,stock_2));
//        String test = cutting.toString();
    }

    @Test
    void testToString() {
        System.out.println(cutting.toString());
        String expected = """
                Ali, Ghazanfar
                3*1,50; 3*1,00

                TotalOffcut: 0,50
                Number of Cuttings: 5,00
                5,00 -> 1,50; 1,50; 1,50 Offcut: 0,50
                3,00 -> 1,00; 1,00; 1,00 Offcut: 0,00""";
        assertThat(cutting.toString()).isEqualToNormalizingNewlines(expected);

    }


    @Test
    void sumOffcuts() {
        assertEquals(0.5, cutting.sumOffcuts());
    }

    @Test
    void sumCuttingCount() {
    }
}