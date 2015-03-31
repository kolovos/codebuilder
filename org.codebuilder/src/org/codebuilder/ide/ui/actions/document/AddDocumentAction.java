package org.codebuilder.ide.ui.actions.document;

import javax.swing.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.actions.*;
import org.codebuilder.ide.ui.dialogs.document.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.project.*;

public class AddDocumentAction
    extends IDEAbstractAction {

  protected static AddDocumentAction instance = new AddDocumentAction();

  /**
   * Initialize the action
   */
  private AddDocumentAction() {
    super();
    putValue(Action.NAME, "Add Document...");
    putValue(Action.SHORT_DESCRIPTION, "Adds an existing document to the project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Document.AddExisting());
  }

  /**
   * Return the one and only instance
   * of the action
   * @return NewDocumentAction
   */
  public static AddDocumentAction getInstance() {
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

    AddDocumentDialog dialog = new AddDocumentDialog();
    dialog.showDialog();

    return dialog.isCancelled();
  }

}
