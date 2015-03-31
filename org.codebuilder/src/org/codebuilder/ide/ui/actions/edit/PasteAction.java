package org.codebuilder.ide.ui.actions.edit;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class PasteAction extends AbstractEditAction{

  protected static PasteAction instance;

  /**
   * Initialize the action
   */
  private PasteAction() {
    super();
    putValue(Action.NAME, "Paste");
    putValue(Action.SHORT_DESCRIPTION,"Pastes the contents of the clipboard into the document");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Paste);
    putValue(Action.MNEMONIC_KEY,new Integer('V'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

  }

  /**
   * Return the one and only instance
   * of the action
   * @return PasteAction
   */
  public static PasteAction getInstance(){
    if (instance == null){
      synchronized(PasteAction.class){
        if (instance == null)
          instance = new PasteAction();
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
    editor.paste();
    return true;
  }

}
