package org.codebuilder.ide.ui.actions.document;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.KeyStroke;

import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.ui.dialogs.document.NewDocumentDialog;
import org.codebuilder.ide.ui.resources.IconFactory;
import org.codebuilder.project.Project;

public class NewDocumentAction
    extends IDEAbstractAction {

  protected static NewDocumentAction instance = new NewDocumentAction();

  /**
   * Initialize the action
   */
  private NewDocumentAction() {
    super();
    putValue(Action.NAME, "New Document...");
    putValue(Action.SHORT_DESCRIPTION, "Adds a new document to the project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Document.New());
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return NewDocumentAction
   */
  public static NewDocumentAction getInstance() {
    return instance;
  }

  /**
   * Performs the action
   * @param Object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {
    Project project = ProjectManager.getInstance().getCurrentProject();

    if (project == null) {
      return false;
    }

    NewDocumentDialog dialog = new NewDocumentDialog();
    dialog.showDialog();

    return dialog.isCancelled();
  }

}
