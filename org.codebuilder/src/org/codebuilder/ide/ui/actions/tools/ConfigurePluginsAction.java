package org.codebuilder.ide.ui.actions.tools;

import org.codebuilder.ide.ui.actions.*;
import javax.swing.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.dialogs.tools.*;

public class ConfigurePluginsAction
    extends IDEAbstractAction{

  private static ConfigurePluginsAction instance = new ConfigurePluginsAction();

  public static ConfigurePluginsAction getInstance(){
    return instance;
  }

  private ConfigurePluginsAction() {
    super();
    putValue(Action.NAME, "Configure Plug-ins...");
    putValue(Action.SHORT_DESCRIPTION, "Configures the available plugins");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Plugin.Configure());
  }

  /**
   * perform
   *
   * @param object Object
   * @return boolean
   */
  public boolean perform(Object object) {
    ConfigurePluginsDialog d = new ConfigurePluginsDialog();
    d.showDialog();
    return true;
  }

}
