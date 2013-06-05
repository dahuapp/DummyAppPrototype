/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dahuapp.editor.drivers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javafx.stage.DirectoryChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author jeremy
 */
public class FileSystemDriver implements Driver{
    
        private File file;
        private DirectoryChooser directoryChooser = new DirectoryChooser();

    
    
    /*
     returns 1 if dir created
     */
    public boolean createDir(String name) {
        File dir = new File(name);
        return dir.mkdir();
    }

    /**
     * Writes the new screen image in the project directory.
     * @param image The image to write.
     * @param projectDir The project directory (name).
     * @return The name of the image created (or null if fail).
     */
    public String writeImage(BufferedImage image, String projectDir) {
        try {
            File dirFile = new File(projectDir);

            FilenameFilter png = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.matches(".*\\.png$");
                }
            };

            final int count = dirFile.listFiles(png).length + 1;
            
            // returns the file separator for this platform (unix or windows eg)
            final String fileSep = System.getProperty("file.separator");
            final String fileName = "screen" + count + ".png";
            final File imageFile = new File(projectDir + fileSep + fileName);
            if (ImageIO.write(image, "png", imageFile)) {
                return fileSep;
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
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

            FilenameFilter png = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.matches(".*\\.png$");
                }
            };
            int nbFiles = dirFile.listFiles(png).length ;
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
