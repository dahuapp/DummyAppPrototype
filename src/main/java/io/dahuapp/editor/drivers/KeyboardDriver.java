package io.dahuapp.editor.drivers;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSException;
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
    
    private WebEngine webEngine;
    private ArrayList<String> callbacks = new ArrayList<>();
    
    public KeyboardDriver(WebEngine webEngine) {
        this.webEngine = webEngine;
    }
    
    public void addKeyCallback(JSObject listener) throws JSException {
        final String functionName = listener.getMember("name").toString();
        switch (functionName) {
            case "undefined":
                throw new JSException("Callback function cannot be anonymous.");
            case "":
                throw new JSException("Callback function cannot be anonymous.");
            default:
                callbacks.add(functionName);
        }
    }
    
    public void removeKeyCallback(JSObject listener) throws JSException {
        final String functionName = listener.getMember("name").toString();
        switch (functionName) {
            case "undefined":
                throw new JSException("Callback function cannot be anonymous.");
            case "":
                throw new JSException("Callback function cannot be anonymous.");
            default:
                callbacks.remove(functionName);
        }
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
                        for (final String callback : callbacks) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    JSObject window = (JSObject)webEngine.executeScript("window");
                                    window.call(callback, "capture");
                                }
                            });
                        }
                        break;
                        
                    case NativeKeyEvent.VK_ESCAPE:
                        for (final String callback : callbacks) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    webEngine.executeScript(callback +
                                            "(\"escape\")");
                                }
                            });
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
