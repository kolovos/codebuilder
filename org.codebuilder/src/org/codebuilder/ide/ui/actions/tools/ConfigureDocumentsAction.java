package org.codebuilder.ide.ui.actions.tools;

import org.codebuilder.ide.ui.actions.*;
import javax.swing.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.dialogs.tools.*;

public class ConfigureDocumentsAction
    extends IDEAbstractAction{

  private static ConfigureDocumentsAction instance = new ConfigureDocumentsAction();

  public static ConfigureDocumentsAction getInstance(){
    return instance;
  }

  private ConfigureDocumentsAction() {
    super();
    putValue(Action.NAME, "Configure Documents...");
    putValue(Action.SHORT_DESCRIPTION, "Configures CodeBuilder documents");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Document.Configure());
  }

  /**
   * perform
   *
   * @param object Object
   * @return boolean
   */
  public boolean perform(Object object) {
    ConfigureDocumentsDialog d = new ConfigureDocumentsDialog();
    d.showDialog();
    return true;
  }

}
