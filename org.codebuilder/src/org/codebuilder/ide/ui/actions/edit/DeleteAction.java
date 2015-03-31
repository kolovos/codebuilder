package org.codebuilder.ide.ui.actions.edit;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class DeleteAction extends AbstractEditAction{

  protected static DeleteAction instance;

  /**
   * Initialize the action
   */
  private DeleteAction() {
    super();
    putValue(Action.NAME, "Delete");
    putValue(Action.SHORT_DESCRIPTION,"Deletes the selection");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Delete);
    putValue(Action.MNEMONIC_KEY,new Integer('D'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));

  }

  /**
   * Return the one and only instance
   * of the action
   * @return DeleteAction
   */
  public static DeleteAction getInstance(){
    if (instance == null){
      synchronized(DeleteAction.class){
        if (instance == null)
          instance = new DeleteAction();
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

    editor.delete();
    return true;
  }

}
