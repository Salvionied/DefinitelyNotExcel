package model.nodes;

import model.Indexer;
import model.program.Program;
import model.program.RangeLoad;

/**
 * Node class for Ranges of cells.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class RangeNode extends NumberVariable
{
    private final int[] cellRange;
    
    /**
     * Constructor for objects of class RangeNode.
     * @param cellRange the range of Cells
     */
    public RangeNode(final int[] cellRange)
    {
        super(cellRange);
        this.cellRange = cellRange.clone();
    }
    
    /**
     *  Method for compiling this Node.
     *  @param p The instance of Program.
     */
    @Override
    public void compile(final Program p) {
        compile(p,new RangeLoad(cellRange));
    }
    
    @Override
    public Type getType() {
        return Type.RANGE;
    }
    
    @Override
    public String toString() {
        final Indexer index = new Indexer();
        return index.getAlpha(cellRange[1])
            + Integer.toString(cellRange[0]) 
            + ":"
            + index.getAlpha(cellRange[3])
            + Integer.toString(cellRange[2]);
    }

}
