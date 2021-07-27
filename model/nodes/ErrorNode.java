package model.nodes;


/**
 * Node Storing the Wrong text, and the error that was raised on it.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class ErrorNode extends StringNode
{
    private final String error;
    private final String value;
    
    /**
     * Constructor for the String Node class.
     * 
     * @param value The String value Held within this Node
     * @param error the text of the error
     */
    public ErrorNode(final String value,final String error) {
        super(value);
        this.value = value;
        this.error = error;
    }
    
    @Override
    public Type getType() {
        return Type.STRING;
    }
    
    @Override
    public String toString() {
        // to be implemented in subclasses
        return value;
    }
    
    @Override
    public String getValue() {
        return error;
    }
}
