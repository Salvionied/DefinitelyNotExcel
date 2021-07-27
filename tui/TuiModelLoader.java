package tui;

import model.Indexer;
import model.SheetModel;
import model.nodes.Node;
import model.nodes.Type;

/**
 * Terminal User Interface methods which interface with backend.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class TuiModelLoader
{
    private int row;
    private int column;
    private SheetModel sm;
    private  String columns = "ABCDEFGHIJKLMNOPQRSTUVXYZ";

    /**
     * Initialize the Terminal User Interface.
     */
    public TuiModelLoader() {
        this.sm = null;
    }
    
    /**
     * Checks if the input is a number or not.
     * 
     * @input strNum the String to be checked
     * @returns boolean true if it is a number false otherwise
     */
    private static boolean isNumeric(final String strNum) {
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    private boolean isAlphabetic(final String str) {
        return columns.contains(str);
    }
    
    /**
     * Method that initializes a table.
     * 
     */
    public void undo() {
        if (!error()) {
            sm.undo();
            print();
        }
    }
    
    /**
     * Method that initializes a table.
     * 
     */
    public void redo() {
        if (!error()) {
            sm.redo();
            print();
        }
    }
    
    /**
     * Method to get the Max width of the rows within the SpreadSheet.
     * @returns int the width of the Sheet.
     */
    private int getWidth() {
        int count = 0;
        for ( int i = 0; i < sm.getHeight(); i++) {
            if (sm.getWidth(i) > count) {
                count = sm.getWidth(i);
            }
        }
        return count;
    }
    
    /**
     * Method that initializes a table.
     * 
     */
    public void newTable() {
        sm = new SheetModel(10, 10);
        System.console().printf("New table created" + "\n");
        print();
    }
    
    /**
     * Method to reset the current SpreadSheet.
     * 
     */
    public void resetTable() {
        if (!error()) {
            sm.resetSheetModel(10,10);
            System.console().printf("Table reset" + "\n");
            print();
        }
    }
    
    /**
     * Method to set the value of a cell.
     * 
     */
    public void setValue() {
        if (!error()) {
            getterSetterHelper();
            System.console().printf("Which value:");  
            final String input = System.console().readLine();
            sm.setParse(row, column, input);
            sm.add();
            System.console().printf("Value set" + "\n");      
            print();
        }
    }
    
    /**
     * Method to print the value of a Cell, its content.
     */
    public void printValue() {
        if (!error()) {
            getterSetterHelper();
            if (sm.get(row, column) != null) {
                if (sm.get(row, column).getType() == Type.STRING) {
                    System.console().printf("The value is: " 
                        + sm.get(row, column).getValue()
                        + "\n");
                } else {
                    System.console().printf("The value is: " + sm.get(row, column).toString()
                        + "\n");
                }
            } else { System.console().printf("No Value found" + "\n"); } 
        }
    }
    
    /**
     * Method to help the get and set methods.
     * 
     */
    private void getterSetterHelper() {
        System.console().printf("Which row:");
        String input = System.console().readLine();
        if (isNumeric(input)) { 
            row = Integer.parseInt(input);
        } else { 
            System.console().printf("Invalid input." + "\n");
            return;
        }
                            
        System.console().printf("Which column:");
        input = System.console().readLine();
        if (isAlphabetic(input)) { 
            column = columns.indexOf(input);
        } else { 
            System.console().printf("Invalid input." + "\n");
            return;
        }
    }
    
    /**
     * Method to add one column in the spreadsheet.
     */
    public void addColumn() {
        if (!error()) {
            for (int i = 0; i < sm.getHeight(); i++) {
                sm.set(i, sm.getWidth(i), null);
            }
            System.console().printf("Column added." + "\n");
            print();
        }
    }
    
    /**
     * Method to add one row in the spreadsheet.
     */
    public void addRow() {
        if (!error()) {
            sm.set(sm.getHeight(), getWidth() - 1, null);
            System.console().printf("Row added." + "\n");
            print();
        }
    }
    
    /**
     * Method to save the Spreadsheet.
     */
    public void save() {
        if (!error()) {
            System.console().printf("Filename:");
            final String input = System.console().readLine();
            sm.setFileName(input);
            try {
                sm.saveSheet();
            } catch (java.io.IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }
    
    /**
     * Method to load an existing Spreadsheet in .csv format.
     */
    public void load() {
        System.console().printf("Filename:");
        final String input = System.console().readLine();
        try {
            sm.loadSheet(input,10,10);
            sm.resetCtaker();
            print();
        } catch (java.io.IOException ioe) {
            System.console().printf(ioe.getMessage());
        }
    }
    
    /**
     * Metod to print the various Help Tooltips.
     */
    public void help() {
        System.console().printf(
            "new " + ":" + " Creates a new table."
            + "\n" + "set " + ":" + " Sets a value in the table." 
            + "\n" + "get " + ":" + " Gets a value from the table."
            + "\n" + "reset " + ":" + " Resets the table." 
            + "\n" + "add column " + ":" + " Adds a column." 
            + "\n" + "add row " + ":" + " Adds a new row." 
            + "\n" + "load " + ":" + " Loads the table." 
            + "\n" + "save " + ":" + " Saves the table." 
            + "\n" + "undo " + ":" + " Undoes an Action."
            + "\n" + "redo " + ":" + " Redoes an Action." 
            + "\n" + "quit/exit " + ":" + " Quit the Tui." 
            + "\n");
    }
    
    /**
     * Metod to print the sheet.
     */
    public void print() {
        if (!error()) {
            String columns = "      |";
            for (int i = 0 ;i < getWidth(); i++) {
                columns += maxPrintSize(new Indexer().getAlpha(i)) + "|";
            }
            System.console().printf(columns + "\n");
            for (int i = 0; i < sm.getHeight(); i++) {
                for (int j = -1; j < getWidth(); j++ ) {
                    if (j < 0) {
                        System.console().printf(maxPrintSize(Integer.toString(i)) + "|");
                    } else {
                        final Node node = sm.get(i,j);
                        printHelper(node, i, j);
                    }
                }
                System.console().printf("\n");
            }
        }
    }
    
    /**
     * Method to limit the printed chars to 4
     * 
     * @param str the String to be cut.
     * @returns String the cut string
     */
    private static String maxPrintSize(final String str) {
        String temp = str;
        if (temp.length() > 6) {
            return temp.substring(0, 6);
        } else { 
            final int empty = 6 - str.length();
            for (int i = 0; i < empty; i++) {
                temp += " ";
            }
            return temp;
        }
    }
    
    /**
     * Method to help the printer.
     * 
     * @param node the node to be printed
     * @param col the column of the cell in the spreadsheet
     * @param row the row of the cell in the spreadsheet
     */
    private void printHelper(final Node node, final int row, final int col) {
        final String line = "|";
        if (node != null) {  
            final String res = sm.getCellResult(row,col).toString();
            System.console().printf(maxPrintSize(res) + line);
        } else {
            System.console().printf("      |");
        }
    }
    
    /**
     * Method to print an error message.
     */
    private boolean error() {
        if (sm == null) {
            System.console().printf("Create a new table first with new" + "\n"); 
            return true;
        } else { return false; }
    }
}
