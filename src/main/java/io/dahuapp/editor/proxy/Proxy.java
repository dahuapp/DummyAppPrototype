package io.dahuapp.editor.proxy;

/**
 *
 * @author barraq
 */
public interface Proxy {
    
    /**
     * Called when proxy is loaded.
     */
    public void onLoad();
    
    /**
     * Called when proxy is stopped.
     */
    public void onStop();
    
}
