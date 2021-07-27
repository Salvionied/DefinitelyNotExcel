package model.nodes;

import model.program.NPush;
import model.program.Program;


/**
 * A Literal is an AST node that 
 * corresponds to a literal Numerical value
 * (a number in the source code).
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class NumberLiteral extends NumberNode {
    
    private final Number value;
    
    
    /**
     * Create a new Literal node.
     * @param value the integer value this node evaluates to
     */
    public NumberLiteral(final double value) {
        super();
        this.value = value;
    }

    @Override
    public Type getType() {
        return Type.NUMBER;
    }

    @Override
    public boolean isConstant() {
        return true;
    }
    
    @Override
    public void compile(final Program p) {
        p.append(new NPush(value));
    }
    
    @Override
    public String toString() {
        return "" + value;
    }
    
}