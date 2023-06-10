package cutting;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * The Job class stores the input data from the {@link FileSupplier}.
 * It holds the length of the pipes and amount of them.
 */
public class Job {
    private final String filepath;
    private  final String orderer;
    private final Map<Float, Integer> pipes;
    /**
     * <p>
     * Construct a default instance replacing all attributes by null.
     */
    public Job() {
        this(null,null,null);
    }

    /**
     * Constructs a segment object tht stores all necessary data to processing.
     * @param filepath path of the file that holds segment data.
     * @param pipes a map relation between the length of the pipes
     * and its pipe
     */
    public Job(String filepath, String orderer, Map<Float, Integer> pipes) {
        this.filepath = filepath;
        this.orderer = orderer;
        this.pipes = new HashMap<>();
        this.pipes.putAll(pipes);
    }

    /**
     *
     * @return path to the file which provides information about pipes {@link #pipes}.
     */
    public String getFilepath() {
        return this.filepath;
    }

    /**
     *
     * @return the length of the pipes and amount of them
     * which are mapped to each other as key value.
     */
    public Map<Float, Integer> getPipes() {
        return this.pipes;
    }

    /**
     *
     * @return the first- and last name of the orderer.
     */
    public String getOrderingName() {
        return this.orderer;
    }


}
