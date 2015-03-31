package org.codebuilder.ide.ui.actions.tools;

import org.codebuilder.ide.ui.actions.*;
import javax.swing.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.dialogs.tools.*;

public class ConfigureEditorsAction
    extends IDEAbstractAction{

  private static ConfigureEditorsAction instance = new ConfigureEditorsAction();

  public static ConfigureEditorsAction getInstance(){
    return instance;
  }

  private ConfigureEditorsAction() {
    super();
    putValue(Action.NAME, "Configure Editors...");
    putValue(Action.SHORT_DESCRIPTION, "Configures the available editors");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Editor.Configure());
  }

  /**
   * perform
   *
   * @param object Object
   * @return boolean
   */
  public boolean perform(Object object) {
    ConfigureEditorsDialog d = new ConfigureEditorsDialog();
    d.showDialog();
    return true;
  }

}
