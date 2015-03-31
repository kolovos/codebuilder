package org.codebuilder.ide.ui.actions.document;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.EditorsPanel;
import org.codebuilder.project.*;
import org.codebuilder.ide.feedback.FeedbackManager;
import org.codebuilder.ide.feedback.FeedbackSeverity;

public class ValidateDocumentAction
    extends AbstractDocumentAction {

  protected static ValidateDocumentAction instance;

  /**
   * Initialize the action
   */
  private ValidateDocumentAction() {
    super();
    putValue(Action.NAME, "Validate current document");
    putValue(Action.SHORT_DESCRIPTION, "Validates the current document");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.ValidateDocument);
    putValue(Action.MNEMONIC_KEY, new Integer('V'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return ValidateDocumentAction
   */
  public static ValidateDocumentAction getInstance() {
    if (instance == null) {
      synchronized (ValidateDocumentAction.class) {
        if (instance == null) {
          instance = new ValidateDocumentAction();
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

    editor = getCurrentEditor();

    if (editor == null) return true;

    try {
      editor.getDocument().validate();
    }
    catch (DocumentValidationException ex) {
      FeedbackManager.getInstance().report("Syntax error encountered", ex.getMessage(),
                                             FeedbackSeverity.ERROR);
      editor.goTo(ex.getLine(),ex.getColumn());
    }
    return true;
  }
}
