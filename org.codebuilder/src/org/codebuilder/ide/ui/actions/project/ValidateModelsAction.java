package org.codebuilder.ide.ui.actions.project;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.EditorsPanel;
import org.codebuilder.project.DocumentValidationException;
import org.codebuilder.ide.feedback.FeedbackManager;
import java.util.ListIterator;
import org.codebuilder.ide.ui.actions.project.SaveAllAction;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.project.Project;
import org.codebuilder.project.Model;
import org.codebuilder.ide.ui.actions.document.EditDocumentAction;
import org.codebuilder.ide.feedback.FeedbackSeverity;
import org.codebuilder.ide.projectmanagement.ProjectManager;


public class ValidateModelsAction
    extends IDEAbstractAction {

  protected static ValidateModelsAction instance;

  /**
   * Initialize the action
   */
  private ValidateModelsAction() {
    super();
    putValue(Action.NAME, "Validate all models");
    putValue(Action.SHORT_DESCRIPTION, "Validates all the models of the project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Model.Validate());
    putValue(Action.MNEMONIC_KEY, new Integer('V'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return ValidateModelsAction
   */
  public static ValidateModelsAction getInstance() {
    if (instance == null) {
      synchronized (ValidateModelsAction.class) {
        if (instance == null) {
          instance = new ValidateModelsAction();
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

    Project project = ProjectManager.getInstance().getCurrentProject();

    if (project == null) return true;

    // ---------------------
    // First save all the open
    // documents
    // ---------------------

    SaveAllAction.getInstance().perform();

    // ---------------------
    // Then try to validate each
    // document
    // ---------------------

    ListIterator li = project.getAllDocuments().listIterator();
    while (li.hasNext()){
      Model model = (Model) li.next();
      try {
        model.validate();
      }
      catch (DocumentValidationException ex) {
        FeedbackManager.getInstance().report("Syntax error encountered", ex.getMessage(),
                                             FeedbackSeverity.ERROR);
        new EditDocumentAction(model).perform();
        IEditor editor = editorsPanel.getEditorFor(model);
        editor.goTo(ex.getLine(), ex.getColumn());
        editorsPanel.activateEditor(editor);
        return false;
      }
    }
    return true;
  }

}
