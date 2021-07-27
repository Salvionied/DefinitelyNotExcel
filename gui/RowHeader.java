package gui;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * Create the Header for To display the numbers representing the rows.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class RowHeader extends JTable
{
    private JTable main;
    
    /**
     * Constructor for objects of class RowHeader.
     * @param table the JTable object this works upon.
     */
    public RowHeader(final JTable table)
    {
        super();
        main = table;
        setFocusable(false);
        setAutoCreateColumnsFromModel(false);
        setSelectionModel(main.getSelectionModel());
        
        final TableColumn column = new TableColumn();
        column.setHeaderValue(" ");
        addColumn(column);
        column.setCellRenderer(new RowNumberRenderer());
        getColumnModel().getColumn(0).setPreferredWidth(50);
        setPreferredScrollableViewportSize(getPreferredSize());
    }

    @Override
    public int getRowCount() {
        return main.getRowCount();
    }
    
    @Override
    public Object getValueAt(final int row,final int column) {
        return Integer.toString(row);
    }
    
    @Override
    public boolean isCellEditable(final int row,final int column) {
        return false;
    }
    
    @Override
    public void setValueAt(final Object value,final int row,final int column) {
        //Useless method
    }
    
    
}
