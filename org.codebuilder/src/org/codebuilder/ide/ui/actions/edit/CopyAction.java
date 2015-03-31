package org.codebuilder.ide.ui.actions.edit;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class CopyAction extends AbstractEditAction{

  protected static CopyAction instance;

  /**
   * Initialize the action
   */
  private CopyAction() {
    super();
    putValue(Action.NAME, "Copy");
    putValue(Action.SHORT_DESCRIPTION,"Copies the selection into the clipboard");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Copy);
    putValue(Action.MNEMONIC_KEY,new Integer('C'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));

  }

  /**
   * Return the one and only instance
   * of the action
   * @return CopyAction
   */
  public static CopyAction getInstance(){
    if (instance == null){
      synchronized(CopyAction.class){
        if (instance == null)
          instance = new CopyAction();
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

    editor.copy();
    return true;
  }

}
