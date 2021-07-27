package model.language;

import model.Indexer;

/**
 * Parse Ranges and CellIdentifiers
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public final  class ParseHelper
{
    /**
     * Useless constructor for Utility Class ParseHelper.
     */
    private ParseHelper(){
        //actually useless constructor for a utility class
    }
    
    /**
     * Static Method for parsing the String formatted CellIdentifier to an array of ints.
     * @param s the Identifier in String format
     * @return int[] an array containing the RowIndex and the ColumnIndex of the Cell,
     *     [0] row [1] col.
     */
    public static int[] cellParser(final String s) {
        final Indexer i = new Indexer();
        String cur = s.replace("(","");
        cur = cur.replace(")","");
        final String[] cellParse = cur.split("(?<=\\D)(?=\\d+\\b)");
        final int[] res = {Integer.parseInt(cellParse[1]),i.getIndex(cellParse[0])};
        return res;
    }
    
    /**
     * Static Method for parsing the String formatted Cell Range to an array of ints.
     * @param s the Range in String format
     * @return int[] an array containing the limits of the selected cell range:
     *     [0] minRow, [1] minCol, [2]maxRow, [3]maxCol
     */
    public static int[] rangeParser(final String s) {
        String cur = s.replace("(","");
        cur = cur.replace(")","");
        final String[] limitCells = cur.split(":");
        final int[] left = cellParser(limitCells[0]);
        final int[] right = cellParser(limitCells[1]);
        final int[] res = {
            left[0],
            left[1],
            right[0],
            right[1]};
        return res;
    }
}
