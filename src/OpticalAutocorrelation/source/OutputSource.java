package OpticalAutocorrelation.source;

import java.io.File;

public abstract class OutputSource<S,T> implements DataSource{

    private final Pair<S,T> pair;
//    private final Pair<S,T> pair;

    public OutputSource(Pair<S, T> thatPair) {
        if (!(thatPair.getSource() instanceof File && thatPair.getContent() instanceof Pair)) {
            throw new IllegalArgumentException("The the element of the pair's elements to initiating " +
                    "the constructor is not correctly derived as expected for the transaction's data!");
        }
        this.pair = thatPair;
    }

    /**
     * @return the final product with its source as key
     */
    @Override
    public Pair<S, ? extends Pair<S,T>> miner() {

        return new Pair< >(pair.getSource(),outputData());
    }

    /**
     * @param resource
     */
//    @Override
    public void setResource(Pair<?, ?> resource) {

    }

    public abstract Pair<S,T> outputData ();
}
