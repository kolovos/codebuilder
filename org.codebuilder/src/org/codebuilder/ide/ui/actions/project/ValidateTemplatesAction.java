package org.codebuilder.ide.ui.actions.project;

import javax.swing.*;

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
import org.codebuilder.project.Template;
import org.codebuilder.ide.ui.actions.document.EditDocumentAction;
import org.codebuilder.ide.feedback.FeedbackSeverity;
import org.codebuilder.ide.projectmanagement.ProjectManager;


public class ValidateTemplatesAction
    extends IDEAbstractAction {

  protected static ValidateTemplatesAction instance;

  /**
   * Initialize the action
   */
  private ValidateTemplatesAction() {
    super();
    putValue(Action.NAME, "Validate all templates");
    putValue(Action.SHORT_DESCRIPTION, "Validates all the templates of the project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Template.Validate());
    putValue(Action.MNEMONIC_KEY, new Integer('V'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return ValidateTemplatesAction
   */
  public static ValidateTemplatesAction getInstance() {
    if (instance == null) {
      synchronized (ValidateTemplatesAction.class) {
        if (instance == null) {
          instance = new ValidateTemplatesAction();
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

    ListIterator li = project.getAllDocuments(Template.class).listIterator();
    while (li.hasNext()){
      Template template = (Template) li.next();
      try {
        template.validate();
      }
      catch (DocumentValidationException ex) {
        FeedbackManager.getInstance().report("Syntax error encountered", ex.getMessage(),
                                             FeedbackSeverity.ERROR);
        new EditDocumentAction(template).perform();
        IEditor editor = editorsPanel.getEditorFor(template);
        editor.goTo(ex.getLine(), ex.getColumn());
        editorsPanel.activateEditor(editor);
        return false;
      }
    }
    return true;
  }

}
