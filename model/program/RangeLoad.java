package model.program;

import model.Indexer;

/**
 * RangeLoad loads the content of all the cells within the range on the stack.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class RangeLoad extends Instruction
{
    private final int[] cells;
    
    /**
     * Constructor for the Variable at compilation.
     * 
     * @param cells the cellRange
     */
    public RangeLoad(final int[] cells) {
        super();
        this.cells = cells.clone();
        int elements = 0;
        for (int i = cells[0]; i <= cells[2]; i++) {
            for (int j = cells[1]; j <= cells[3]; j++) {
                elements += 1;
            }
        }
    }
    
    @Override
    public void execute(final Storage storage) {
        for (int i = cells[0]; i <= cells[2]; i++) {
            for (int j = cells[1]; j <= cells[3]; j++) {
                CellLoaderHelper.loadCellOnStack(storage,i,j);
            }
        }
    }
    
    @Override
    public String toString() {
        final Indexer i = new Indexer();
        return "RLOAD " 
            + i.getAlpha(cells[1])
            + Integer.toString(cells[0])
            + ":"
            + i.getAlpha(cells[3])
            + Integer.toString(cells[2]);
    }
}
