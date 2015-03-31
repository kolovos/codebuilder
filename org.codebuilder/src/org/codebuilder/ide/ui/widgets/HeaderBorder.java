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
public class HeaderBorder implements Border{
    public HeaderBorder() {
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width,
                            int height) {
        g.setColor(SystemColor.controlLtHighlight);
        g.drawLine(x,y,x+width,y);
        g.drawLine(x,y,x,y+height);
        g.setColor(SystemColor.controlShadow);
        g.drawLine(x+width-2, y+1, x+width-2, y + height);
        g.setColor(SystemColor.controlDkShadow);
        g.drawLine(x+width-1, y+1, x+width-1, y + height);

        /*
        g.setColor(SystemColor.control);

        g.fillRect(x+1,y+1,x+width-2,y+3);

        g.fillRect(x+1,y+height-1,x+width-2,y+height-3);

        g.fillRect(x+1,y+1,x+3,y+width-3);
        */
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(2,2,2,2);
    }
}
