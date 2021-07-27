package model;

import model.language.Interpreter;
import model.nodes.Node;

import java.io.IOException;
import java.util.ArrayList;

/**
 * BackEnd Class to manage the SpreadSheet
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class SheetModel
{
    private Node[][] bdArray;
    private ArrayList<SheetModelListener> listeners;
    private SheetModelLoader loader;
    private Caretaker ct;
    private final Interpreter interpreter;

    /**
     * Constructor for an empty bidimensional array of size 0:0.
     */
    public SheetModel() {
        this.interpreter = new Interpreter(this);
        bdArray = new  Node[1][0];
        listeners = new ArrayList<SheetModelListener>();
        loader = new SheetModelLoader(this);
        ct = new Caretaker(this);
        ct.add();
    }
    
    /**
     * Constructor for an empty bidimensional array of size Height:width.
     * 
     * @param height the number of rows within the array
     * @param width the number of columns within the array
     */
    public SheetModel(final int height, final int width) {
        this.interpreter = new Interpreter(this);
        bdArray = new Node[height + 1][width + 1];
        listeners = new ArrayList<SheetModelListener>();
        loader = new SheetModelLoader(this);
        ct = new Caretaker(this);
        ct.add();
    }
    
    /**
     * Method for resetting the SheetModel.
     * 
     * @param rows the number of rows of the new sheet
     * @param cols the number of cols of the new sheet
     */
    public void resetSheetModel(final int rows, final int cols) {
        bdArray = new Node[rows][cols];
        loader.setPath("");
        ct.add();
        fireSheetModelChanged();
    }
    
    /**
     * Method for setting the Content of said Cell at index row and index column.
     * 
     * @param row the RowIndex of the cell we want to change
     * @param column the columnIndex of the cell we want to change
     * @param content the Node Object we want to place at Row,Column
     */
    public void set(final int row, final int column, final Node content) {
        if (row < getHeight() && row >= 0) {
            if (column < getWidth(row) && column >= 0) {
                bdArray[row][column] = content; 
            } else if (column >= getWidth(row)) {
                addColumn(row);
                set(row,column,content);
            }
        } else if (row >= getHeight()) {
            addRow();
            set(row,column,content);
        }
    }
    
    /**
     * Method for inserting a value in the Model at the given index after parsing it.
     * 
     * @param row the RowIndex
     * @param column the ColumnIndex
     * @param content the String we want to interpret
     */
    public void setParse(final int row,final int column, final String content) {
        set(row,column, interpreter.parseCode(content));
        fireSheetModelChanged();
    }
    
    /**
     * Method for accessing the value at the given coordinates.
     * 
     * @param row of the element
     * @param column of the element
     * @return Node object stored at the give coordinates
     */
    public Node get(final int row, final int column) {
        if (row < getHeight() && row >= 0) {
            if (column < getWidth(row) && column >= 0) {
                return bdArray[row][column];
            }
        }
        return null;
    }
    
    /**
     * Method to retrieve the result of the given cell.
     * 
     * @param rowIndex the row index of the cell
     * @param colIndex the col index of the cell
     * @return Object could be either the result of the Calculation or the String of the error.
     */
    public Object getCellResult(final int rowIndex,final int colIndex) {
        return interpreter.execute(get(rowIndex,colIndex));
    }
    
    /**
     * Method for getting the Height of the Model.
     * 
     * @return the height of the model.
     */
    public int getHeight() {
        return bdArray.length;
    }
    
    /**
     * Method for getting the Width of the model.
     * 
     * @param i rowIndex
     * @return the width of the model at ith row
     */
    public int getWidth(final int i) {
        return bdArray[i].length;
    }
    
    /**
     * Private Method to add a row to the Array.
     */
    private void addRow() {
        Node[][] temp = new Node[getHeight() + 1][0];
        for (int i = 0; i < getHeight(); i++) {
            temp[i] = bdArray[i];
        }
        bdArray = temp;
    }
    
    /**
     * Private method to add a column to the array.
     * 
     * @param row the row to which we want to add a new Column
     */
    private void addColumn(final int row) {
        Node[] temp = new Node[getWidth(row) + 1];
        for (int i = 0; i < getWidth(row); i++) {
            temp[i] = bdArray[row][i];
        }
        bdArray[row] = temp;
    }
    
    /**
     * Method for adding a new Listener.
     * 
     * @param sml SheetModelListener object
     */
    public void addSheetModelListener(final SheetModelListener sml) {
        listeners.add(sml);
    }
    
    /**
     * Method for removing a Listener.
     * 
     * @param sml SheetModelListener object
     * 
     */
    public void removeSheetModelListener(final SheetModelListener sml) {
        listeners.remove(sml);
    }
    
    /**
     * Notify all the listeners.
     */
    public void fireSheetModelChanged() {
        if (!listeners.isEmpty()) {
            for (final SheetModelListener sml: listeners) {
                sml.sheetModelChanged(this);
            }
        }
    }

    /**
     * Load an existing Spreadsheet.
     * 
     * @param filename The name of .CSV file we want to load
     * @param rows the number of rows
     * @param cols the number of cols
     * @throws IOException if format is invalid or file is missing or non existent
     */
    public void loadSheet(
        final String filename, 
        final int rows, 
        final int cols) throws IOException {
        loader.load(filename,rows,cols);
        resetCtaker();
    }
    
    /**
     * Set current filename.
     * 
     * @param name of the file 
     */
    public void setFileName(final String name) {
        loader.setFileName(name);
    }
    
    /**
     * Set path of the file.
     * 
     * @param path the path of the file
     */
    public void setPath(final String path) {
        loader.setPath(path);
    }
    
    /**
     * Save the Current Workspace on a csv file.
     * 
     * @throws IOException if no path was specified for saving.
     */
    public void saveSheet() throws IOException {
        loader.save();
    }
    
    /**
     * save the current state on the caretaker as a memento.
     */
    public void add() {
        ct.add();
    }
    
    /**
     * Undo the last action.
     */
    public void undo() {
        ct.undo();
    }
    
    /**
     * redo the last action.
     */
    public void redo() {
        ct.redo();
    }
    
    /**
     * resets the caretaker saved states.
     */
    public void resetCtaker() {
        this.ct = new Caretaker(this);
        ct.add();
    }
    
    /**
     * Save the current state of the object as a memento and return it.
     * @return Memento object storing the state of the object
     */
    public Memento saveToMemento() {
        //final Node[][] stateToSave = cloned();
        return new Memento(bdArray);
    }
    
    /**
     * Restore the Current object from aMemento object (previous or later state).
     * @param memento the object containing the older or newer state of the object.
     */
    public void restoreFromMemento(final Memento memento) {
        this.bdArray = memento.getSavedState();
        fireSheetModelChanged();
    }
    
    /**
     * Static class to save the state of the Object based on the memento Pattern.
     * @author Edoardo Salvioni
     * @author Anton Tanev
     * @Version 0.0.1
     */
    public static class Memento {
        private final Node[][] state;
        
        /**
         * Constructor of the memento, wraps the object in another one to save its state.
         * @param stateToSave the current state of the object.
         */
        public Memento(final Node[][] stateToSave) {
            this.state = cloned(stateToSave);
        }
        
        /**
         * Private method to retrieve a deep copy of the Array to save the state.
         * @return Node[][] array of the cellgrid.
         */
        private Node[][] cloned(final Node[][] toCopy) {
            Node[][] clone = new Node[toCopy.length][];
            for (int i = 0; i < toCopy.length; i ++ ) {
                clone[i] = new Node[toCopy[i].length];
                for (int j = 0; j < toCopy[i].length; j++) {
                    clone[i][j] = toCopy[i][j];
                }
            }
            return clone;
        }
        
        /**
         *  Method to retrieve the saved state of the Object.
         *  @return the Node[][] Array.
         */
        private Node[][] getSavedState() {
            return state;
        }
    }
    
}
