package io.dahuapp.editor.drivers;

import javafx.scene.text.Text;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author mathieu
 */
public class DriverKeyboard {

    public static void listnerKeyboard() {
        final Text info = new Text();
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        //Construct the example object and initialze native hook.
        GlobalScreen.getInstance().addNativeKeyListener(new NativeKeyListener() {
            public void nativeKeyPressed(NativeKeyEvent e) {
                info.setText(NativeKeyEvent.getKeyText(e.getKeyCode()));

                switch (e.getKeyCode()) {

                    case NativeKeyEvent.VK_F8:
                        DriverScreen.takeScreen();
                        break;

                    case NativeKeyEvent.VK_ESCAPE:
                        GlobalScreen.unregisterNativeHook();
                        break;

                }
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nke) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void nativeKeyTyped(NativeKeyEvent nke) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
}
