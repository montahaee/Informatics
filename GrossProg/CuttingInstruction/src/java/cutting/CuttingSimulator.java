package cutting;

import framework.Handleable;

import cutting.utility.Heap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * CuttingSimulator is an object that is used to transform input data in the form of
 * available size of the pipes, which are integer, and partly listed lengths to cutting
 * as long as the sum of the lengths less than or equals to this size. Those represent
 * altogether at least waste after necessary cuttings of the pipes to complete instruction.
 * A cutting simulator can be run as a thread and will obtain new {@link CutJob} from
 * the {@link DataStream}, transform the data and make the data available for the consumer in
 * a ConcurrentLinkedQueue.
 *
 */
public class CuttingSimulator implements Handleable<CutJob,CuttingSimulation>, Runnable {

    private final DataStream<CutJob> jobQueue;
    private final LinkedBlockingQueue<Data<CutJob,CuttingSimulation>> handleQueue;

    /**
     * Constructs CuttingSimulator with DataStream and ConcurrentLinkedQueue.
     * @param jobQueue is a DataStream {@link #<DataStream>} that provides
     *                data to be transformed.
     * @param handleQueue a Queue that passes transformed data to consumer.
     */
    public CuttingSimulator(DataStream<CutJob> jobQueue, LinkedBlockingQueue<Data<CutJob, CuttingSimulation>> handleQueue) {
        this.jobQueue = jobQueue;
        this.handleQueue = handleQueue;
    }

    /**
     * Starts some methods using the Heap Properties {@link Heap} which
     * handle the input data.
     * <ul>
     * Takes an issue performs the transformation in form of  and returns the solution.
     * <p>
     * @param cutJob the specified issues that has to be solved.
     * @return the result object
     */
    @Override
    public CuttingSimulation handle(CutJob cutJob) {
        int cuttingCount;
        List<Map.Entry<Integer,float[]>> pieces;
        List<Float> offcuts;


        return null;
    }

    /**
     *
     */
    public class Tree implements Comparable<Tree> {

        /** The root of the tree */
        Node root;

        /**
         * Create a tree containing a leaf node
         * @param weight of the subtree rooted at this node.
         * @param element is the size of the pipe for a lef node.
         */
        public Tree(double weight, double element) {
            this.root = new Node(weight, element);
        }


        /**
         * Compares this Tree with the specified Tree for order.
         *
         * @return a negative integer, zero, or a positive integer as this Tree
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified Tree is null
         * @throws ClassCastException   if the specified Tree's type prevents it
         *                              from being compared to this Tree.
         */
        @Override
        public int compareTo(Tree t) {
//            if (root)
            return 0;
        }

        public class Node {
            double element; // Stores the size of the pipe for a lef node.
            double weight; // Weight of the subtree rooted at this node.
            Node left; // Reference to the left subtree.
            Node right; // Reference to the right subtree.
            double offcut; // Store the offcut of this node.

            /**
             * Create the empty node as default.
             */
            public Node() {

            }

            /**
             * Create a node with the specified weight and pipe's size.
             * @param weight of the subtree rooted at <u>other</u> node.
             * @param element is a pipe with specified size.
             */
            public Node(double weight, double element) {
                this.weight = weight;
                this.element = element;
            }

        }
    }

    /**
     * Start
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general process of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

    }
}
