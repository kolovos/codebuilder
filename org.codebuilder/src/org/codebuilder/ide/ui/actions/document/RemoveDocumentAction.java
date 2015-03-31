package org.codebuilder.ide.ui.actions.document;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.project.Project;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.project.*;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.actions.document.CloseDocumentAction;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.ide.ui.*;

public class RemoveDocumentAction
    extends IDEAbstractAction implements DocumentSelectionListener{

  protected static RemoveDocumentAction instance = new RemoveDocumentAction();

  /**
   * Initialize the action
   */
  private RemoveDocumentAction() {
    super();
    DocumentSelectionManager.getInstance().addDocumentSelectionListener(this);
    putValue(Action.NAME, "Remove Document");
    putValue(Action.SHORT_DESCRIPTION,
             "Removes the selected document from the project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Document.Remove());
    putValue(Action.MNEMONIC_KEY, new Integer('R'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return RemoveDocumentAction
   */
  public static RemoveDocumentAction getInstance() {
    return instance;
  }

  /**
   * Performs the action
   * @param Object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {
    Project project = ProjectManager.getInstance().getCurrentProject();
    IDE ide = IDE.getInstance();
    if (project == null) {
      return false;
    }

    //------------------
    // Since the project
    // is not null, proceed
    // with the action
    //------------------

    Document document = ide.getProjectPanel().
        getSelectedDocument();

    if (document == null || document.getParent() == null) {
      return false;
    }

    // ---------------------
    // Now we have the model
    // ready from removal. Just
    // confirm with the user
    // ---------------------

    int response = JOptionPane.showConfirmDialog(ide,
                                                 "Remove \"" +
                                                 document.getName() + "\" from project?",
                                                 "Remove from project ",
                                                 JOptionPane.YES_NO_OPTION);

    if (response != JOptionPane.OK_OPTION) {
      return false;
    }

    // ---------------------
    // Since the user confirmed
    // try to close it if it is
    // being edited
    // ---------------------

    IEditor editor = ide.getEditorsPanel().getEditorFor(document);
    boolean closed = CloseDocumentAction.getInstance().perform(editor);

    if (!closed) {
      return false;
    }

    // ---------------------
    // Since the editor is closed
    // we can now remove the model
    // from the project
    // ---------------------

    document.getParent().removeDocument(document);
    ProjectManager.getInstance().notifyProjectChangeListeners();
    return true;

  }

  /**
   * documentSelected
   *
   * @param newDocument Document
   * @param oldDocument Document
   */
  public void documentSelected(Document newDocument, Document oldDocument) {
    if (newDocument != null){
      putValue(Action.NAME, "Remove \"" + newDocument.getName() + "\"");
    }
  }

}
