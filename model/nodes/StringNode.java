package model.nodes;


/**
 * StringNode class
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class StringNode extends Node
{    
    private final String value;
    
    /**
     * Constructor for the String Node class.
     * 
     * @param value The String value Held within this Node
     */
    public StringNode(final String value) {
        super();
        this.value = value;
    }
    
    @Override
    public Type getType() {
        return Type.STRING;
    }
    
    @Override
    public String getValue() {
        return value;
    }
    
    /**
     * Decompile this node into a string.
     * Note that the resulting string may have
     * extra parentheses.
     * @return a String representation of this AST
     */
    public String toString() {
        // to be implemented in subclasses
        return value;
    }
}