package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Class for rendering the ColumnHeader.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class ColumnHeaderRenderer extends DefaultTableCellRenderer
{
    /**
     * Constructor for the ColumnHeaderRenderer.
     */
    public ColumnHeaderRenderer() {
        super();
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(LEFT);
        setVerticalAlignment(BOTTOM);
        setOpaque(false);
    }
    
    /**
     * Method that retrieves the actual component.
     * @param table the JTable object
     * @param value the value
     * @param isSelected true if the column is selected false otherwise
     * @param hasFocus if the element is seen on the window
     * @param row the row number
     * @param column the columnIndex
     * @return the Graphical rendered Component
     */
    public Component getTableCellRendererComponent(
        final JTable table,
        final Object value,
        final boolean isSelected,
        final boolean hasFocus,
        final int row, 
        final int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (table != null) {
            final JTableHeader header = table.getTableHeader();
            if (header != null) {
                setForeground(header.getForeground());
                setBackground(header.getBackground());
                setFont(header.getFont());
            }
        }

        final int[] selectedColumn = table.getSelectedColumns();
        for (final int i : selectedColumn) {
            if (i == column) {
                setFont(getFont().deriveFont(Font.BOLD));
                setBackground(new Color(202,231,193));
                setOpaque(true);
            } 
        }
        
        setText((value == null) ? "" : value.toString());
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        return this;
    }
}

