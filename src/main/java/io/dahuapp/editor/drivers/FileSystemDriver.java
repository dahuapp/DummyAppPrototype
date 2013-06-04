/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dahuapp.editor.drivers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author jeremy
 */
public class FileSystemDriver {
    
    /*
     * TODO : onload and onstop methods
     */

    
    
    
    

    /*
     returns 1 if dir created
     */
    public static boolean createDir(String name) {
        File dir = new File(name);
        return dir.mkdir();
    }

    /*
     * writes the new screen "image" in the project directory
     * false if image can't be written
     * projectDir is the directory of the project, where images and JSon are
     * stored
     */
    public static boolean writeImage(BufferedImage image, String projectDir)
            throws IOException {
        try {
            File dirFile = new File(projectDir);
            int count = 0;

            FilenameFilter png = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.matches(".*\\.png$");
                }
            };

            count = dirFile.listFiles(png).length + 1;

            return ImageIO.write(image, "png", new File(projectDir + "/screen" + count +".png"));

        } catch (IOException e) {
            return false;
        }
    }
}
