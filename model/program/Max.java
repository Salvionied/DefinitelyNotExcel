package model.program;

/**
 * Max function class Instruction.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Max extends FunctionInstruction
{
    private final int elements;
    
    /**
     * Constructor for Maximum Instruction.
     * @param elements the number of elements on which this operates.
     */
    public Max(final int elements) {
        super();
        this.elements = elements;
    }
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(FunctionHelper.computeMaxMin(storage, elements)[1]);
    }
    
    @Override
    public String toString() {
        return "MAX";
    }
}
