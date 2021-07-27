import gui.GuiBase;
import tui.TuiBase;


/**
 * main program class.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public final class DefinitelyNotExcel {
    
    /**
     * Actually Useless Constructor.
     */
    private DefinitelyNotExcel() {
        //PlaceHolder
    }
    
    /**
     * Initialize the Program in Your Preferred UserInterface
     * 
     * @param args Commands at terminal call.
     */
    public static void main(final String[] args) {
        if (args.length == 0) {
       
            System.out.println("Welcome to the Terminal User Interface of Definitely Not Excel");
            System.out.println("Commands:");
            System.out.println("To open In Terminal User Interface please type:");
            System.out.println("java DefinitelyNotExcel tui");
            System.out.println("To open in Graphical User Interface please type:");
            System.out.println("java DefinitelyNotExcel gui");
        
        } else {
            if ("gui".equals(args[0])) {
                new GuiBase();
            } else if ("tui".equals(args[0])) {
                System.out.println("You have entered the Tui");
                System.out.println("Type help for commands");
                new TuiBase();
            } else { System.out.println("Error Opening the Software"); }
        }
    }
}
