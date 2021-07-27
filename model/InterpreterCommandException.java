package model;

/**
 * Throwable  Exception for Function specific Errors.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class InterpreterCommandException extends InterpreterException
{
    /**
     * Default constructor for Function specific error within the Interpreter
     * 
     * @param message to be thrown.
     */
    public InterpreterCommandException(final String message) {
        super("Error: The function " + "\"" + message + "\"" +  " is not defined.");
    }
}
