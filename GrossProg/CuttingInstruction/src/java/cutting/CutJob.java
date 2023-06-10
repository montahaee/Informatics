package cutting;


import process.Customer;
import process.Order;

/**
 * <p>
 * The CutJob class stores the input data from the {@link FileOrder}.
 * It holds the length of the pipes and amount of them.
 */
public class CutJob {

    private final String filepath;
    private final Order order;


    /**
     * <p>
     * Construct a default instance replacing all attributes by empty.
     */
    public CutJob() {
        this(null,new Order(new Customer()));
    }

    /**
     * Constructs a segment object tht stores all necessary data to processing.
     * @param filepath path of the file that holds segment data.
     * @param order represent all information about a specific order.
     * @see Order
     */
    public CutJob(String filepath, Order order) {
        this.filepath = filepath;
        this.order = order;
    }

    /**
     *
     * @return path to the file which provides information about pipes {@link #order}.
     */
    public String getFilepath() {
        return this.filepath;
    }

    /**
     *
     * @return the current order to cutout.
     */
    public Order getOrder() {
        return this.order;
    }
}
