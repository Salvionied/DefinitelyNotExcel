package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 * VIsual Panel For a Single SpreadSheet Tab
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class SpreadSheet extends JPanel
{
    private final TableModel dm;
    private final Table table;
    
    /**
     * Constructor for a single SpreadSheet.
     */
    public SpreadSheet() {
        super(new BorderLayout());
        //create the table panel
        dm = new TableModel();
        // Get the menu panel
        table = new Table(dm);
        final InputBar ib = table.getIB();
        add(ib.getbar(),BorderLayout.NORTH);
        add(table.getTable(),BorderLayout.CENTER);
    }
    
    /**
     * Get the TableModel object.
     * @return TableModel object of this SpreadSheet.
     */
    public TableModel getDM() {
        return dm;
    }
    
    /**
     * Get The UsabilityHelper for the Table.
     * @return UsabilityHelper bound to this spreadsheet.
     */
    public UsabilityHelper getHelper() {
        return table.getUsHelper();
    }
    
    /**
     * Get the Table.
     * @return Table the table of this SpreadSheet.
     */
    public Table getTable() {
        return table;
    }
}
