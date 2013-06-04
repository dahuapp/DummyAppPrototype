package io.dahuapp.editor.drivers;

import java.awt.AWTException;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

/**
 * Driver to take a screenshot with AWT.
 * @author jeremy
 */
public class ScreenDriver implements Driver {
    
    private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private GraphicsDevice[] gs = ge.getScreenDevices();
    
    /**
     * Take a screenshot and returns the image taken.
     * @return The screenshot (full screen) image.
     */
    public BufferedImage takeScreen() {
        Robot robot = null;
        try {
            robot = new Robot(gs[gs.length - 1]);
        } catch (AWTException e) {
            System.out.println("Failed to create screenshot: "+e.getMessage());
            System.exit(-1);
        }
        DisplayMode mode = gs[0].getDisplayMode();
        Rectangle bounds = new Rectangle(0, 0, mode.getWidth(), mode.getHeight());
        if (robot == null) {
            return null;
        } else {
            return robot.createScreenCapture(bounds);
        }
    }
    
    @Override
    public void onLoad() {
        System.out.println(this.getClass() + " loaded.");
    }
    
    @Override
    public void onStop() {
        System.out.println(this.getClass() + " stopped.");
    }
}
