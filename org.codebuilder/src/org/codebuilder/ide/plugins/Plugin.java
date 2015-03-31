package org.codebuilder.ide.plugins;

import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.ide.feedback.FeedbackManager;
import javax.swing.*;

public interface Plugin {
  /**
   * Starts the plug-in
   * @param ide IDE
   * @param projectManager ProjectManager
   * @param feedbackManager FeedbackManager
   */
  public void start(IDE ide, ProjectManager projectManager, FeedbackManager feedbackManager);
  /**
   * Gets the icon of the plug-in
   * @return ImageIcon
   */
  public Icon getIcon();
  /**
   * Returns the name of the plug-in
   * @return String
   */
  public String getName();

  /**
   * Returns a short description of
   * the plug-in
   * @return String
   */
  public String getShortDescription();

  /**
   * Returs a description of the plug-in
   * @return String
   */
  public String getDescription();


}
