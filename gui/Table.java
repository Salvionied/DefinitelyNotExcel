package gui;

import model.nodes.Node;
import model.nodes.Type;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Class for creating the Panel containing The spreadsheet
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class Table 
{
    private JPanel mainTable;
    private TableModel dm;
    private JTable jt;
    private InputBar ib;
    private int lastcol;
    private int lastrow;
    private UsabilityHelper usHelper;
    
    /**
     * Constructor of Table Object.
     * 
     * @param dm TableModel Object.
     */
    public Table(final TableModel dm) {
        //Initialize Variables
        lastcol = 0;
        lastrow = 0;
        this.dm = dm;
        //Create JPanel object
        mainTable = new JPanel();
        //Create JTable object upon The TableModel
        jt = initializeTable();
        //CopyPasteManager
        usHelper = new UsabilityHelper(jt,this); 
        //Create the InputBar
        ib = new InputBar(dm);
        //Create ScrollBars for the JTable Objec
        final JScrollPane sp = new JScrollPane(jt);
        sp.setRowHeaderView(new RowHeader(jt));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //Add the components to the main Panel
        mainTable.setLayout(new BoxLayout(mainTable, BoxLayout.PAGE_AXIS));
        mainTable.add(sp);
        //Call the event Handler Method
        selectionUpdater();
        //selectionInserter();
    }
    

    /**
     * Method to initialize the JTable object.
     * @return JTable object
     */
    private JTable initializeTable() {
        final JTable table = new JTable(dm);
        table.setSelectionBackground(new Color(202,231,193));
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.setTableHeader(new ColumnHeader(table));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new ColumnHeaderRenderer());
        table.setCellSelectionEnabled(true);
        table.setAutoResizeMode(jt.AUTO_RESIZE_OFF);
        table.setBounds(10,20,900,900);
        return table;
    }
    
    /**
     * Event Handler method to keep the content of the inputbar updated.
     */
    public void selectionUpdater() {
        jt.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                updateIBContent();
            }
        });
        jt.addKeyListener(new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                updateIBContent();
            }
        });
        
    }
    
    /**
     * get the TableModelObject.
     * @return tablemodel object.
     */
    public TableModel getTm() {
        return this.dm;
    }
    
    /**
     * Get the JPanel object of the table.
     * 
     * @return JPanel object
     */
    public JPanel getTable() {
        //return the final panel
        return mainTable;
    }
    
    /**
     * Get the InputBar Object.
     * 
     * @return InputBar Object
     */    
    public InputBar getIB() {
        return ib;
    }
    
    /**
     * Get the UsabilityHelperObject.
     * @return UsabilityHelper Object
     */
    public UsabilityHelper getUsHelper() {
        return usHelper;
    }
    
    /**
     * Method to Update the Content of the InputBar.
     */
    public void updateIBContent() {
        this.lastrow = jt.getSelectedRow();
        this.lastcol = jt.getSelectedColumn();
        String content = "";
        if (dm.get(lastrow,lastcol) != null) {
            final Node n = dm.get(lastrow, lastcol);
            if (n.getType() == Type.STRING) {
                content = n.getValue();
            } else { content = "=" + dm.get(lastrow,lastcol).toString(); }
        }
        ib.updateText(lastrow,lastcol,content);
    }
}