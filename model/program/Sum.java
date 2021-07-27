package model.program;

/**
 * Sum function class Instruction.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Sum extends FunctionInstruction
{    
    private int elements;
    
    /**
     * Constructor for Summation Instruction.
     * @param elements the number of elements on which this operates.
     */
    public Sum(final int elements) {
        super();
        this.elements = elements;
    }
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(FunctionHelper.computeSum(storage,elements));
    }
    
    @Override
    public String toString() {
        return "SUM";
    }
}
