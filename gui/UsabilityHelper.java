package gui;

import model.SheetModel;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.KeyStroke;

/**
 * Class that enables to import Copied Cells from Excel
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class UsabilityHelper implements ActionListener {
    private Clipboard system;
    private JTable table;
    private TableModel tm;
    private SheetModel sm;
    private Table tableObj;
    
    /**
     * Constructor for the UsabilityHelper.
     * @param table the JTable of the SpreadSheet.
     * @param t the Table object.
     */
    public UsabilityHelper(final JTable table, final Table t) {
        tableObj = t;
        
        this.tm = t.getTm();
        this.table = table;
        this.sm = tm.getSM();
        registerStrokes();
        system = Toolkit.getDefaultToolkit().getSystemClipboard();
    }
    
    private void registerStrokes() {
        final KeyStroke copy = createMaskedStrokes(KeyEvent.VK_C);
        final KeyStroke paste = createMaskedStrokes(KeyEvent.VK_V);
        final KeyStroke cut = createMaskedStrokes(KeyEvent.VK_X);
        final KeyStroke save = createMaskedStrokes(KeyEvent.VK_S);
        final KeyStroke newSheet = createMaskedStrokes(KeyEvent.VK_N);
        final KeyStroke undo = createMaskedStrokes(KeyEvent.VK_Z);
        final KeyStroke redo = createMaskedStrokes(KeyEvent.VK_R);
        final KeyStroke deleteSel = KeyStroke.getKeyStroke("BACK_SPACE");
        registerShortcuts("newSheet",newSheet);
        registerShortcuts("Save", save);
        registerShortcuts("Cut", cut);
        registerShortcuts("Copy",copy);
        registerShortcuts("Paste",paste);
        registerShortcuts("delete", deleteSel);
        registerShortcuts("undo", undo);
        registerShortcuts("redo", redo);
    }
    
    private KeyStroke createMaskedStrokes(final int key) {
        return KeyStroke.getKeyStroke(key,ActionEvent.CTRL_MASK,false);
    }
    
    private void registerShortcuts(final String name, final KeyStroke ks) {
        table.registerKeyboardAction(this,name,ks,JComponent.WHEN_FOCUSED);
    }
    
    /**
     * Method to get the JTable Object.
     * @return JTable object stored within the usHElper
     */
    public JTable getJTable() { 
        return table; 
    }
    
    /**
     * Method to set the JTable to a new one within the usHelper.
     * @param table the Jtable object
     */
    public void setJTable(final JTable table) { 
        this.table = table; 
    }
    
    /**
     * Method to Listen for events Keyboard related.
     * @param e the ActionEvent.
     */
    public void actionPerformed(final ActionEvent e) {
        final String ac = e.getActionCommand();
        switch (ac) {
            case "Copy":
                copy();
                break;
            case "Paste":
                paste();
                break;
            case "Cut":
                cut();
                break;
            case "delete":
                delete();
                break;
            default:
                externalActions(ac);
                break;
        }
    }
    
    private void externalActions(final String ac) {
        switch (ac) {
            case "Save":
                save();
                break;
            case "newSheet":
                reset();
                break;
            case "undo":
                tm.getSM().undo();
                break;
            case "redo":
                tm.getSM().redo();
                break;
            default:
                break;
        }
    }
    
    /**
     * Copy the selected then empty them.
     */
    private void cut() {
        copy();
        delete();
    }
    
    /**
     * Method to copy to the Clipboard the current selection of cells.
     */
    private void copy() {
        final StringBuffer sbf = new StringBuffer();
        final int[][] rowsCols = getRanges();
        for (int i = 0;i < rowsCols[0].length; i++) {
            for (int j = 0; j < rowsCols[1].length; j++) {
                sbf.append(tm.getCopyOfContent(rowsCols[0][i],rowsCols[1][j]));
                if (j < rowsCols[1].length - 1) { 
                    sbf.append("\t"); 
                }
            }
            sbf.append("\n");
        }
        final StringSelection stsel  = new StringSelection(sbf.toString());
        system = Toolkit.getDefaultToolkit().getSystemClipboard();
        system.setContents(stsel,null);
    }
    
    /**
     * Method to Delete all the contents in the cells that are currently selected.
     */
    private void delete() {
        sm.add();
        final int[][] rowsCols = getRanges();
        for (int i = 0; i < rowsCols[0].length; i++) {
            for (int j = 0; j < rowsCols[1].length; j++) {
                sm.setParse(rowsCols[0][i],rowsCols[1][j],"");
            }
        }
    }
    
    private int[][] getRanges() {
        int[][] res = new int[2][];
        res[0] = table.getSelectedRows();
        res[1] = table.getSelectedColumns();
        return res;
    }
    
    /**
     * Method to paste From the clipboard cells
     * copied within the same software or even from excel.
     */
    private void paste() {
        final int startRow = table.getSelectedRows()[0];
        final int startCol = table.getSelectedColumns()[0];
        String value = "";
        try {
            final String trString = (String)(system.getContents(this).getTransferData(
                DataFlavor.stringFlavor));
            final StringTokenizer st1 = new StringTokenizer(trString,"\n");
            for (int i = 0; st1.hasMoreTokens(); i++) {
                final String rowstring = st1.nextToken();
                final StringTokenizer st2 = new StringTokenizer(rowstring,"\t");
                for (int j = 0; st2.hasMoreTokens(); j++) {
                    value = (String)st2.nextToken();
                    sm.setParse(startRow + i, startCol + j, value);
                }
            }
            sm.add();
        } catch (UnsupportedFlavorException exception) {
            System.out.println(exception.getMessage());
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    
    
    
    /**
     * Method that opens a window for selecting the file to load.
     */
    public void load() {
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        try {
            final int result = fileChooser.showOpenDialog(new JFrame());
            if (result == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = fileChooser.getSelectedFile();
                final String filePath = selectedFile.getAbsolutePath();
                tm.path = filePath;
                tm.getSM().loadSheet(filePath,100,50);
            }    
        } catch (IOException exception) { tableObj.getIB().updateText(exception.getMessage()); }
    }
    
    /**
     * Method that opens a windows for selecting the path where to save the current file.
     */
    public void save() { 
        try {
            if (!"".equals(tm.path)) {
                tm.getSM().setPath(tm.path);
                tm.getSM().saveSheet();
            } else {
                final JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
                final int result = fileChooser.showOpenDialog(new JFrame());
                if (result == JFileChooser.APPROVE_OPTION) {
                    final File selectedFile = fileChooser.getSelectedFile();
                    final String filePath = selectedFile.getAbsolutePath();
                    tm.path = filePath;
                    tm.getSM().setPath(filePath);
                    tm.getSM().saveSheet();
                }
            }
        } catch (IOException exception) { tableObj.getIB().updateText(exception.getMessage()); }
    }
    
    /**
     * Method to reset the current workspace.
     */
    public void reset() {
        tm.getSM().resetSheetModel(100,50);
        tm.path = "";
    }
}
