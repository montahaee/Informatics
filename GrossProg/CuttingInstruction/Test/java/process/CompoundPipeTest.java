package process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CompoundPipeTest {
    CompoundPipe compoundPipe = new CompoundPipe(1);
    @BeforeEach
    void setUp() {
        compoundPipe = new CompoundPipe(5);
    }

    @Test
    void getLength() {
        assertEquals(5.0, compoundPipe.getLength());
    }

    @Test
    void addCutoutByLength() {
        compoundPipe.cuttings.clear();
        compoundPipe.setRemaining(compoundPipe.getLength());
        compoundPipe.addCutout(4);
        List<Pipe> cutouts = new ArrayList<>();
        cutouts.add(new Pipe(4));
        assertThat(compoundPipe.getCuttings().size()).isEqualTo(cutouts.size());
        for (int i = 0 ; i < compoundPipe.getCuttings().size(); i++) {
            assertThat(compoundPipe.getCuttings().get(i).getLength()).isEqualTo( cutouts.get(i).getLength());
        }
//        compoundPipe.getCuttings().forEach(cp -> assertThat(cp).isEqualTo());
        assertEquals(1.0, compoundPipe.getRemaining());
        double currCutout =  compoundPipe.getCuttings().get(compoundPipe.getCuttings().size() - 1).getLength();
        assertEquals(4.0, currCutout);
    }

    @Test
    void getCuttings() {
        addCutoutByLength();
        assertEquals(compoundPipe.cuttings, compoundPipe.getCuttings());
    }

    @Test
    void getRemaining() {
        addCutoutByLength();
        assertEquals(1.0, compoundPipe.getRemaining());
    }


    @Test
    void testAddCutout() {
        compoundPipe.cuttings.clear();
        compoundPipe.setRemaining(compoundPipe.getLength());
        compoundPipe.addCutout(new Pipe(4));
        compoundPipe.addCutout(new Pipe(2));
        List<Pipe> cutouts = new ArrayList<>();
        cutouts.add(new Pipe(4.0));
        cutouts.add(new Pipe(2.0));
        assertEquals(1.0, compoundPipe.getRemaining());
        assertEquals(1.0, compoundPipe.Offcut());
        compoundPipe.addCutout(new CompoundPipe(1.0));
        compoundPipe.sortCuttings();
        cutouts.remove(cutouts.size() - 1);
        cutouts.add(new Pipe(1.0));
        assertEquals(cutouts.size(), compoundPipe.getCuttings().size());
        for (int i = 0; i < cutouts.size(); i++) {
            assertEquals(cutouts.get(i).getLength(), compoundPipe.getCuttings().get(i).getLength());
        }
        assertEquals(0.0, compoundPipe.getRemaining());
        assertEquals(0.0, compoundPipe.Offcut());
    }



    @Test
    void testToString() {
        testAddCutout();

        String expected = "5,00 -> 4,00; 1,00 Offcut: 0,00";
        assertEquals(expected, compoundPipe.toString());
    }

    @Test
    void cuttingCount() {
        testAddCutout();
        assertEquals(1, compoundPipe.cuttingCount());
        addCutoutByLength();
        assertEquals(1, compoundPipe.cuttingCount());
        compoundPipe.addCutout(0.75);
        assertEquals(0.25, compoundPipe.getRemaining());
        assertEquals(0.25, compoundPipe.Offcut());
        assertEquals(compoundPipe.getRemaining(), compoundPipe.Offcut());
        assertEquals(2, compoundPipe.cuttingCount());



    }

    @Test
    void offcut() {
        assertEquals(compoundPipe.Offcut(), compoundPipe.getRemaining());
    }
}