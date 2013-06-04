package io.dahuapp.editor.drivers;

/**
 * Driver interface.
 * @author barraq
 */
public interface Driver {
    
    /**
     * Loads the driver (must be called before any operations
     * with this driver).
     */
    public void onLoad();
    
    /**
     * Unloads the driver (must be called when the driver is not
     * used anymore).
     */
    public void onStop();
}
