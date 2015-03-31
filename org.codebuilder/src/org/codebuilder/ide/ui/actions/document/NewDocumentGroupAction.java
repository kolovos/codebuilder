package org.codebuilder.ide.ui.actions.document;

import org.codebuilder.ide.ui.actions.*;
import org.codebuilder.ide.ui.*;
import javax.swing.*;
import org.codebuilder.project.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.projectmanagement.*;

public class NewDocumentGroupAction extends IDEAbstractAction{

  protected static NewDocumentGroupAction instance = new NewDocumentGroupAction();

  private NewDocumentGroupAction() {
    super();
    putValue(Action.NAME, "New Document Group...");
    putValue(Action.SHORT_DESCRIPTION, "Adds a new document group to the project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.DocumentGroup.New());
  }

  public static NewDocumentGroupAction getInstance(){
    return instance;
  }

  /**
   * perform
   *
   * @param object Object
   * @return boolean
   */
  public boolean perform(Object object) {
    Project project = ProjectManager.getInstance().getCurrentProject();
    if (project == null) return false;

    Document document = IDE.getInstance().getProjectPanel().getSelectedDocument();
    DocumentGroup documentGroup = new DocumentGroup();
    documentGroup.setName("untitled");

    if (!(document instanceof DocumentGroup)) document = document.getParent();

    ((DocumentGroup) document).addDocument(documentGroup);
    ProjectManager.getInstance().notifyProjectChangeListeners();
    return true;

  }
}
