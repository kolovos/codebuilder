package org.codebuilder.ide.ui.actions.tools;

import org.codebuilder.ide.ui.actions.*;
import javax.swing.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.dialogs.tools.*;

public class ConfigureToolsAction extends IDEAbstractAction{

  private static ConfigureToolsAction instance = new ConfigureToolsAction();

  public static ConfigureToolsAction getInstance(){
    return instance;
  }

  private ConfigureToolsAction() {
    super();
    putValue(Action.NAME, "Configure Tools...");
    putValue(Action.SHORT_DESCRIPTION, "Configures the available tools");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Tool.Configure());
  }

  /**
   * perform
   *
   * @param object Object
   * @return boolean
   */
  public boolean perform(Object object) {
    ConfigureToolsDialog d = new ConfigureToolsDialog();
    d.showDialog();
    return true;
  }

}
