package model.program;


/**
 * Utility Class For function Computations
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public final class FunctionHelper
{
    /**
     * Constructor for the utility class, It will never be called since this
     * is a utility static class.
     */
    private FunctionHelper() {
        //USELESS CONSTRUCTOR
    }
    
    /**
     * Static Method to compute the Sum of all the cells.
     * @param storage the storage on which to call
     * @param elements the number of cells
     * @return double number representing the SUM of all the cells.
     */
    public static double computeSum(final Storage storage, final int elements) {
        final OperandStack stack = storage.getOperandStack();
        double res = 0;
        for (int i = 0; i < elements; i++) {
            res += stack.pop().doubleValue();
        }
        return res;
    }
    
    /**
     * Static method to compute the Min and the Max of all the selected cells.
     * @param storage the storage on which to call
     * @param elements the number of cells
     * @return double[] where [0] is min and [1] is max
     */
    public static double[] computeMaxMin(final Storage storage, final int elements) {
        final OperandStack stack = storage.getOperandStack();
        double[] res = new double[2];
        final double x = stack.pop().doubleValue();
        res[0] = x;
        res[1] = x;
        for (int i = 0; i < elements - 1; i++) {
            final double temp = stack.pop().doubleValue();
            if (temp > res[1]) {
                res[1] = temp;
            } else if (temp < res[0]) {
                res[0] = temp;
            }
        }
        return res;
    }
}