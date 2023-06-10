package OpticalAutocorrelation.source;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
///import java.io.FileReader;
//import java.io.IOException;

public abstract class InputSource <S extends String,T> implements DataSource {
    private final S sourceName;


    public InputSource(S thatSourceName) {
        this.sourceName = thatSourceName;
    }

    /**
     * @return file and its contents as key value pair
     */
    @Override
    public Pair<File, List<Pair<T,T>>> miner() {
        File file = new File(sourceName);
//      filename is here saved as metadata to further traceability
///        Pair<T, T> pair = processingData(new Pair<>(filename, file));
///
///        List<Pair<T, T>> content = readSource(file);
///        String meta = file.getName();
///        String resource = meta +":\n" + content;
///        Pair<?, ?> resource = new Pair<>(meta,content);
//        return new Pair<>(file, processingData(new Pair<>(filename, file)));
        return new Pair<>(file, inputData(file));
    }
//
//    /**
//     * @param resource
//     */
//    @Override
    public void setResource(Pair<?, ?> resource) {
//        new InputSource<String, T>
//        miner() = this.miner();
//        DataSource t = this;
//        t.miner() = resource;
    }

    /**
     *
     * @param file is a concrete type for initial processing
     * @return concrete processing data to next step
     */
    public abstract List<Pair<T,T>> inputData(File file);

    public String getSourceName() {
        return sourceName;
    }


//    private LinkedList<String> getFiles (final String folderPath) {
//        LinkedList<String> circularFiles = new LinkedList<>();
//        File folder = new File(folderPath);
//        File[] files = folder.listFiles();
//        for (File file :
//                Objects.requireNonNull(files)) {
//            if (file.isFile() && file.getName().endsWith(".txt")) {
//                circularFiles.add(file.getName());
//            } else if (file.isDirectory()) {
//                getFiles(file.getPath());
//            }
//        }
////		circularFiles.
//        return circularFiles;
//    }

    //    public abstract List<Pair<T,T>> inputProcessing(Pair<?,?> input);

//    /**
//     *
//     * @param file to parsing
//     * @return processing content's data
//     */
//    public abstract List<Pair<T, T>> readSource(File file);

//The following could be worked for all token as part of
// the contents the file but to avoid of redundancy of
// reading implementation to every specific dataformat,
// we change it to  an abstract method.

///    /**
//     *
//     * @param file to reading
//     * @return contents the file independent of its type.
//     */
///    private String readSource(File file) {
//        char[] buffer = null;
//        try (FileReader reader = new FileReader(file)) {
//            buffer = new char[(int) file.length()];
//            reader.read(buffer);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        return new String(buffer != null? buffer : new char[0]);
//    }
//    @Override
//    public void setResource(Pair<?,?>){
//
//    }
    private LinkedList<File> getFiles (final String folderPath) {
        LinkedList<File> circularFiles = new LinkedList<>();
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        for (File file :
                Objects.requireNonNull(files)) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                circularFiles.add(file);
            } else if (file.isDirectory()) {
                getFiles(file.getPath());
            }
        }
    //		circularFiles.
        return circularFiles;
    }
}

