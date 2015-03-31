package org.codebuilder.ide.ui.widgets;

import javax.swing.border.*;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TabsBorder implements Border{
    public TabsBorder() {

    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width,
                            int height) {
        g.setColor(SystemColor.control);
        g.drawLine(x+1,y,x+width-1,y);
        g.setColor(SystemColor.controlShadow);
        g.drawLine(x+width-2,y,x+width-2,y);
        g.setColor(SystemColor.controlDkShadow);
        g.drawLine(x+width-1,y,x+width-1,y);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
}
