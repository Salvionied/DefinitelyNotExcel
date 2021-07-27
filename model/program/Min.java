package model.program;

/**
 * Min function class Instruction.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Min extends FunctionInstruction
{
    private final int elements;
    
    /**
     * Constructor for Minimum Instruction.
     * @param elements the number of elements on which this operates.
     */
    public Min(final int elements) {
        super();
        this.elements = elements;
    }
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(FunctionHelper.computeMaxMin(storage, elements)[0]);
    }
    
    @Override
    public String toString() {
        return "MIN";
    }
}