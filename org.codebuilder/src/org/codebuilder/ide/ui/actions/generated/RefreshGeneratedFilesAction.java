package org.codebuilder.ide.ui.actions.generated;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.feedback.FeedbackManager;

public class RefreshGeneratedFilesAction
    extends IDEAbstractAction{

  protected static RefreshGeneratedFilesAction instance;

  /**
   * Initialize the action
   */
  private RefreshGeneratedFilesAction() {
    super();
    putValue(Action.NAME, "Refresh generated files tree");
    putValue(Action.SHORT_DESCRIPTION,"Refreshes the generated files tree");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Refresh);
    //putValue(Action.MNEMONIC_KEY,new Integer('X'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return ClearDebugLogAction
   */
  public static RefreshGeneratedFilesAction getInstance(){
    if (instance == null){
      synchronized(RefreshGeneratedFilesAction.class){
        if (instance == null)
          instance = new RefreshGeneratedFilesAction();
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
    return true;
  }

}
