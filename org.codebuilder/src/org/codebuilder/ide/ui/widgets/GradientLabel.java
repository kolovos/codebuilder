package org.codebuilder.ide.ui.widgets;

import javax.swing.JLabel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.*;
import org.codebuilder.ide.ui.resources.*;
import java.awt.*;

public class GradientLabel
    extends JLabel {

  public GradientLabel(String text) {
    super(text);
  }

  public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    Rectangle rec = this.getVisibleRect();
    //Color endColor = SystemColor.inactiveCaption;
    Color endColor = SystemColor.control;

    if (this.getParent() != null) {
      endColor = this.getParent().getBackground();
    }
    Color startColor = SystemColor.activeCaption;
    Color medianColor = new Color(
        (startColor.getRed() + endColor.getRed()) / 2,
        (startColor.getGreen() + endColor.getGreen()) / 2,
        (startColor.getBlue() + endColor.getBlue()) / 2);

    medianColor = SystemColor.inactiveCaption;
    medianColor = SystemColor.activeCaption;
      GradientPaint gp = new GradientPaint(0, 0, medianColor,
                                         rec.width,
                                         rec.height, medianColor);
    g2.setPaint(gp);
    g2.fillRect(rec.x, rec.y, rec.width, rec.height);
    g2.setColor(Color.WHITE);

    //g2.setColor(Color.BLACK);
    //g2.drawLine(this.getWidth()-4,0,this.getWidth()-4,rec.height);
    //g2.drawLine(0,0, rec.width,0);
    //g2.drawLine(rec.width, rec.y, rec.width, rec.height);
    super.paintComponent(g);
    g2.setFont(new Font("Tahoma", 1, 11));
    g2.drawString("_ o x", 0, 120);
    /*
         Graphics2D g2 = (Graphics2D) g;
         GradientPaint gp = new GradientPaint(45.0F,45.0F, SystemColor.activeCaption, 70.0F,
                                         70.0F,new Color(166,202,240));
         g2.setPaint(gp);
         Rectangle rec = this.getVisibleRect();
         g2.fillRect(rec.x, rec.y, rec.width, rec.height);
         g2.setColor(Color.WHITE);
         g2.setPaintMode();
         g2.drawString(getText(),3, 13);
         getIcon().paintIcon(this,g,2,2);
         paintChildren(g2);
     */
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JLabel label = new GradientLabel("Hello");
    label.setForeground(Color.WHITE);
    label.setIcon(IconFactory.Model);
    frame.getContentPane().add(label);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    frame.setBounds(100, 100, 500, 500);
    frame.setVisible(true);
  }
}
