package org.codebuilder.ide.ui.widgets;

import javax.swing.*;
import javax.swing.border.*;

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
public class JCustomizedMenu extends JMenu{
  public JCustomizedMenu() {
  }

  public static Border MENU_BORDER = BorderFactory.createEmptyBorder(3,3,3,3);
  public static Border MENU_ITEM_BORDER = BorderFactory.createEmptyBorder(3,3,3,3);

  public JCustomizedMenu(String title){
    super(title);
    this.setBorder(JCustomizedMenu.MENU_BORDER);
  }

  public JMenuItem add(Action action){
    JMenuItem item = new JMenuItem(action);
    return(add(item));
  }

  public JMenuItem add(JMenuItem menuItem) {
    customizeUI(menuItem);
    super.add(menuItem);
    return (menuItem);
  }

  public void customizeUI(JMenuItem item){
    item.setBorder(JCustomizedMenu.MENU_ITEM_BORDER);
  }



}
