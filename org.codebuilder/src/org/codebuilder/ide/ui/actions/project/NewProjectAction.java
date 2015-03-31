package org.codebuilder.ide.ui.actions.project;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.project.Project;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.project.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.project.Template;
import java.io.File;
import java.io.*;
import org.codebuilder.project.writer.*;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.ide.ui.dialogs.project.*;

public class NewProjectAction
    extends IDEAbstractAction {

  protected static NewProjectAction instance;

  /**
   * Initialize the action
   */
  private NewProjectAction() {
    super();
    putValue(Action.NAME, "New Project...");
    putValue(Action.SHORT_DESCRIPTION, "Creates a new project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Project.New());
    putValue(Action.MNEMONIC_KEY, new Integer('N'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return NewProjectAction
   */
  public static NewProjectAction getInstance() {
    if (instance == null) {
      synchronized (NewProjectAction.class) {
        if (instance == null) {
          instance = new NewProjectAction();
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
    Project project = ProjectManager.getInstance().getCurrentProject();
    IDE ide = IDE.getInstance();

    // ---------------------
    // First try to close any
    // other open project
    // ---------------------

    if (project != null) {
      if (!CloseProjectAction.getInstance().perform()) {
        return false;
      }
    }

    NewProjectDialog dialog = new NewProjectDialog();
    dialog.showDialog();

    return true;
    /*
    //------------------
    // Since the project
    // is not null, proceed
    // with the action
    //------------------

    String name = "";

    while ( (name != null) && !Template.isValidName(name)) {
      name = JOptionPane.showInputDialog(ide,
                                         "Provide a name for the new project",
                                         "New project",
                                         JOptionPane.OK_CANCEL_OPTION);
      if (name != null && !Template.isValidName(name)) {
        JOptionPane.showMessageDialog(ide,
                                      "The name" + name +
                                      " is not a valid project name");
      }
    }

    // ---------------------
    // If the user has pressed
    // cancel, abort the sequence
    // ---------------------

    if (name == null) {
      return false;
    }

    // ---------------------
    // Now the user has entered
    // a valid name. Prompt to save
    // the new project
    // ---------------------

    JFileChooser chooser = new JFileChooser();
    chooser.setSelectedFile(new File(name + ".xml"));
    int response = chooser.showSaveDialog(ide);

    // ---------------------
    // If the user cancels then
    // abort
    // ---------------------

    if (response != JFileChooser.APPROVE_OPTION) {
      return false;
    }

    // ---------------------
    // The user has chosen a valid
    // name and filename.
    // Therefore create the project
    // ---------------------

    Project newProject = new Project();
    newProject.setName(name);
    newProject.setPath(chooser.getSelectedFile().getAbsolutePath());
    try {
      newProject.save();
    }
    catch (WriteException ex) {
      ex.printStackTrace();
    }

    ProjectManager.getInstance().setCurrentProject(newProject);
    ProjectManager.getInstance().notifyProjectChangeListeners();

    return false;
    */
  }

}
