package org.codebuilder.ide.ui.actions.document;

import javax.swing.*;

import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.EditorsPanel;
import org.codebuilder.project.DocumentValidationException;
import org.codebuilder.ide.feedback.FeedbackManager;
import java.util.ListIterator;
import org.codebuilder.ide.ui.actions.project.SaveAllAction;
import org.codebuilder.ide.feedback.FeedbackSeverity;


public class ValidateOpenDocumentsAction
    extends AbstractDocumentAction {

  protected static ValidateOpenDocumentsAction instance;

  /**
   * Initialize the action
   */
  private ValidateOpenDocumentsAction() {
    super();
    putValue(Action.NAME, "Validate all open documents");
    putValue(Action.SHORT_DESCRIPTION, "Validates the open documents");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.ValidateOpenDocuments);
    putValue(Action.MNEMONIC_KEY, new Integer('V'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return ValidateOpenDocumentsAction
   */
  public static ValidateOpenDocumentsAction getInstance() {
    if (instance == null) {
      synchronized (ValidateOpenDocumentsAction.class) {
        if (instance == null) {
          instance = new ValidateOpenDocumentsAction();
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
    IDE ide = IDE.getInstance();
    EditorsPanel editorsPanel = ide.getEditorsPanel();

    // ---------------------
    // First save all the open
    // documents
    // ---------------------

    SaveAllAction.getInstance().perform();

    // ---------------------
    // Then try to validate each
    // document
    // ---------------------

    ListIterator li = editorsPanel.getEditors().listIterator();
    while (li.hasNext()){
      IEditor editor = (IEditor) li.next();
      try {
        editor.getDocument().validate();
      }
      catch (DocumentValidationException ex) {
        FeedbackManager.getInstance().report("Syntax error encountered", ex.getMessage(),
                                             FeedbackSeverity.ERROR);
        editor.goTo(ex.getLine(), ex.getColumn());
        editorsPanel.activateEditor(editor);
        return false;
      }
    }
    return true;
  }

}
