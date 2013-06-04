package io.dahuapp.editor.app.proxy;

import io.dahuapp.editor.drivers.*;

/**
 *
 * @author barraq
 */
public class DahuAppDriverProxy implements Proxy {
    
    public DummyDriver dummy;
    public KeyboardDriver keyboard;
    
    public DahuAppDriverProxy() {
        // init all drivers
        dummy = new DummyDriver();
        keyboard = new KeyboardDriver();
        
        // load all drivers
        dummy.onLoad();
        keyboard.onLoad();
    }
    
    @Override
    public void onLoad() {
        // ignore
    }
    
    @Override
    public void onStop() {
        // ignore
    }
}
