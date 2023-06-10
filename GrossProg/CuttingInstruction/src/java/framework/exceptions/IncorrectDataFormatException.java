package framework.exceptions;

/**
 * IncorrectDataFormatException thrown when the provided data is wrongly formatted.
 */
public class IncorrectDataFormatException extends Throwable
{
    /**
     * Constructs an IncorrectDataFormatException
     */
    public IncorrectDataFormatException(){}

    /**
     * Construcs an IncorrectDataFormatException with specified detail message.
     * @param message specified detail why the exceptions occurred.
     */
    public IncorrectDataFormatException(String message)
    {
        super(message);
    }

    /**
     * Constructs an IncorrectDataFormatException with specified detail message and cause.
     * @param message specified detail message
     * @param innerException cause of the exceptions.
     */
    public IncorrectDataFormatException(String message, Exception innerException){
        super(message, innerException);
    }
}
