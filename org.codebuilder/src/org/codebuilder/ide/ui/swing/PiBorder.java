package org.codebuilder.ide.ui.swing;

import javax.swing.border.*;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.*;
import javax.swing.*;

public class PiBorder implements Border{
  public PiBorder() {
  }

  /**
   * isBorderOpaque
   *
   * @return boolean
   */
  public boolean isBorderOpaque() {
    return false;
  }

  /**
   * paintBorder
   *
   * @param c Component
   * @param g Graphics
   * @param x int
   * @param y int
   * @param width int
   * @param height int
   */
  public void paintBorder(Component c, Graphics g, int x, int y, int width,
                          int height) {
    g.setColor(new Color(64,64,64));
    g.drawLine(x,y,width,y);
    g.drawLine(x,y,x,height);
    g.drawLine(width-1,y,width-1,height);
    g.setColor(Color.RED);
    g.drawLine(x,height,width,height);
  }

  /**
   * getBorderInsets
   *
   * @param c Component
   * @return Insets
   */
  public Insets getBorderInsets(Component c) {
    return new Insets(1,1,1,1);
  }

  public static void main(String[] args){
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Hello");
    label.setBorder(new PiBorder());
    frame.setBounds(100,100,500,500);
    frame.getContentPane().add(label);
    frame.setVisible(true);
  }
}
