package org.codebuilder.ide.ui.actions.edit;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class CutAction extends AbstractEditAction{

  protected static CutAction instance;

  /**
   * Initialize the action
   */
  private CutAction() {
    super();
    putValue(Action.NAME, "Cut");
    putValue(Action.SHORT_DESCRIPTION,"Cuts the selection and puts it in the clipboard");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Cut);
    putValue(Action.MNEMONIC_KEY,new Integer('u'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return CutAction
   */
  public static CutAction getInstance(){
    if (instance == null){
      synchronized(CutAction.class){
        if (instance == null)
          instance = new CutAction();
      }
    }
    return instance;
  }

  /**
   * Performs the action
   *
   * @param object - The object to perform the action on
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

    editor.cut();
    return true;
  }

}
