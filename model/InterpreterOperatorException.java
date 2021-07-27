package model;

/**
 * Throwable Exception for Operator specific low level Errors.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class InterpreterOperatorException extends InterpreterException
{
    /**
     * Default Operator Exception, thrown when an operator is expected but non are found.
     * 
     */
    public InterpreterOperatorException() {
        super("Error: Operator expected.");
    }
    
    /**
     * Alternative Constructor for case specific Messages.
     * 
     * @param message to be thrown.
     */
    public InterpreterOperatorException(final String message) {
        super(message);
    }
}
