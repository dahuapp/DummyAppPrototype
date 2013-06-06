/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dahuapp.editor.drivers;

import java.io.File;
import java.io.FilenameFilter;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author jeremy
 */
public class FileSystemDriver implements Driver{
    
        private File file;
        private DirectoryChooser directoryChooser = new DirectoryChooser();
    private FilenameFilter pngFilter = new FilenameFilter() {
        @Override
        public boolean accept(File dir, String name) {
            return name.matches(".*\\.png$");
        }
    };        
    
    
    /*
     returns 1 if dir created
     */
    public boolean createDir(String name) {
        File dir = new File(name);
        return dir.mkdir();
    }

    /**
     * Let the user choose the project directory.
     * @return The path of the chosen directory.
     */
    public String getDir() {               
        
        this.file = directoryChooser.showDialog(null); 
        
        return file.getAbsolutePath() + "/";
    }
    
    public String[] getFiles(String projectDir) {
        try {
            File dirFile = new File(projectDir);

            int nbFiles = dirFile.listFiles(pngFilter).length ;
            String [] listFiles = new String[nbFiles];
            for(int i = 0 ; i < nbFiles ; i++) {
                listFiles[i] = dirFile.listFiles()[i].getPath();
            }
            return listFiles;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onLoad() {
    }
    
    @Override
    public void onStop() {
    }
}
