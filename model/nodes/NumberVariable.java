package model.nodes;

import model.Indexer;
import model.program.Instruction;
import model.program.Nload;
import model.program.Program;

/**
 * Cell Variable from the table.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class NumberVariable extends NumberNode
{
    // the value is stored
    private final int[] cell;
    
    /**
     * Constructor of the variable class.
     * 
     * @param cell Int[] representing the variable
     */
    public NumberVariable(final int[] cell) {
        super();
        this.cell = cell.clone();
    }
    
    @Override
    public Type getType() {
        return Type.NUMBER;
    }
    
    @Override
    public boolean isConstant() {
        return false;
    }
    
    @Override
    public void compile(final Program p) {
        compile(p, new Nload(cell));
    }
    
    /**
     * CellLoader compile for NumberVariables and NumberRanges.
     * @param p Program object
     * @param i the Instruction to compile
     * 
     */
    public void compile(final Program p,final Instruction i) {
        p.append(i);
    }
    
    @Override
    public String toString() {
        final Indexer i = new Indexer();
        return i.getAlpha(cell[1]) + Integer.toString(cell[0]);
    }
}