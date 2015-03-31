package org.codebuilder.ide.plugins.demo;

import org.codebuilder.ide.plugins.Plugin;
import javax.swing.ImageIcon;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.ide.feedback.FeedbackManager;
import org.codebuilder.project.Project;
import javax.swing.JOptionPane;
import javax.swing.*;
import org.codebuilder.project.*;

public class PersistentPlugin implements Plugin{
  public PersistentPlugin() {
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "";
  }

  /**
   * getIcon
   *
   * @return ImageIcon
   */
  public Icon getIcon() {
    return null;
  }

  /**
   * getName
   *
   * @return String
   */
  public String getName() {
    return "Persistent plug-in";
  }

  /**
   * getShortDescription
   *
   * @return String
   */
  public String getShortDescription() {
    return "Plugin that modifies the properties of the project (see Project->Properties)";
  }

  /**
   * start
   *
   * @param ide IDE
   * @param projectManager ProjectManager
   * @param feedbackManager FeedbackManager
   */
  public void start(IDE ide, ProjectManager projectManager,
                    FeedbackManager feedbackManager) {
    Project project = projectManager.getCurrentProject();
    if (project == null){
      JOptionPane.showMessageDialog(ide, "No project is currently open");
      return;
    }
    project.getProperties().add(new StringProperty("persistentplugin.property", "some value"));
    JOptionPane.showMessageDialog(ide, "Added a persistentplugin.property to the project properties");
    return;
  }
}
