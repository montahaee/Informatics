package cutting;

import framework.Suppliable;
import framework.exceptions.FileAccessException;
import framework.exceptions.IncorrectDataFormatException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;
//TODO this class will be removed. Because it is developed and replaced by FileOrder.
/**
 * FileSupplier is used to simulate providing spider cam data from the file.
 * It implements method {@link #read()} from {@link Suppliable}.
 * <p>
 *  The CutJob dat provides methods for reading a file and getting files from a directory.
 * <p>
 *  Notice: <u>The file format of input data has to be textuell!</u>
 */
public class FileSupplier implements Suppliable<CutJob>, Runnable{

    private Path sourcePath;
    private final DataStream<CutJob> jobDataStream;
    private final List<Path> filepaths;

    public FileSupplier(String sourcePath, DataStream<CutJob> jobDataStream) throws FileAccessException {
        this.sourcePath = Path.of(sourcePath);
        this.jobDataStream = jobDataStream;
        this.filepaths = this.getPaths();

    }

    /**
     * Creates a list of files. Traverses the file-tree if a directory is provided.
     * @return all existed input files in a path to simulate their contents in FileConsumer.
     * @see FileConsumer1
     */
    private List<Path> getPaths() throws FileAccessException{
        List<Path> paths = new ArrayList<>();
        if (!Files.exists(sourcePath))
        {
            throw new FileAccessException("Filepath does not exist.");
        }

        if(Files.isDirectory(sourcePath)){
            try(Stream<Path> fileTree = Files.walk(sourcePath, 1)){

                fileTree.filter(Files::isRegularFile).forEach(p ->
                {
                    String fileName = p.getFileName().toString().toLowerCase();
//                    File file = p.toFile();
                    if(!fileName.endsWith(".csv") && !paths.contains(p) ){
                        paths.add(p);
                    }

                });
            }catch (IOException e){
                throw new FileAccessException("Could not walk file tree.");
            }
        }else if (Files.isRegularFile(sourcePath)){
            paths.add(sourcePath);
        }

        return paths;
    }

    /**
     * Reads data and produces specified element.
     *
     * @return the important contents of the file to process them as input the
     * @throws IncorrectDataFormatException as Data should be formatted correctly in order to process.
     */
    @Override
    public CutJob read() throws IncorrectDataFormatException {

        Map<Double,Integer> pipes = new HashMap<>();
        String orderer ="";
        try(BufferedReader bfr= new BufferedReader(Files.newBufferedReader(sourcePath))) {

            String line;
            String[] lineContents;
            boolean firstInfo = true;
            do {
                line = bfr.readLine();
                lineContents = line.trim().split(",");
//              Some last names consist of more than one word.  2 <= (first- and lastname).length <= 4.
                if ( 2 <= lineContents.length && lineContents.length <= 4 && firstInfo) {
                    for (int i = 0; i < lineContents.length && firstInfo; i++) {
//                  check if all string is alphabetic
                        firstInfo = lineContents[i].matches("\\w*[^0-9]");
                    }
                    if (firstInfo) {
                        orderer = line;
                        firstInfo = false;

                    } else {
                        System.out.println("The name of the orderer is not found in the file: " + sourcePath.getFileName());
                        System.out.println("The file will be processed without of orderer's name.");
                    }
                } else {
//                  The new initialization(old one was for the name of orderer) works for an Array of Strings.
                    lineContents = line.trim().split(";");
                    boolean secondInfo = true;
                    for (int i = 0; i < lineContents.length && secondInfo; i++) {
                        String[] subword = lineContents[i].split("\\*");
                        secondInfo  = subword.length == 2;
                        secondInfo &= subword[0].matches("\\d+,\\d+"); // \d same as [0-9]+
                        secondInfo &= subword[1].matches("\\d+");
                        double key = Float.parseFloat(subword[0]);
                        if (pipes.containsKey(key)) {
                            System.out.println("The same length of the stored pip is found." +
                                    " Therefor will be only added its numbers to the stored one.");
                            pipes.put(key,pipes.get(key) + Integer.parseInt(subword[1]));
                        } else { //TODO check if the length of the pipes namely subword[1] less than 5!
                            pipes.put(Double.parseDouble(subword[0]),Integer.parseInt(subword[1]));
                        }
                    }
                    if (!secondInfo) {
                        throw new IncorrectDataFormatException(sourcePath.getFileName() + ": Wrong Data Format!");
                    }
                }

            } while (bfr.readLine() != null);



        } catch (IOException e) {
            e.printStackTrace();
        }
//        return new CutJob(sourcePath.toString(),orderer,pipes);
        return new CutJob();
    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general process of the method {@code run} is that it may
     * take any action whatsoever.
     * <p>
     * Hir therefor the list of files will be iterated  and invoked the
     * {@link #read()} method. Continues until all input files have
     * been processed meanwhile each processed file is transferred to
     * the DataStream.
     * @see Thread#run()
     * @see DataStream
     */
    @Override
    public void run() {
//        List<Path> paths = filepaths;
        Set<Path> processedFiles = new HashSet<>();
        while (processedFiles.size() != filepaths.size()) {

            for (Path path : filepaths) {
                try {
                    String filename = path.toString().substring(0,
                            path.toString().lastIndexOf("\\")+1);
                    filename += "simulated_";
                    filename += path.toString().substring(path.toString().lastIndexOf("\\")+1);
                    Path targetPath;

                    targetPath = Path.of(sourcePath.toString().substring(
                            0, sourcePath.toString().lastIndexOf("\\") + 1) + "out" + sourcePath.toString().substring(
                            sourcePath.toString().lastIndexOf("\\") + 1));
                    if (Files.exists(Path.of(filename))) {
                        processedFiles.add(path);
                        continue;
                    }
                    sourcePath = path;
                    this.jobDataStream.put(read());
                    Thread.sleep(50);
                } catch (InterruptedException | IncorrectDataFormatException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
        this.jobDataStream.put(new CutJob());
    }
}
