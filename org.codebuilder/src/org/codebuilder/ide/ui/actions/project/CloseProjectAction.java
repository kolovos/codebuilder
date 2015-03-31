package org.codebuilder.ide.ui.actions.project;

import javax.swing.Action;
import org.codebuilder.ide.ui.resources.IconFactory;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.project.Project;
import org.codebuilder.ide.ui.EditorsPanel;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.project.writer.*;
import java.util.ListIterator;
import org.codebuilder.ide.ui.actions.document.CloseDocumentAction;
import java.util.ArrayList;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import javax.swing.*;

public class CloseProjectAction
    extends IDEAbstractAction {

  protected static CloseProjectAction instance;

  /**
   * Initialize the action
   */
  private CloseProjectAction() {
    super();
    putValue(Action.NAME, "Close Project");
    putValue(Action.SHORT_DESCRIPTION, "Closes the current project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Project.Close());
    putValue(Action.MNEMONIC_KEY, new Integer('C'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return CloseProjectAction
   */
  public static CloseProjectAction getInstance() {
    if (instance == null) {
      synchronized (CloseProjectAction.class) {
        if (instance == null) {
          instance = new CloseProjectAction();
        }
      }
    }
    return instance;
  }

  /**
   * Performs the action
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {

    Project project = ProjectManager.getInstance().getCurrentProject();
    if (project == null) {
      return true;
    }

    // ---------------------
    // Try to close the templates
    // ---------------------

    IDE ide = IDE.getInstance();
    EditorsPanel editorsPanel = ide.getEditorsPanel();

    //ListIterator li = editorsPanel.getEditors().listIterator();

    //while (li.hasNext()){
    ArrayList editors = editorsPanel.getEditors();
    for (int i = 0, n = editors.size(); i < n; i++) {
      if (!CloseDocumentAction.getInstance().perform(editors.get(0))) {
        return false;
      }
    }
    //}

    // ---------------------
    // Since all the documents
    // have been closed, it is
    // time to save the project
    // ---------------------
    if (project.isDirty()) {
      int answer = JOptionPane.showConfirmDialog(IDE.getInstance(),
                                                 "Project " + project.getName() +
                                                 " has been modified.\nDo you wish to save changes?",
                                                 "Save project...",
                                                 JOptionPane.YES_NO_CANCEL_OPTION);
      if (answer == JOptionPane.YES_OPTION){
        try {
          ProjectWriter writer = new ProjectWriter();
          writer.write(project);
          project.setDirty(false);
        }
        catch (WriteException ex) {
          ex.printStackTrace();
        }
      }
      else if (answer == JOptionPane.CANCEL_OPTION) return false;
    }

    ProjectManager.getInstance().setCurrentProject(null);
    ProjectManager.getInstance().notifyProjectChangeListeners();
    return true;

  }

}
