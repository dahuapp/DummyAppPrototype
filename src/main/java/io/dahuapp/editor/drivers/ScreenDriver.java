/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package io.dahuapp.editor.drivers;

import java.awt.AWTException;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javafx.scene.image.ImageView;

/**
 *
 * @author jeremy
 */
public class ScreenDriver {
    
// on retourne l'image ? 
    public static BufferedImage takeScreen(){
    
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();

        java.awt.Robot robot = null;
        try {
            robot = new java.awt.Robot(gs[gs.length-1]);
        } catch (AWTException e) {
            System.out.println("Failed to create screenshot: "+e.getMessage());
            System.exit(-1);
        }
        DisplayMode mode = gs[0].getDisplayMode();
        Rectangle bounds = new Rectangle(0, 0, mode.getWidth(), mode.getHeight());
        return robot.createScreenCapture(bounds);
    }
    
    
}
