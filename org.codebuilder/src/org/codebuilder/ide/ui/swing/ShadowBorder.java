package org.codebuilder.ide.ui.swing;

import javax.swing.border.*;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.*;
import java.awt.*;
import com.sun.java.swing.plaf.windows.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ShadowBorder implements Border{

    private int shadow;
    private Component component = null;

    public ShadowBorder() {
        shadow = 5;
    }


    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int
width,
                            int height) {

        component = c;

        //--------------------
        // Draw the shadow
        //--------------------
        g.setColor(UIManager.getColor("controlShadow"));
        g.fillRect(x+shadow,y+height-shadow,width-shadow,shadow);
        g.fillRect(x+width-shadow,y+shadow,shadow,height-shadow);


        //--------------------
        // Fill the little boxes
        // in the corners with the
        // parent background color
        //--------------------

        g.setColor(c.getParent().getBackground());
        g.fillRect(x,height-shadow+y,shadow,shadow);
        g.fillRect(width-shadow+x, y, shadow, shadow);

        //--------------------
        // Draw the lines
        //--------------------

        g.setColor(Color.GRAY);
        g.drawRect(x,y,width-shadow,height-shadow);

    }

    public Insets getBorderInsets(Component c) {
        return new Insets(shadow,shadow,shadow,shadow);
    }

    public Border getSpacedBorder(){
        return new CompoundBorder(new LineBorder(new
JFrame().getBackground(),5), this);
    }

    public static JComponent createComponent(){
        //JLabel component = new JLabel("Panel");
        JTree component = ComponentFactory.createJTree();
        component.setBorder(new ShadowBorder().getSpacedBorder());
        return component;
    }
}
