package cutting;

import cutting.utility.Truncation;
import framework.Suppliable;
import framework.exceptions.FileAccessException;
import framework.exceptions.IncorrectDataFormatException;
import process.Customer;
import process.Order;
import process.Pipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;


/**
 * FileSupplier is used to simulate providing spider cam data from the file.
 * It implements method {@link #read()} from {@link Suppliable}.
 * <p>
 *  The CutJob dat provides methods for reading a file and getting files from a directory.
 * <p>
 *  Notice: <u>The file format of input data has to be textuell!</u>
 */
public class FileOrder implements Suppliable<CutJob>, Runnable {


    private Path sourcePath;
    private final DataStream<CutJob> jobDataStream;
    private final List<Path> filepaths;

    public FileOrder(String sourcePath, DataStream<CutJob> jobDataStream) throws FileAccessException {
        this.sourcePath = Path.of(sourcePath);
        this.jobDataStream = jobDataStream;
        this.filepaths = this.getPaths();

    }

    /**
     * Creates a list of files. Traverses the file-tree if a directory is provided.
     * @return all existed input files in a path to simulate their contents in FileConsumer.
     * @see FileConsumer
     */
    private List<Path> getPaths() throws FileAccessException{
        List<Path> paths = new ArrayList<>();
        if (!Files.exists(sourcePath)) {
            throw new FileAccessException("Filepath does not exist.");
        }

        if(Files.isDirectory(sourcePath)){
            try(Stream<Path> fileTree = Files.walk(sourcePath, 1)){

                fileTree.filter(Files::isRegularFile).forEach(p -> {
                    String fileName = p.getFileName().toString().toLowerCase();
//                    File file = p.toFile();
                    if(!fileName.startsWith("optimized_") && !paths.contains(p) ){
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
        String[] lineContents;
///        String massage = "";
//        boolean isDuplicated = false;
        boolean isFirstLine = true;
        boolean isSecondLine = true;
//        Customer customer = new Customer();
        Order order = new Order(new Customer());
//        List<Map.Entry<Pipe, Integer>> items;
        try (BufferedReader bfr = new BufferedReader(Files.newBufferedReader(sourcePath))) {
            String line;
            final String digit = "[0-9]+(\\.[0-9]*)?"; // Represent a positive real number

            while ((line = bfr.readLine()) != null) { // && !isDuplicated) {
                if (isFirstLine && !line.startsWith("#")) {
                    String info = sourcePath.getFileName().toString();
                    info += System.lineSeparator();
                    info += "File will be processed without comment.";
                    System.out.print(Truncation.form("We could not find a comment line in file: ","s",true, info));
                    isFirstLine = false;
                } else if (isFirstLine) {
                    isFirstLine = false;
                } else if (isSecondLine && line.contains(",")) {
//                    massage = "customer's name";
                    lineContents = line.trim().split(" *, *");
                    isSecondLine = (lineContents.length >= 2);
                    for (int i = 0; (i < lineContents.length && isSecondLine); i++) {
//                        isSecondLine = lineContents[i].matches("^[a-zA-Z].{2,}"); Maybe the both first and lastname consist of tow or more characters.
                        isSecondLine = lineContents[i].matches("^[a-zA-Z]+");
                    }
                    if (!isSecondLine) {
                        throw new IncorrectDataFormatException("The customer's name failed!");
                    }
//                    isDuplicated = order.getCustomer().isSpecified();
                    String firstname = lineContents[lineContents.length - 1];
                    StringBuilder lastname = new StringBuilder();
                    for (int i = 0; i < lineContents.length - 2; i++) {
                        lastname.append(lineContents[i]).append(" ");
                    }
//                    lastname.delete(lastname.length()-1, lastname.length());
                    lastname.append(lineContents[lineContents.length - 2]);
//                    customer = new Customer(firstname, lastname.toString());
                    order = new Order(new Customer(firstname, lastname.toString()));
                    isSecondLine = false;
                } else if (isSecondLine) {
                    isSecondLine = false;
                } else if (line.contains("#")) { // remove the comment signe and text after that inside the line.
                    line = line.substring(0,line.indexOf("#"));
                } else if (line.isEmpty()) {
                    line = line.replaceAll("\\s", "");

                } else if ((lineContents = line.trim().split(";")).length >= 1) {
//                    } else if (line.matches(digit) && line.contains("*")) {

//                    massage = "not corrected orderlist";

                    for (String content : lineContents) {
                        String[] item = content.trim().split("\\*");
                        if (item.length == 2 && item[0].matches(digit) && item[1].matches(digit)) {
                            order.addItem(new Pipe(Double.parseDouble(item[1])), Integer.parseInt(item[0]));
                        }
                    }
                    if (order.getItems().isEmpty()) {
                        throw new IncorrectDataFormatException(Truncation.form(
                                order.getCustomer().toString(),"s", false," don't have orderlist."));
                    }
                } else {
                    throw new IncorrectDataFormatException(sourcePath.getFileName() +": Wrong Data Format!");
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new CutJob(sourcePath.toString(), order);
    }

    /**
     *
     * @return The path of input source files.
     */
    public Path getSourcePath() {
        return this.sourcePath;
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
                    filename += "optimized_";
                    filename += path.toString().substring(path.toString().lastIndexOf("\\")+1);
///                    Path targetPath;
///
// /                   targetPath = Path.of(sourcePath.toString().substring(
//                            0, sourcePath.toString().lastIndexOf("\\") + 1) + "out" + sourcePath.toString().substring(
//                            sourcePath.toString().lastIndexOf("\\") + 1));
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
