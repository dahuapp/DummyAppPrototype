
package io.dahuapp.editor.drivers;

import java.awt.MouseInfo;
import java.awt.Point;

/**
 *
 * @author mathieu
 */
public class MouseDriver {
    
    public static Point getMouse() {
        return MouseInfo.getPointerInfo().getLocation();
    }
}
