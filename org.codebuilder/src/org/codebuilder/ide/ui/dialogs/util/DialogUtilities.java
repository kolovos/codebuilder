package org.codebuilder.ide.ui.dialogs.util;

/*
 * @(#) DialogUtilities.java 1.0 3/5/2004
 *
 * Copyright (c) 2004 University of York
 * All rights reserved
 *
 */

import java.awt.Container;
import java.awt.Component;
import java.awt.*;
import javax.swing.*;

/**
 * Utilities relative to dialog windows
 * @author Dimitrios Kolovos
 * @version 1.0
 */

public class DialogUtilities {

  /**
   * Positions the child in the center of the parent
   * @param parent Container
   * @param child Container
   */
  public static void positionInCenter(Component parent, Component child, int height, int width){
    int left, top;
    left = parent.getBounds().x + (parent.getWidth() - width)/2;
    top = parent.getBounds().y + (parent.getHeight() - height)/2;
    child.setBounds(left,top,width,height);
  }


  public static void positionInCenterScreen(Component c, int height, int width){
    Rectangle screen = GraphicsEnvironment.getLocalGraphicsEnvironment().
              getMaximumWindowBounds();
    int left, top;
    left = (int) (screen.getWidth() - width)/2;
    top = (int) (screen.getHeight() - height)/2;
    c.setBounds(left,top,width,height);
  }
}
