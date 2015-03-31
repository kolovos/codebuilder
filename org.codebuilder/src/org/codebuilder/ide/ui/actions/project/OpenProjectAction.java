package org.codebuilder.ide.ui.actions.project;

import javax.swing.*;

import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.project.reader.*;
import org.codebuilder.ide.feedback.FeedbackManager;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import java.io.File;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import org.codebuilder.ide.feedback.FeedbackSeverity;
import org.codebuilder.ide.projectmanagement.ProjectManager;

public class OpenProjectAction
    extends IDEAbstractAction {

  protected String projectFileName;
  /**
   * Initialize the action
   */
  public OpenProjectAction(String projectFileName) {
    super();
    this.putValue(AbstractAction.NAME, projectFileName);
    this.putValue(AbstractAction.SMALL_ICON, IconFactory.Project);
    this.projectFileName = projectFileName;
  }

  /**
   * Performs the action
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {
    IDE ide = IDE.getInstance();
    ProjectManager projectManager = ProjectManager.getInstance();

    // ---------------------
    // First try to close the previous project
    // if one is open
    // ---------------------

    if (projectManager.getCurrentProject() != null) {
      boolean currentProjectClosed = CloseProjectAction.getInstance().perform();
      if (!currentProjectClosed) {
        return false;
      }
    }

    try {
      ide.getLastRecentlyUsedProjects().add(projectFileName);
      projectManager.openProject(projectFileName);
    }
    catch (ReadException ex) {
      FeedbackManager.getInstance().report("Could not open project from file " +
                                           projectFileName, ex.getMessage(),
                                           FeedbackSeverity.ERROR);
      ex.printStackTrace();
      return false;
    }

    return true;
  }

}
