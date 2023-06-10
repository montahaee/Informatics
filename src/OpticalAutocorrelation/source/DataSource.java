package OpticalAutocorrelation.source;


public interface DataSource  {

    Pair<?, ?> miner();
//    void setResource(Pair<?, ?> resource);

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
//    void setResource(Pair<?, ?>);
// /   public boolean isG();
///    public abstract List <Pair<T,T>> processingData(Pair<?,?> input);
///    public abstract List <Pair<T,T>> processingData();
//    public abstract Pair<S,T> processingData();
}
