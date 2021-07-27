package model;

/**
 * Throwable generic Exception for Interpreter related low level errors.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class InterpreterException extends ModelException
{
    /**
     * Default exception constructor for Interpreter related errors.
     * 
     * @param message to be shown as the error .
     */
    public InterpreterException(final String message) {
        super(message);
    }
}
