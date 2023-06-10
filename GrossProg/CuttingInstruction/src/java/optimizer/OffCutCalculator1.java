package optimizer;


import cutting.CutJob;
import cutting.DataStream;
import cutting.utility.Heap;
import cutting.utility.MyMath;
import framework.Handleable;
import process.*;

import java.awt.geom.CubicCurve2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;


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
public class OffCutCalculator1 implements Handleable<CutJob, Cutting>, Runnable {


    private final DataStream<CutJob> jobQueue;
    private final LinkedBlockingQueue<Data<CutJob,Cutting>> handleQueue;
    private static final Logger LOG = Logger.getLogger(OffCutCalculator.class.getName());
    private final Storage storage;

    /**
     * Constructs CuttingSimulator with DataStream and ConcurrentLinkedQueue.
     * @param jobQueue is a DataStream {@link #<DataStream>} that provides
     *                data to be transformed.
     * @param handleQueue a Queue that passes transformed data to consumer.
     */
    public OffCutCalculator1(DataStream<CutJob> jobQueue, LinkedBlockingQueue<Data<CutJob, Cutting>> handleQueue, Storage storage) {
        this.jobQueue = jobQueue;
        this.handleQueue = handleQueue;
        this.storage = storage;
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
    public Cutting handle(CutJob cutJob) {
        long start = System.currentTimeMillis();
        LOG.info("Starting the optimized cutting process");
        Order order = cutJob.getOrder();
        List<Pipe> orderedItems = order.sortedOrders();
        Cutting[] currentlyOptimized = initializeCuttingList();
        List<CompoundPipe> cutouts = new ArrayList<>();
        calculateOffcut(order,orderedItems,currentlyOptimized,cutouts,-1);
        long duration = System.currentTimeMillis() - start;
        String newline = System.lineSeparator();
        LOG.info(String.format("For the order %s%s%sThe optimization of the processing process took %dms!%sOptimal solution:%s%s",
                newline, order, newline, duration, newline, newline, currentlyOptimized[0]));
//TODO update cutouts hier using private methods, which you have to write in this class according to MinVerschnittRechner

        return currentlyOptimized[0];
    }

    private void calculateOffcut(Order order, List<Pipe> onOrder, Cutting[] currentlyOptimized, List<CompoundPipe> proceeds, double rest) {
        if (onOrder.isEmpty()) {
            // the tree reaches its lif.
            Cutting process = new Cutting(order,proceeds);
            if (currentlyOptimized[0].isBetterCutting(process)) {
                currentlyOptimized[0] = process;
                return;
            }
            if (currentlyOptimized[0].isPotentiallyBetterCutting(process, rest)) {
                searchOnPossibleBranch(order,onOrder,currentlyOptimized,proceeds,rest);
            }
        }
    }

    private void searchOnPossibleBranch(Order order, List<Pipe> onOrder, Cutting[] currentlyOptimized, List<CompoundPipe> proceeds, double rest ) {
        onOrder.forEach(pipe -> {
            if (rest > pipe.getLength() || MyMath.almostEqual(rest, pipe.getLength())) {
                calculateOffcut(order, excludCurrentPipeFromOrderList(onOrder, pipe),currentlyOptimized,addCurrentPipeToCurrentCompoundPipe(proceeds, pipe), pipe.getLength() - rest);
            }
            storage.getStocklist().forEach(stock -> {
                if (stock.getLength() > pipe.getLength() ||
                        MyMath.almostEqual(stock.getLength(), pipe.getLength())) {
                    calculateOffcut(order, excludCurrentPipeFromOrderList(onOrder, pipe), currentlyOptimized,addCurrentPipeToNewCompoundPipe(proceeds,pipe,stock) ,stock.getLength() - pipe.getLength());
                }
            });
        });
    }


    private List<Pipe> excludCurrentPipeFromOrderList(List<Pipe> pipes, Pipe current) {
        List<Pipe> onOrder = new ArrayList<>(pipes);
        onOrder.removeIf(current::equals);
        return onOrder;
    }

    private List<CompoundPipe> addCurrentPipeToNewCompoundPipe(List<CompoundPipe> current, Pipe currentPipe, CompoundPipe stockPipe) {
        List<CompoundPipe> nextCurrent = current == null ? new ArrayList<>() : deepCopy(current);
        CompoundPipe newCut = new CompoundPipe(stockPipe);
        newCut.addCutout(new Pipe(currentPipe));
        nextCurrent.add(newCut);
        return nextCurrent;
    }

    // Returns a list of CompoundPipe objects with the current Pipe object added to the last CompoundPipe object in the list
    private List<CompoundPipe> addCurrentPipeToCurrentCompoundPipe(List<CompoundPipe> current, Pipe currentPipe) {
        // Creates a deep copy of the current list to avoid modifying the original list
        List<CompoundPipe> nextCurrent = deepCopy(current);
        // Gets the last CompoundPipe object in the list and adds the current Pipe object to its cut list
        nextCurrent.get(nextCurrent.size() - 1).addCutout(new Pipe(currentPipe));
        // Returns the modified list
        return nextCurrent;
    }

    private Cutting[] initializeCuttingList() {
        Cutting[] currentlyOptimized = new Cutting[1];
        currentlyOptimized[0] = null;
        return currentlyOptimized;
    }

    private List<CompoundPipe> deepCopy(List<CompoundPipe> current) {
        List<CompoundPipe> copy = new ArrayList<>(current.size());
        current.forEach(cp -> copy.add(new CompoundPipe(cp)));
        return copy;
    }

    private double sumOffcuts(List<CompoundPipe> compoundPipes) {
        return compoundPipes.stream().mapToDouble(CompoundPipe::getRemaining).sum();
    }


    /**
     * Starts the thread and gets data from the DataStream provided
     * by the Producer, transforms it and wraps the original and
     * transformed data in {@link Handleable.Data}
     * and adds it to the ConcurrentLinkedQueue for the consumer.
     * Finishes when Messdaten without filepath are received.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        Data<CutJob, Cutting> data;
        HashSet<String> processedData = new HashSet<>();
        while (true) {
            CutJob job = this.jobQueue.take();
            if (job.getFilepath() == null) {
                this.handleQueue.add(new Data<>());
                break;
            } else if (processedData.contains(job.getFilepath())) {
                continue;
            }
            processedData.add(job.getFilepath());
            Cutting result = handle(job);

            data = new Data<>();
            data.in = job;
            data.out = result;
            this.handleQueue.add(data);
        }
    }

}

