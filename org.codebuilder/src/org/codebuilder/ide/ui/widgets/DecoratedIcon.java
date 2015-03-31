package org.codebuilder.ide.ui.widgets;

import javax.swing.*;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.*;

/**
 * <p>Title: CodeBuilder</p>
 *


 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: Dimitrios Kolovos</p>
 *
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */
public class DecoratedIcon
    extends ImageIcon {

  private Icon icon;

  public DecoratedIcon(Icon icon) {
    this.icon = icon;
  }

  /**
   * Returns the height of the base icon
   * @returns the height of the icon
   */
  public int getIconHeight() {
    return 32;
  }

  /**
   * Returns the width of the icon
   * @returns the width of the base icon
   */
  public int getIconWidth() {
    return 32;
  }

  /**
   * Paints the two icons, calling each of their paintIcon methods
   * @param c the GUI component inw which to paint this icon
   * @param g the graphics object which contains the icon
   * @param x the x location of the base icon
   * @param y the y location of the base icon
   */
  public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(new Color(204,204,204));
    g.fillRect(0,0,32,32);
    g.setColor(SystemColor.activeCaption);
    g.drawRect(0,0,32,32);
    icon.paintIcon(c, g, 8,8);
    //base.paintIcon(c, g, x, y);
    //overlay.paintIcon(c, g, x+ox, y+oy);
  }

}
