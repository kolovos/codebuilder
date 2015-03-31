package org.codebuilder.ide.ui.actions.edit;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class UndoAction extends AbstractEditAction{

  protected static UndoAction instance;

  /**
   * Initialize the action
   */
  private UndoAction() {
    super();
    putValue(Action.NAME, "Undo");
    putValue(Action.SHORT_DESCRIPTION,"Undoes the last action on the document");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Undo);
    putValue(Action.MNEMONIC_KEY,new Integer('U'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));

  }

  /**
   * Return the one and only instance
   * of the action
   * @return UndoAction
   */
  public static UndoAction getInstance(){
    if (instance == null){
      synchronized(UndoAction.class){
        if (instance == null)
          instance = new UndoAction();
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

    editor.undo();
    return true;
  }

}
