package org.codebuilder.ide.ui.actions.console;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.feedback.FeedbackManager;

public class ClearConsoleAction extends IDEAbstractAction{

  protected static ClearConsoleAction instance;

  /**
   * Initialize the action
   */
  private ClearConsoleAction() {
    super();
    putValue(Action.NAME, "Clear console");
    putValue(Action.SHORT_DESCRIPTION,"Clears the console");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON, IconFactory.getIconFor("clear"));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return ClearMessagesAction
   */
  public static ClearConsoleAction getInstance(){
    if (instance == null){
      synchronized(ClearConsoleAction.class){
        if (instance == null)
          instance = new ClearConsoleAction();
      }
    }
    return instance;
  }

  /**
   * Performs the action
   * @param object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object){
    FeedbackManager.getInstance().clearMessages();
    return true;
  }

}
