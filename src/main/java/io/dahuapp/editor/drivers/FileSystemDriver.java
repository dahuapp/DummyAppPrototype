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
public class FileSystemDriver implements Driver{
    
    /*
     returns 1 if dir created
     */
    public static boolean createDir(String name) {
        File dir = new File(name);
        return dir.mkdir();
    }

    /**
     * Writes the new screen image in the project directory.
     * @param image The image to write.
     * @param projectDir The project directory (name).
     * @return The name of the image created (or null if fail).
     */
    public static String writeImage(BufferedImage image, String projectDir) {
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

    @Override
    public void onLoad() {
    }
    
    @Override
    public void onStop() {
    }
}
