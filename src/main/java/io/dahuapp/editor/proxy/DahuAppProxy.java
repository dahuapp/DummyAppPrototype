package io.dahuapp.editor.proxy;

import javafx.application.Platform;
import javafx.scene.web.WebEngine;

/**
 *
 * @author barraq
 */
public class DahuAppProxy implements Proxy {
    
    public DahuAppDriverProxy drivers;
    
    public DahuAppProxy(WebEngine webEngine) {
        drivers = new DahuAppDriverProxy(webEngine);
    }
    
    @Override
    public void onLoad() {
        drivers.onLoad();
    }

    public String sayHello(String name) {
        return "Hello "+name;
    }

    public void printHello(String name) {
        System.out.println(sayHello(name));
    }

    public void exit() {
        Platform.exit();
    }
    
    @Override
    public void onStop() {
        drivers.onStop();
    }
}
