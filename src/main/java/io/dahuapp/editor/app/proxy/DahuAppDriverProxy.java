package io.dahuapp.editor.app.proxy;

import io.dahuapp.editor.drivers.DummyDriver;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author barraq
 */
public class DahuAppDriverProxy implements Proxy {
    public DummyDriver dummy;
    
    public DahuAppDriverProxy() {
        // init all drivers
        dummy = new DummyDriver();
        
        // load all drivers
        dummy.onLoad();
    }
    
    public void onLoad() {
        // ignore
    }
    
    public void onStop() {
        // ignore
    }
}
