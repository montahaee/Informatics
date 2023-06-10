//package cutting;
//
//import cutting.utility.Truncation;
//import framework.Consumable;
//import framework.Handleable;
//import framework.exceptions.FileAccessException;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.*;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * FileConsumer takes data and writes it to a .txt file.
// * <p></p>
// * The class can be used as a thread an implements the {@link #run() run} method. It
// * also contains a parameter method for writing files that can be used without a thread as well.
// *
// */
//public class FileConsumer implements Consumable<Handleable.Data<Job, CuttingSimulation>>, Runnable {
//
//    LinkedBlockingQueue <Handleable.Data<CutJob, CuttingSimulation>> solutionQueue;
//
//    /**
//     * Creates FileConsumer with a ConcurrentLinedQueue that transfers the data.
//     * @param solutionQueue ConcurrentLinedQueue that holds data
//     */
//    public FileConsumer(LinkedBlockingQueue<Handleable.Data<CutJob, CuttingSimulation>> solutionQueue){
//        this.solutionQueue = solutionQueue;
//    }
//
//    /**
//     * Creates a string from input data and writes it to a .txt file.
//     * @param data the data that has to be written.
//     * @throws FileAccessException as a path may not exist.
//     */
//    @Override
//    public void write(Handleable.Data<Job, CuttingSimulation> data ) throws FileAccessException {
//        Job input = data.in;
//        CuttingSimulation result = data.out;
//        String inputPath = input.getFilepath();
//        if (inputPath.indexOf(".") <= 0) {
//            throw new InputMismatchException("The input data doesn't have extension.");
//        }
//        String outputPath = inputPath.substring(0, inputPath.lastIndexOf("\\")+1);
//        outputPath += "simulated_";
//        outputPath += inputPath.substring(inputPath.lastIndexOf("\\")+1);
//
//        Map<Double,Integer> pipes = input.getPipes();
//        List<Map.Entry<Integer,float[]>> pieces = result.getPieces();
//        List<Double> offcuts = result.getOffcuts();
//        int cuttingCount = result.getCuttingCount();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(String.format(Locale.US, "# Orderer : %s\n", input.getOrderingName())); //TODO using Truncation after replace orderer in
//                                                                                                // the job class by Customer object
//        sb.append("# Cutting2 List :\n");
//        int count = 0;
//        for (Map.Entry<Double, Integer> entry : pipes.entrySet()) {
//            sb.append(entry.getValue()).append("*").append(Truncation.form("", false,entry.getKey()));
//
//            count++;
//            if (count < 9) {
//                sb.append("; ");
//            } else {
//                 sb.append(entry.getValue()).append("*").append(Truncation.form("", true,entry.getKey()));
//
//                count = 0;
//            }
//        }
//        if (count != 0) {
//            sb.append("\n");
//        }
//        sb.append("\n");
////                                                                        stream().reduce((w1,w2)-> w1 + w2).get();
//        sb.append(Truncation.form("TotalOffcut:",true, offcuts.stream().reduce(Double::sum).get()));
//        sb.append(Truncation.form("Number of cuttings:",true, cuttingCount));
//        pieces.forEach((e -> {
//            sb.append(Truncation.form(" ->",false,  e.getKey()));
//            float[] ev = e.getValue();
//
//            for (int  i = 0; i < ev.length - 1; i++) {
//                if ((i/9.0) != (int)(i/9)) {
//                    sb.append(Truncation.form("",false, ev[i]));
//                    sb.append("; ");
//                } else { //write the next line.
//                    sb.append(Truncation.form("",true, ev[i]));
//                    }
//            } //add offcut
//            sb.append(Truncation.form("", false,ev[ev.length - 1])).append(
//                    Truncation.form(" Offcut:",false, offcuts.iterator().next()));
//
//        }));
//
//        Path filePath = Path.of(outputPath);
//
//        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(filePath)){
//            bufferedWriter.write(sb.toString());
//        }catch (IOException e)
//        {
//            throw new FileAccessException("Could not write file to path: " + filePath.toAbsolutePath());
//        }
//    }
//
//    /**
//     * Starts the {@link #write(Handleable.Data)} Method and runs until an empty problem is received.
//     */
//    @Override
//    public void run()
//    {
//        while(true){
//            try
//            {
//                if(solutionQueue.peek() == null){
//                    continue;
//                }
//                Handleable.Data<CutJob, CuttingSimulation> data = solutionQueue.poll();
//                if(data.in == null){
//                    break;
//                }
//                this.write(data);
//            } catch (FileAccessException e)
//            {
//                System.err.println(e.getMessage());
//            }
//        }
//    }
//}
