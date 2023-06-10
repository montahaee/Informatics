package cutting;

import framework.Consumable;
import framework.Handleable;
import framework.exceptions.FileAccessException;
import process.Cutting;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
//TODO replace Cutting by Cutting. This causes many changes at this class.
/**
 * FileConsumer takes data and writes it to a .txt file.
 * <p></p>
 * The class can be used as a thread an implements the {@link #run() run} method. It
 * also contains a parameter method for writing files that can be used without a thread as well.
 *
 */
public class FileConsumer implements Consumable<Handleable.Data<CutJob, Cutting>>, Runnable {

    LinkedBlockingQueue <Handleable.Data<CutJob, Cutting>> solutionQueue;

    /**
     * Creates FileConsumer with a ConcurrentLinedQueue that transfers the data.
     * @param solutionQueue ConcurrentLinedQueue that holds data
     */
    public FileConsumer(LinkedBlockingQueue<Handleable.Data<CutJob, Cutting>> solutionQueue){
        this.solutionQueue = solutionQueue;
    }

    /**
     * Creates a string from input data and writes it to a .txt file.
     * @param data the data that has to be written.
     * @throws FileAccessException as a path may not exist.
     */
    @Override //TODO think about if it is not better replace the order attribute by list<Pipe> .
    public void write(Handleable.Data<CutJob, Cutting> data ) throws FileAccessException {
        CutJob input = data.in;
        Cutting result = data.out;
        String inputPath = input.getFilepath();
        if (inputPath.indexOf(".") <= 0) {
            throw new InputMismatchException("The input data doesn't have extension.");
        }
        String outputPath = inputPath.substring(0, inputPath.lastIndexOf("\\")+1);
        outputPath += "optimized_";
        outputPath += inputPath.substring(inputPath.lastIndexOf("\\")+1);

///        Map<Double,Integer> pipes = input.getPipes();
///        List<Map.Entry<Integer,float[]>> pieces = result.toString();
//  /      List<Map.Entry<Integer,float[]>> pieces = result.getPieces();
//   /     List<Double> offcuts = result.getOffcuts();
///        int cuttingCount = result.getCuttingCount();

///        StringBuilder sb = new StringBuilder();
///        sb.append(input.getOrder().toString());

///                                                                        stream().reduce((w1,w2)-> w1 + w2).get();
//T          float[] ev = e.getValue();
//
//            for (int  i = 0; i < ev.length - 1; i++) {
//                if ((i/9.0) != (int)(i/9)) {
//                    sb.append(Truncation.form("",false, ev[i]));
//                    sb.append("; ");
//                } else { //write the next line.
//                    sb.append(Truncation.form("",true, ev[i]));
//                }
//            } //add offcut
//            sb.append(Truncation.form("", false,ev[ev.length - 1])).append(
//                    Truncation.form(" Offcut:",false, offcuts.iterator().next()));
//
//        }));

        Path filePath = Path.of(outputPath);

        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)){
            bufferedWriter.write(result.toString());
        }catch (IOException e)
        {
            throw new FileAccessException("Could not write file to path: " + filePath.toAbsolutePath());
        }
    }

    /**
     * Starts the {@link #write(Handleable.Data)} Method and runs until an empty problem is received.
     */
    @Override
    public void run() {
        while(true){
            try {
                if(solutionQueue.peek() == null){
                    continue;
                }
                Handleable.Data<CutJob, Cutting> data = solutionQueue.poll();
                if(data.in == null){
                    break;
                }
                this.write(data);
            } catch (FileAccessException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}


