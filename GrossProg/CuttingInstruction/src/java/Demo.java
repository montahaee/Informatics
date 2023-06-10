import cutting.CutJob;
import cutting.DataStream;
import cutting.FileConsumer;
import cutting.FileOrder;
import framework.CommandLineArguments;
import framework.CommandLineArgumentsParser;
import framework.Handleable;
import framework.exceptions.FileAccessException;
import framework.exceptions.InvalidCommandLineArgumentsException;
import optimizer.OffCutCalculator;
import process.Cutting;
import process.Storage;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class Demo {

    private static final Logger LOG = Logger.getLogger(OffCutCalculator.class.getName());

    public static void main(String[] args) {
        long duration;
        long start = System.currentTimeMillis();
        String newline = System.lineSeparator();
        LOG.info("Starting the multithreading to read, process and writ the results.");
        String source;
        try {
            LOG.info(newline + "Reading Command Line Arguments");
            CommandLineArguments arguments = CommandLineArgumentsParser.parse(args);
            source = arguments.getSourceFile();
            duration = System.currentTimeMillis() - start;
            LOG.info(String.format("Command Line Arguments read successfully,%sIt took %dms!%s Inputfile: %s%s", newline, duration, newline, source, newline));
            start = System.currentTimeMillis();
            DataStream<CutJob> jobDataStream = new DataStream<>();
            FileOrder procedure = new FileOrder(source, jobDataStream);
            LinkedBlockingQueue<Handleable.Data<CutJob, Cutting>> resultQueue = new LinkedBlockingQueue<>();
            Storage storage = new Storage();
            OffCutCalculator calculator = new OffCutCalculator(jobDataStream, resultQueue, storage);
            FileConsumer consumer = new FileConsumer(resultQueue);

            Thread A = new Thread(procedure, "Thread A");
            Thread B = new Thread(calculator, "Thread B");
            Thread C = new Thread(consumer, "Thread C");
            A.start();
            B.start();
            C.start();
        } catch (InvalidCommandLineArgumentsException | FileAccessException e) {
            e.printStackTrace();
        } finally {
            duration = System.currentTimeMillis() - start;
            LOG.info(String.format("End of the Programm<(.^;^.)>%sIt took %dms!%s ",newline, duration, newline));
        }

    }
}
