package OpticalAutocorrelation.source;

import java.io.File;
import java.util.List;

public abstract class TransactionSource<S extends File,T> implements DataSource {

    private final Pair<S,? extends List<? extends Pair<T,T>>> pair;
//    private final ArrayList <DataSource> dataSources;
//    private final ArrayList<DataSource> sources;

    public TransactionSource(Pair<S ,? extends List<? extends Pair<T,T>>> thatPair) {
//        if (!(thatPair.getContent() != null && thatPair.getSource() != null)) {
        if (thatPair.isEmpty()) {
            throw new IllegalArgumentException("The the element of the pair's elements to initiating " +
                    "the constructor is not correctly derived as expected for the detector's files!");
        }
        this.pair = thatPair;
//        this.dataSources = new ArrayList<>();
//        this.sources = new ArrayList<>();
    }

//    /**
//     *
//     * @param source as composite object will be added in the storage.
//     */
//    public void add(DataSource source) {
//        dataSources.add(source);
//    }

    /**
     * @return new source
     */
    @Override
    public Pair<?, ?> miner() {
//        Pair<T,T> output = processedData(input);

//        Pair<S,T> input = new Pair<>(pair.getSource(), pair.getContent());
//        Pair<T,> processedData(new Pair<>(pair.getSource(), pair.getSource()));
//        return new  Pair<>(pair.getSource(), processingData(pair));

        return new  Pair<>(pair.getSource(), transactionData());
//        Pair<?, ?> pairs =  dataSources.get(0).miner();
//        for (DataSource ds :
//                dataSources) {
//            Pair<?, ?> temp = ds.miner();
//            pairs.
//        }
//        return dataSources.replaceAll(miner(),Pair<>);
    }

//    /**
//     * @param resource
//     */
//    @Override
//    public void setResource(Pair<?, ?> resource) {
//
//    }

    //    /**
//     * @param input is the content from the last processing step
//     * @return the next processing data
//     */
    public abstract Pair<?,?> transactionData() ;

//    public Pair<? extends File,? extends List<? extends Pair<T,T>>> getPair() {
//        return pair;
//    }
}
