package org.codebuilder.ide.ui.actions.edit;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class RedoAction extends AbstractEditAction{

  protected static RedoAction instance;

  /**
   * Initialize the action
   */
  private RedoAction() {
    super();
    putValue(Action.NAME, "Redo");
    putValue(Action.SHORT_DESCRIPTION,"Redoes the last undone action on the document");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Redo);
    putValue(Action.MNEMONIC_KEY,new Integer('R'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));

  }

  /**
   * Return the one and only instance
   * of the action
   * @return RedoAction
   */
  public static RedoAction getInstance(){
    if (instance == null){
      synchronized(RedoAction.class){
        if (instance == null)
          instance = new RedoAction();
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
    IEditor editor = getCurrentEditor();
    if (editor == null) return false;

    //------------------
    // Since the editor
    // is not null, proceed
    // with the action
    //------------------

    editor.redo();
    return true;
  }

}
