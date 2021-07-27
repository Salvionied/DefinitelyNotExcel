package model.program;

/**
 * Loads the Number onto the Stack.
 * @author Edoardo Salvioni
 * @author Anton Tanev
 */
public class NPush extends Instruction {
    
    private final Number value;
    
    /**
     * Constructor of the Bipush Class.
     * @param value representing the value to bepushed onto the stack.
     */
    public NPush(final Number value) {
        super();
        this.value = value;
    }
    
    @Override
    public void execute(final Storage storage) {
        storage.getOperandStack().push(value);
    }
    
    @Override
    public String toString() {
        return "NPUSH " + value;
    }

}
