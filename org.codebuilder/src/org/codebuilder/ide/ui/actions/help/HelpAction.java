package org.codebuilder.ide.ui.actions.help;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.actions.project.CloseProjectAction;
import org.codebuilder.ide.ui.dialogs.SplashScreen;
import org.codebuilder.ide.help.*;

public class HelpAction
    extends IDEAbstractAction {

  protected static HelpAction instance;

  /**
   * Initialize the action
   */
  private HelpAction() {
    super();
    putValue(Action.NAME, "CodeBuilder Studio Help");
    putValue(Action.SHORT_DESCRIPTION, "Displays the CodeBuilder Studio help");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Help);
  }

  /**
   * Return the one and only instance
   * of the action
   * @return NewTemplateAction
   */
  public static HelpAction getInstance() {
    if (instance == null) {
      synchronized (HelpAction.class) {
        if (instance == null) {
          instance = new HelpAction();
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
    HelpViewer.getInstance().showDialog();
    return true;
  }

}
