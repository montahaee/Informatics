package optimizer;

import cutting.CutJob;
import cutting.DataStream;
import cutting.FileOrder;
import framework.exceptions.FileAccessException;
import framework.exceptions.IncorrectDataFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import process.Cutting;
import process.Order;
import process.Storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class OffCutCalculatorTest {


    OffCutCalculator calculator;
    @BeforeEach
    void setUp() {
         calculator = new OffCutCalculator(new DataStream<>(),
                new LinkedBlockingQueue<>(),new Storage());

    }

    @Test
    void handle() {
        Path source = Paths.get("Test/resource/Beispiel_1.txt");
        try {
            FileOrder forder = new FileOrder(source.toString(), new DataStream<>());
            Order order = forder.read().getOrder();
            CutJob  job = forder.read();
            Cutting cutting = calculator.handle(job);
            System.out.println(cutting.toString());
        }  catch (FileAccessException | IncorrectDataFormatException e) {
            System.err.println(e.getMessage());
        }

    }
}