package model.program;

import model.nodes.Node;
import model.nodes.Type;

/**
 * Utility class for Loading cells from the SheetTable.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public final class CellLoaderHelper
{
    
    /**
     * An actually useless constructor for a Static Utility Class.
     */
    private CellLoaderHelper() {
        //A useless Constructor for a Util Class.
    }
    
    /**
     * Static Method for Loading a Cell onto the Stack.
     * @param storage the Storage on which to execute
     * @param row the row in which the cell to load is situated
     * @param col the column in which the cell is situated
     */
    public static void loadCellOnStack(
        final Storage storage, 
        final int row, 
        final int col) {
        final Node a = storage.getVariableTable().get(row,col);
        final Program p = new Program();
        if (a != null) {
            if (a.getType() != Type.NUMBER) {
                storage.getOperandStack().push(0);
            } else {
                a.compile(p);
                final Number res = p.execute(storage.getVariableTable());
                storage.getOperandStack().push(res);
            }
        } else { storage.getOperandStack().push(0); }
    }
    
}
