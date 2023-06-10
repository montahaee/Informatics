package cutting;

import java.util.List;
import java.util.Map;

/**
 * CuttingSimulation is a class that holds the  number of cuttings and
 * the list of any pipes which was optimally cut to the pieces so that
 * their total waste/offcut is minimized
 */
public class CuttingSimulation {

    private final int cuttingCount;
    private final List<Map.Entry<Integer,float[]>> pieces;
    private final List<Double> offcuts;

    /**
     * Constructs a CuttingSimulation with optimized pipes to spice.
     * As well the total number of cuttings and their offcuts.
     * @param pieces refer to the pipes which are optimally cut so that the total offcut is minimized.
     * @param cuttingWastes refer to the all offcuts whose sum is minimized.
     * @param cuttingCount refers to the necessary times for cutting the pipes
     */
    public CuttingSimulation(List<Map.Entry<Integer, float[]>> pieces, List<Double> cuttingWastes, int cuttingCount) {
        this.pieces = pieces;
        this.offcuts = cuttingWastes;
        this.cuttingCount = cuttingCount;
    }

    /**
     *
     * @return the size of pipes and corresponding pieces as the sum offcuts is minimized.
     */
    public List<Map.Entry<Integer, float[]>> getPieces() {
        return this.pieces;
    }

    /**
     *
     * @return offcuts whose sum is the minimal.
     */
    public List<Double> getOffcuts() {
        return this.offcuts;
    }

    /**
     *
     * @return the number of cutting to make the pieces.
     */
    public int getCuttingCount() {
        return this.cuttingCount;
    }
}
