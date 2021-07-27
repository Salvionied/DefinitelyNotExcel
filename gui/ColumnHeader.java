package gui;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;


/**
 * ColumnHeader for the JTable object.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class ColumnHeader extends JTableHeader
{
    /**\
     * Constructor for the ColumnHeader.
     * @param table the JTable object whose columns this Header will represent.
     */
    public ColumnHeader(final JTable table) {
        super();
        setColumnModel(table.getColumnModel());
        table.getColumnModel().getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(final ListSelectionEvent e) {
                    repaint();
                }
            });
    }

    @Override
    public void columnSelectionChanged(final ListSelectionEvent e) {
        repaint();
    }

}
