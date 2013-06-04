package io.dahuapp.editor.drivers;

/**
 *
 * @author barraq
 */
public class DummyDriver implements Driver {
    public void onLoad() {
        System.out.println("".getClass().getName()+" loaded");
    }
    
    public void onStop() {
    
    }
}
