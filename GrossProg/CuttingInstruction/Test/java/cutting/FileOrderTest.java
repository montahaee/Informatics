package cutting;

import framework.exceptions.FileAccessException;
import framework.exceptions.IncorrectDataFormatException;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import process.Customer;
import process.Order;
import process.Pipe;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileOrderTest {

    private FileOrder forder;
    @BeforeEach
    void setUp() {
        Path source = Paths.get("Test/resource");
        try {
            forder = new FileOrder(source.toAbsolutePath().toString(),new DataStream<>());

        } catch (FileAccessException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void read() {
        Path newSource = Paths.get("Test/resource/Beispiel_1.txt");
        List<Map.Entry<Pipe, Integer>> expected = new ArrayList<>();
        expected.add(Map.entry(new Pipe(1.50), 2));
        expected.add(Map.entry(new Pipe(0.50), 2));
//        System.out.println(newSource.toAbsolutePath().toString());
        try {
            forder = new FileOrder(newSource.toString(), new DataStream<>());
            Order neworder = forder.read().getOrder();
            assertEquals(new Customer("Hans", "Hauptmann"), neworder.getCustomer());
//            System.out.println(forder.read().getOrder().toString());
            assertThat(neworder.getItems().size()).isEqualTo(expected.size());
            for (int i = 0; i < expected.size(); i++) {
                assertThat(neworder.getItems().get(i).getKey().getLength()).isEqualTo(
                        expected.get(i).getKey().getLength());
                assertThat(neworder.getItems().get(i).getValue()).isEqualTo(
                        expected.get(i).getValue());
            }
        } catch (FileAccessException | IncorrectDataFormatException e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    void getSourcePath() {
        Path resource = Paths.get("Test/resource");
        assertEquals(resource.toAbsolutePath(), forder.getSourcePath());
    }
}