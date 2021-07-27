package model.nodes;


/**
 * Utility class that calculates the amount of elements within the range.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public final class RangeHelper
{
    private RangeHelper() {
        //Useless constructor
    }
    
    /**
     * Method to count the amount of cells that are within the given range.
     * @param cellRange the range of cells
     * @return int the amount of elements.
     */
    public static int countCells(final int[] cellRange) {
        int count = 0;
        for (int i = cellRange[0]; i <= cellRange[2]; i++) {
            for (int j = cellRange[1]; j <= cellRange[3]; j++) {
                count ++;
            } 
        }
        return count;
    }
}
