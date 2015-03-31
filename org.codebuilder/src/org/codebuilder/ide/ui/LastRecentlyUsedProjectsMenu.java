package org.codebuilder.ide.ui;

import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import org.codebuilder.ide.ui.actions.project.*;
import org.codebuilder.ide.ui.widgets.*;
import org.codebuilder.ide.ui.resources.*;

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
public class LastRecentlyUsedProjectsMenu extends JCustomizedMenu implements MenuListener{
  public LastRecentlyUsedProjectsMenu() {
    this.addMenuListener(this);
    this.setText("Recent Projects ...");
    this.setBorder(JCustomizedMenu.MENU_ITEM_BORDER);
    this.setIcon(IconFactory.Empty);
  }

  public void menuCanceled(MenuEvent e) {

  }

  public void menuDeselected(MenuEvent e) {

  }

  public void menuSelected(MenuEvent e) {
    IDE ide = IDE.getInstance();
    this.removeAll();
    ListIterator li = ide.getLastRecentlyUsedProjects().listIterator();
    while(li.hasNext()){
      this.add(new OpenProjectAction(li.next().toString()));
    }
  }
}
