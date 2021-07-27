package tui;

/**
 * Terminal User Interface Version of Definitely Not Excel.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class TuiBase
{
    private TuiModelLoader tm;
    
    /**
     * Initialize the Terminal User Interface.
     */
    public TuiBase() {
        main();
    }
    
    /**
     * Main method fo the Terminal User Interface.
     */
    private void main() {
        tm = new TuiModelLoader();
        boolean flag = true;
        while (flag) {
            final String userInput = System.console().readLine();
            if ("quit".equals(userInput) || "exit".equals(userInput)) {
                flag = false;
                break;
            }
            commands(userInput);
        }
    }
    
    /**
     * Method that Binds basic Operation methods to words.
     * @param input the string
     */
    private void commands(final String input) {
        switch (input) {
            case "set": tm.setValue();
                break;
            case "get": tm.printValue();
                break;
            case "add column": tm.addColumn();
                break;
            case "add row": tm.addRow();
                break;
            default: loadCases(input);
                break;
        }
    }
    
    /**
     * Method that binds loader methods.
     * @param input the keyword
     */
    private void loadCases(final String input) {
        switch (input) {
            case "load": tm.load();
                break;
            case "save": tm.save();
                break;
            case "reset": tm.resetTable();
                break;
            case "help": tm.help();
                break;
            default: undoRedoCases(input);
                break;
        }
    }
    
    /**
     * Method that binds undo/redo methods.
     * @param input the keyword
     */
    private void undoRedoCases(final String input) {
        switch (input) {
            case "undo": tm.undo();
                break;
            case "redo": tm.redo();
                break;
            case "print": tm.print();
                break;
            case "new": tm.newTable();
                break;
            default:
                System.console().printf("Unknown command" + "\n");
                System.console().printf("Type help for commands" + "\n");
                break;
        }
    }
}


