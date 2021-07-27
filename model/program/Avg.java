package model.program;

/**
 * Avg function class Instruction.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Avg extends FunctionInstruction
{
    private final int elements;
    
    /**
     * Constructor for Average Instruction.
     * @param elements the number of elements on which this operates.
     */
    public Avg(final int elements) {
        super();
        this.elements = elements;
    }
    
    @Override
    public void execute(final Storage storage) {
        final OperandStack stack = storage.getOperandStack();
        stack.push(FunctionHelper.computeSum(storage, elements) / elements);
    }

    @Override
    public String toString() {
        return "AVG";
    }
}