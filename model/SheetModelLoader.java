package model;

import model.nodes.Node;
import model.nodes.StringNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Loader Class for The SheetModel object.
 *
 * @author Edoardo Salvioni
 * @author Anton Tanev
 * @version 0.0.1
 */
public class SheetModelLoader
{
    private String filename;
    private final SheetModel sm;
    
    /**
     * Constructor for the SheetModelLoader.
     * 
     * @param sm the SheetModel the loader works upon
     */
    public SheetModelLoader(final SheetModel sm) {
        this.sm = sm;
        filename = "";
    }
    
    /**
     * Method to Load the SheetModel From a file.
     * 
     * @param filename of the wanna be loaded file
     * @param rows the number of rows
     * @param cols the number of columns
     * @throws IOException if file doesnt exist or has the wrong format.
     * 
     */
    public void load(
        final String filename, 
        final int rows, 
        final int cols) throws IOException {
        this.filename = filename;
        if (filename.endsWith("/") || "".equals(filename)) {
            throw new SheetModelException("Missing Name Of File");
        }
        if (!filename.endsWith(".csv")) { 
            throw new SheetModelException("Invalid File Format"); 
        }
        final ArrayList<String> lines = new ArrayList<String>();
        String line = "";
        final BufferedReader br = new BufferedReader(new FileReader(filename));
        line = br.readLine();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        
        }
        
        generateTableFromFile(lines, rows, cols);
    }
    
    private void generateTableFromFile(
        final ArrayList<String> lines, 
        final int rows,
        final int cols) {
        sm.resetSheetModel(rows,cols);
        for (int i = 0; i < lines.size(); i++) {
            final String[] line = lines.get(i).split(",");
            for (int j = 0; j < line.length; j ++) {
                sm.setParse(i,j,line[j]);
            }
        }
        sm.add();
        sm.fireSheetModelChanged();
    }
    
    /**
     * Set current filename.
     * 
     * @param name of the file 
     */
    public void setFileName(final String name) {
        this.filename = System.getProperty("user.dir") + "/" + name;
    }
    
    /**
     * Set path of the file.
     * 
     * @param path the path of the file
     */
    public void setPath(final String path) {
        this.filename = path;
    }
    
    /**
     * Save the Current Workspace on a csv file.
     * 
     * @throws IOException if no path was specified for saving.
     */
    public void save() throws IOException {
        final String save = generateSaveData();
        if (filename.endsWith("/") || "".equals(filename)) {
            throw new SheetModelException("No previous FilePath Saved.");
        }
        if (!filename.endsWith(".csv")) {
            filename += ".csv";
        }
        final FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(save);
        fileWriter.close();
    }
    
    /**
     * Helper method to retrieve the Save data.
     * 
     * @return String containing the data to be saved
     */
    public String generateSaveData() {
        String saveData = "";
        for (int i = 0 ; i < sm.getHeight(); i++ ) {
            for (int j = 0; j < sm.getWidth(i); j++) {
                if (j != sm.getWidth(i) - 1) {
                    if (sm.get(i,j) != null) {
                        final Node data = sm.get(i,j);
                        saveData += saveDataGenHelper(data);
                    } else { saveData += ","; }
                } else {
                    if (sm.get(i,j) != null) {
                        final Node data = sm.get(i,j);
                        saveData += saveDataGenHelper(data);
                    } else { saveData += ""; }
                }
            }
            saveData += "\n";
        }
        return saveData;
    }
    
    private String saveDataGenHelper(final Node data) {
        if (data instanceof StringNode) {
            return data.toString() + ",";
        } else {
            return "=" + data.toString() + ",";
        }
    }
}
