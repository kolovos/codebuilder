package org.codebuilder.ide.ui.actions.console;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;

public class SaveConsoleAction extends IDEAbstractAction{

  protected static SaveConsoleAction instance;

  /**
   * Initialize the action
   */
  private SaveConsoleAction() {
    super();
    putValue(Action.NAME, "Save console text");
    putValue(Action.SHORT_DESCRIPTION,"Saves the console text into a file");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Logs.Save());
  }

  /**
   * Return the one and only instance
   * of the action
   * @return SaveDebugLogAction
   */
  public static SaveConsoleAction getInstance(){
    if (instance == null){
      synchronized(SaveConsoleAction.class){
        if (instance == null)
          instance = new SaveConsoleAction();
      }
    }
    return instance;
  }

  /**
   * Performs the action
   * @param Object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object){
    return false;
  }

}
