package process;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DataStream class creates a thread safe container that allows a producer to input data and
 * continuously overwrite the old values. Each takes operation {@link #take()}
 * must wait until a new object is available.
 * <p></p>
 * Provides a one parameter method for adding and method for taking the element from the DataStream.
 *
 * @param <S> The streaming data to be transferred.
 */
public class DataStreame <S>{

    private S _input;
    private final AtomicInteger counter;

    /**
     * Constructs a DataStream and sets the counter to 0 as default;
     */
    public DataStreame() {
        this.counter = new AtomicInteger(0);
    }

    /**
     * Adds data to the stream. Overwriting old data and only making the newest element available.
     * Notifying threads that are waiting to take an item{@link #take()}.
     * @param input is the element that should be added.
     */
    public synchronized void put(final S input) {
        Objects.requireNonNull(input,"Job data cannot be null");
        this._input = input;
        counter.set(1);
        notify();
    }

    /**
     * Retrieves and removes the element from the DataStream,
     * waiting if necessary for another thread to insert it.
     * @return the type of element that is contained in the stream.
     */
    public synchronized S take() {
        while (this.counter.get() == 0){
            try {
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.counter.decrementAndGet();
        }
        counter.decrementAndGet();
        return _input;
    }

}
