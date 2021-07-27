package model;

import java.io.IOException;

/**
 * Exception class for IOExceptions given by the SheetModel Class.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class SheetModelException extends IOException {
    
    /**
     * Constructor for Custom Exceptions.
     * 
     * @param message The Message to be shown for the exception.
     */
    public SheetModelException(final String message) {
        super(message);
    }
}
