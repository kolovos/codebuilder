package org.codebuilder.ide.ui.swing;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class JComponentUtilities {
  public JComponentUtilities() {
  }

  public static void updateUI(JComponent component){
    final JComponent c = component;
    SwingUtilities.invokeLater(new Runnable(){
    public void run(){
      c.updateUI();
    }
    });
  }

  public static java.util.List getAllComponents(Container c){
    ArrayList list = new ArrayList();
    for (int i=0;i<c.getComponentCount();i++){
      list.add(c.getComponent(i));
      if (c.getComponent(i) instanceof Container){
        list.addAll(getAllComponents((Container)c.getComponent(i)));
      }
    }
    return list;
  }
}
