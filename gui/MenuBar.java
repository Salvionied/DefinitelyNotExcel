package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * MenuBar class.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class MenuBar extends JMenuBar 
{
    private TabManager tabs;
    
    /**
     * Constructor for the JMenuBar object.
     * @param tabs The TabManager.
     */
    public MenuBar(final TabManager tabs) {
        super();
        this.tabs = tabs;
        initializeFileMenuBar();
        initializeHelpMenuBar();
    }
    
    /**
     * Initialize the File Menu bar with 3 buttons, save,load and new.
     */
    private void initializeFileMenuBar() {
        final JMenu file = new JMenu("File");
        add(file);
        file.add(initializeSaveButton());
        file.add(initializeLoadButton());
        file.add(initializeResetButton());
    }
    
    /**
     * Initialize the help menu button which opens a secondary window with HelpToolTips.
     */
    private void initializeHelpMenuBar() {
        final JMenuItem help = initializeButton("Help","Get some Help!",false,0);
        add(help);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                help();
            }
        }); 
    }
    
    /**
     * Generic class for initializing subMenuItems or buttons.
     * @param title the String to be displayed on the item
     * @param description the description to be shown when mouse hovers on item
     * @param hasShortcut true if there will be a shortcut.
     * @param keyEvent the int representing the KeyEvent.VirtualKeyboard
     */
    private JMenuItem initializeButton(final String title, 
        final String description, 
        final boolean hasShortcut,
        final int keyEvent) {
        final JMenuItem button = new JMenuItem(title);
        if (hasShortcut) {
            button.setAccelerator(KeyStroke.getKeyStroke(keyEvent,ActionEvent.CTRL_MASK,false));
        }
        button.getAccessibleContext().setAccessibleDescription(description);
        return button;
    }
    
    /**
     * Initialize the saveSubMenuItem.
     */
    private JMenuItem initializeSaveButton() {
        final JMenuItem save = initializeButton(
            "Save as...",
            "Save the current Spreadsheet as csv.",
            true,
            KeyEvent.VK_S);
        listenerHelper(save, "save");
        return save;
    }
    
    /**
     * Initialize the loadSubMenuItem.
     */
    private JMenuItem initializeLoadButton() {
        final JMenuItem load = initializeButton(
            "Open",
            "Open a .CSV File.",
            false,
            0);
        listenerHelper(load, "load");
        return load;
    }
    
    private void listenerHelper(final JMenuItem item, final String function) {
        item.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (getHelper() != null) {
                    if ("load".equals(function)) {
                        getHelper().load();
                    } 
                    if ("save".equals(function)) {
                        getHelper().save();
                    } 
                }
            }
        });
    }
    
    /**
     * Initialize the resetSubMenuItem.
     */
    private JMenuItem initializeResetButton() {
        final JMenuItem newSheet = initializeButton(
            "Reset SpreadSheet",
            "Reset the Current Spreadsheet to a Blank one.",
            true,
            KeyEvent.VK_N);
        newSheet.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                if (getHelper() != null) {
                    getHelper().reset();
                }
            }
        });
        return newSheet;
    }
    
    private UsabilityHelper getHelper() {
        final SpreadSheet sp = tabs.getSelectedSp();
        return sp.getHelper();
    }
    
    /**
     * method to open the helper window.
     */
    private void help() {
        new Helper();
    }
}
