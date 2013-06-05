package io.dahuapp.editor.drivers;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 12772dcafd4de64ea45138613543d729e6ea38df
import javafx.application.Platform;
import netscape.javascript.JSObject;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * Driver to get keyboard events.
 * It does 2 actions :
 * <ul>
 * <li>takes a screenshot when a specific key is pressed</li>
 * <li>stops the capture mode when an other specific key is pressed</li>
 * </ul>
 * @author mathieu
 */
public class KeyboardDriver implements Driver {
    
    private ArrayList<JSObject> listeners = new ArrayList<>();
    
    public void addKeyListener(JSObject listener) {
        listeners.add(listener);
    }
    
    public void removeKeyListener(JSObject listener) {
        listeners.remove(listener);
    }
    
    @Override
    public void onLoad() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        
        // Construct the example object and initialze native hook.
        GlobalScreen.getInstance().addNativeKeyListener(new NativeKeyListener() {
            @Override
            public void nativeKeyReleased(NativeKeyEvent nke) {
                switch (nke.getKeyCode()) {
                    case NativeKeyEvent.VK_F8:
                        System.out.println("je suis la");
                        for (JSObject l : listeners) {
                            if (l != null) {
                                l.call("notifyCapture");
                            }
                        }
                        break;
                    case NativeKeyEvent.VK_ESCAPE:
                        System.out.println("je suis ici");
                        for (JSObject l : listeners) {
                            if (l != null) {
                                l.call("notifyEscape");
                            }
                        }
                        break;
                }
            }
            
            @Override
            public void nativeKeyTyped(NativeKeyEvent nke) {
                // nothing to do
            }
            
            @Override
            public void nativeKeyPressed(NativeKeyEvent e) {
                // nothing to do
            }
        });
        System.out.println(this.getClass() + " loaded.");
    }

    @Override
    public void onStop() {
        GlobalScreen.unregisterNativeHook();
        System.out.println(this.getClass() + " stopped.");
    }
}
