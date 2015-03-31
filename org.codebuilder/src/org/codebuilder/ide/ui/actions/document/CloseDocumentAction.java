package org.codebuilder.ide.ui.actions.document;

import java.awt.event.*;
import javax.swing.*;

import org.codebuilder.ide.ui.*;
import org.codebuilder.ide.ui.editors.*;
import org.codebuilder.ide.ui.resources.*;


public class CloseDocumentAction
    extends AbstractDocumentAction {

  protected static CloseDocumentAction instance;

  /**
   * Initialize the action
   */
  private CloseDocumentAction() {
    super();
    putValue(Action.NAME, "Close");
    putValue(Action.SHORT_DESCRIPTION, "Closes the current document");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.CloseDocument);
    putValue(Action.MNEMONIC_KEY, new Integer('Q'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return CloseDocumentAction
   */
  public static CloseDocumentAction getInstance() {
    if (instance == null) {
      synchronized (CloseDocumentAction.class) {
        if (instance == null) {
          instance = new CloseDocumentAction();
        }
      }
    }
    return instance;
  }

  /**
   * Performs the action
   * @param Object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {
    IEditor editor;
    IDE ide = IDE.getInstance();
    EditorsPanel editorsPanel = ide.getEditorsPanel();

    if (object == null) {
      editor = getCurrentEditor();
    }
    else {
      editor = (IEditor) object;
    }

    if (editor == null) return true;

    //------------------
    // Since the editor
    // is not null, proceed
    // with the action
    //------------------

    if (editor.isDirty()) {
      int userReply = JOptionPane.showConfirmDialog(ide,
          editor.getCaption() + " has been changed. Save changes?",
          "Closing " + editor.getCaption(),
          JOptionPane.YES_NO_CANCEL_OPTION);
      switch (userReply) {
        case JOptionPane.YES_OPTION: editor.save(); editorsPanel.removeEditor(editor); return true;
        case JOptionPane.NO_OPTION: editorsPanel.removeEditor(editor); return true;
        case JOptionPane.CANCEL_OPTION: return false;
        default: return true;
      }
    }
    else{
      editorsPanel.removeEditor(editor);
      return true;
    }
  }

}
