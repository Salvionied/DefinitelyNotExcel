package gui;

import java.util.ArrayList;
import javax.swing.JTabbedPane;


/**
 * Manages the various Open SpreadSheets.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class TabManager extends JTabbedPane
{
    private ArrayList<SpreadSheet> sptabs;
    
    /**
     * Constructor for the TabManager.
     */
    public TabManager() {
        super();
        sptabs = new ArrayList<SpreadSheet>();
        add("",null);
        setTabComponentAt(0, new InsertTabComponent(this));
        final SpreadSheet panel0 = new SpreadSheet();
        sptabs.add(panel0);
        add("SpreadSheet #" + getTabCount(),panel0);
        setSelectedIndex(1);
    }
    
    /**
     * Add a New Spreadsheet to the tabs.
     */
    public void addSpTab() {
        final SpreadSheet sp = new SpreadSheet();
        add("SpreadSheet #" + getTabCount(),sp);
        sptabs.add(sp);
        setTabComponentAt(getTabCount() - 1,new TabComponent(this));
    }
    
    @Override
    public void remove(final int index) {
        removeTabAt(index);
        sptabs.remove(index - 1);
    }
    
    /**
     * Get the selected index' SpreadSheet.
     * @return SpreadSheet the currently open SpreadSheet.
     */
    public SpreadSheet getSelectedSp() {
        if (getSelectedIndex() != 0) {
            return sptabs.get(getSelectedIndex() - 1);
        } else {
            return sptabs.get(0);
        }
    }
}
