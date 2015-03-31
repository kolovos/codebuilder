package org.codebuilder.ide.ui.actions.help;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.actions.project.CloseProjectAction;
import org.codebuilder.ide.ui.dialogs.SplashScreen;
import org.codebuilder.ide.ui.dialogs.AboutDialog;

public class AboutAction
    extends IDEAbstractAction {

  protected static AboutAction instance;

  /**
   * Initialize the action
   */
  private AboutAction() {
    super();
    putValue(Action.NAME, "About CodeBuilder Studio");
    putValue(Action.SHORT_DESCRIPTION, "About CodeBuilder Studio");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.About);
  }

  /**
   * Return the one and only instance
   * of the action
   * @return NewTemplateAction
   */
  public static AboutAction getInstance() {
    if (instance == null) {
      synchronized (AboutAction.class) {
        if (instance == null) {
          instance = new AboutAction();
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
    AboutDialog aboutDialog = new AboutDialog();
    aboutDialog.showDialog();
    return true;
  }

}
