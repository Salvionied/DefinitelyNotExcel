package model;

import java.util.ArrayList;

/**
 * Maps an alphabetical Name to a value "A" to 0 "AA" to 27.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Indexer
{
    private final ArrayList<String> indexes;

    /**
     * Constructor for the indexer.
     */
    public Indexer() {
        final char[] values = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        indexes = new ArrayList<String>();
        for (final char e: values) {
            indexes.add(String.valueOf(e));
        }
        for (final char e: values) {
            for (final char f: values) {
                indexes.add("" + e + f);    
            }
        }
    }
    
    /**
     * Retrieve the index of the Given Cell Alphanumeric Identifier.
     * 
     * @param value the Alphanumeric identifier of the cell.
     * @return int index of the cell.
     */
    public int getIndex(final String value) {
        for (int i = 0; i < indexes.size(); i++) {
            if (value.equals(indexes.get(i))) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Method to retrieve the alphanumeric Identifier from the index.
     * @param index the index of the col you want the Alphanumeric Identifier for.
     * @return the Alphanumeric String name at the given index
     */
    public String getAlpha(final int index) {
        return indexes.get(index);
    }
}
