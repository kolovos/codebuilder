package org.codebuilder.ide.ui.actions.edit;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class SelectAllAction extends AbstractEditAction{

  protected static SelectAllAction instance;

  /**
   * Initialize the action
   */
  private SelectAllAction() {
    super();
    putValue(Action.NAME, "Select All           ");
    putValue(Action.SHORT_DESCRIPTION,"Selects all the contents of the active editor");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Empty);
    putValue(Action.MNEMONIC_KEY,new Integer('C'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));

  }

  /**
   * Return the one and only instance
   * of the action
   * @return SelectAllAction
   */
  public static SelectAllAction getInstance(){
    if (instance == null){
      synchronized(SelectAllAction.class){
        if (instance == null)
          instance = new SelectAllAction();
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

    editor.selectAll();
    return true;
  }

}
