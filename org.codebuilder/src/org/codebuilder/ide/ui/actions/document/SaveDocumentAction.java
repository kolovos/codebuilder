package org.codebuilder.ide.ui.actions.document;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;

public class SaveDocumentAction extends AbstractDocumentAction{

  protected static SaveDocumentAction instance;

  /**
   * Initialize the action
   */
  private SaveDocumentAction() {
    super();
    putValue(Action.NAME, "Save");
    putValue(Action.SHORT_DESCRIPTION,"Saves the current document");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.SaveDocument);
    putValue(Action.MNEMONIC_KEY,new Integer('S'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return SaveDocumentAction
   */
  public static SaveDocumentAction getInstance(){
    if (instance == null){
      synchronized(SaveDocumentAction.class){
        if (instance == null)
          instance = new SaveDocumentAction();
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

    return editor.save();
  }

}
