package io.dahuapp.editor.app.proxy;

import javafx.application.Platform;

/**
 *
 * @author barraq
 */
public class DahuAppProxy implements Proxy {
    
    public DahuAppDriverProxy drivers;
    
    public DahuAppProxy() {
        drivers = new DahuAppDriverProxy();
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
