package model.nodes;

import model.program.Program;

/**
 * IntNode intermediary class
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class NumberNode extends Node
{   
    
    @Override
    public Type getType() {
        return Type.NUMBER;
    }

    /**
     * Compile this node into the given Program.
     * @param p The program to append this node to
     */
    public void compile(final Program p) {
        // to be implemented in subclasses
    }
}
