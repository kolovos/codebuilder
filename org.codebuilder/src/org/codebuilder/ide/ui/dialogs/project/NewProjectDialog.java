package org.codebuilder.ide.ui.dialogs.project;

import javax.swing.*;
import javax.swing.event.*;
import org.codebuilder.ide.feedback.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.*;
import org.codebuilder.ide.ui.dialogs.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.project.*;
import org.codebuilder.project.reader.*;
import org.codebuilder.project.writer.*;
import org.codebuilder.util.*;

/**
 * <p>Title: CodeBuilder</p>
 *


 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: Dimitrios Kolovos</p>
 *
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */

public class NewProjectDialog
    extends AbstractDialog implements UndoableEditListener{

  JLabel nameLabel = new JLabel("Project name:");
  JLabel pathLabel = new JLabel("Project directory:");
  JTextField nameField = new JTextField();
  JFileField pathField = new JFileField(ProjectManager.getInstance().getDefaultProjectPath(),
                                        JFileChooser.DIRECTORIES_ONLY);

  public NewProjectDialog() {
    super();
    this.setTitle("New project");
    this.setIcon(IconFactory.Project.New());
    this.setDescription(
        "Provide a name and a directory for the new project");

    //--------------------
    // Add the components in the contentPane
    //--------------------

    contentPane.add(nameLabel);
    contentPane.add(nameField);
    contentPane.add(pathLabel);
    contentPane.add(pathField);

    nameField.getDocument().addUndoableEditListener(this);
    nameField.setText("untitled");
  }

  protected void ok() {
    Project newProject = new Project();
    newProject.setName(nameField.getText());
    newProject.addDocument(new DocumentGroup("templates"));
    newProject.addDocument(new DocumentGroup("models"));
    newProject.addDocument(new DocumentGroup("resources"));

    FileSystem fs = new FileSystem();

    fs.createDirectory(pathField.getText());

    String filename = pathField.getText() + "\\" + nameField.getText() + ".cbp";
    newProject.setPath(filename);
    try {
      ProjectWriter writer = new ProjectWriter();
      writer.write(newProject);
    }
    catch (WriteException ex) {
      showValidationError("The project could not be created.\n Check that the project directory exists and is writeable");
      return;
    }
    IDE.getInstance().getLastRecentlyUsedProjects().add(filename);
    try {
      ProjectManager.getInstance().openProject(filename);
    }
    catch (ReadException ex1) {
      FeedbackManager.getInstance().reportException(ex1);
    }
    this.dispose();
  }

  /**
   * undoableEditHappened
   *
   * @param e UndoableEditEvent
   */
  public void undoableEditHappened(UndoableEditEvent e) {
    pathField.getTextField().setText(ProjectManager.getInstance().getDefaultProjectPath() + "\\" + nameField.getText());
  }

}
