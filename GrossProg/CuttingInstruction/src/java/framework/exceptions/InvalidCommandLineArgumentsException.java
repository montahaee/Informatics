package framework.exceptions;

public class InvalidCommandLineArgumentsException extends Exception{

    /**
     * Constructs an InvalidCommandLineArgumentsException
     */
    public InvalidCommandLineArgumentsException(){}

    /**
     * Construts an InvalidCommanLineArgumentsException with the specified detail message.
     * @param message specified detail message.
     */
    public InvalidCommandLineArgumentsException(String message){
        super(message);
    }

    /**
     * Constructs an InvalidCommandLineArgumentsException with the specified detail message and cause.
     * @param message specified detail message.
     * @param innerException the cause of the exception.
     */
    public InvalidCommandLineArgumentsException(String message, Exception innerException){
        super(message, innerException);
    }
}
