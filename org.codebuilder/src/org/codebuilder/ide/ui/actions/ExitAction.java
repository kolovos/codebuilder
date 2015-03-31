package org.codebuilder.ide.ui.actions;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.actions.project.CloseProjectAction;

public class ExitAction
    extends IDEAbstractAction {

  protected static ExitAction instance;

  /**
   * Initialize the action
   */
  private ExitAction() {
    super();
    putValue(Action.NAME, "Exit");
    putValue(Action.SHORT_DESCRIPTION, "Exit CodeBuilder Studio");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Empty);
  }

  /**
   * Return the one and only instance
   * of the action
   * @return NewTemplateAction
   */
  public static ExitAction getInstance() {
    if (instance == null) {
      synchronized (ExitAction.class) {
        if (instance == null) {
          instance = new ExitAction();
        }
      }
    }
    return instance;
  }

  /**
   * Performs the action
   * @param Object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {
    if (CloseProjectAction.getInstance().perform()) {
      //--------------------
      // Save the last recently
      // used projects
      //--------------------
      IDE.getInstance().getLastRecentlyUsedProjects().save();

      // ---------------------
      // Save the states of the
      // docking windows
      // ---------------------

      //IDE.getInstance().SaveDockFloatStates("config/info.dock");

      System.exit(0);
      return true;
    }
    else {
      return false;
    }

  }

  public void msgbox(String str) {
    JOptionPane.showMessageDialog(IDE.getInstance(), str, "Exit Action", 0);
  }

  public void msgbox(boolean bool) {
    msgbox(Boolean.toString(bool));
  }
}
