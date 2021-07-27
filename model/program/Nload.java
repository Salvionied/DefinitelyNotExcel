package model.program;

import model.Indexer;

/**
 * NLOAD class pushes the value of a Cell onto the stack.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Nload extends Instruction
{
    private final int[] cell;
    
    /**
     * Constructor for the Variable at compilation.
     * 
     * @param cell the row [0] and column [1] of the cell
     */
    public Nload(final int[] cell) {
        super();
        this.cell = cell.clone();
    }
    
    @Override
    public void execute(final Storage storage)  {
        CellLoaderHelper.loadCellOnStack(storage,cell[0],cell[1]);
    }
    
    @Override
    public String toString() {
        final Indexer i = new Indexer();
        return "NLOAD " + i.getAlpha(cell[1]) + Integer.toString(cell[0]) ;
    }
}
