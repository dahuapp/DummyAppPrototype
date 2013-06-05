package io.dahuapp.editor.drivers;

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
    
    private JSObject listener = null;
    
    public void addKeyListener(JSObject listener) {
        this.listener = listener;
    }
    
    public void removeKeyListener() {
        this.listener = null;
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
                if (listener != null) {
                    switch (nke.getKeyCode()) {
                        case NativeKeyEvent.VK_F8:
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    listener.call("notify", "capture");
                                }
                            });
                            break;
                        case NativeKeyEvent.VK_ESCAPE:
                            listener.call("notify", "escape");
                            break;
                    }
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
