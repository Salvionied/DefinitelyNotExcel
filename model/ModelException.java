package model;

/**
 * Throwable exception class for all the Low Level foreseeable errors.
 * 
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 * 
 */
public class ModelException extends Exception
{
    /**
     * Default constructor for ModelExceptions.
     * 
     * @param message to be thrown
     */
    public ModelException(final String message) {
        super(message);
    }
}
