package framework;

import framework.exceptions.IncorrectDataFormatException;

/**
 * The Suppliable Interface should be implemented by any classes whose instances
 * are intended to process some kind of input data. The class must define a supplier
 * methode(a methode with no arguments but return something) called readData.
 * <p>
 * The Interface is designed to provide a common protocol for instances
 * that are designed to be producers.
 * <p>
 * @param <I> The type of output that should be produced
 */
public interface Suppliable<I> {

    /**
     * Reads data and produces specified element.
     * <p>
     * @return returns the element that was produced from the data
     * @throws IncorrectDataFormatException as Data should be formatted correctly in order to process.
     */
    I read() throws IncorrectDataFormatException;
}
