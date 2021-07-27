package model.program;

import model.SheetModel;

/**
 * The SheetModel onto which to Execute Cellloading or rangeloading is stored here,
 * as well as the OperandStack to that holds all the Values needed for the current Program.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Storage {
    
    private final OperandStack stack;
    private final SheetModel variables;
    
    /**
     * Create a Storage.
     * @param stack The OperandStack
     * @param variables The VariableTable
     */
    public Storage(final OperandStack stack, final SheetModel variables) {
        this.stack = stack;
        this.variables = variables;
    }
    
    /**
     * Get the OperandStack.
     * @return The OperandStack
     */
    public OperandStack getOperandStack() {
        return stack;
    }
    
    /**
     * Get the VariableTable.
     * @return The VariableTable
     */
    public SheetModel getVariableTable() {
        return variables;
    }
    
}
