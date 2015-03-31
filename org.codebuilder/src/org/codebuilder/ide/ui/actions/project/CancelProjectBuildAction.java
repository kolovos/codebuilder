package org.codebuilder.ide.ui.actions.project;

import java.awt.event.*;
import javax.swing.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.actions.*;
import org.codebuilder.ide.ui.resources.*;

public class CancelProjectBuildAction
    extends IDEAbstractAction implements ProjectBuildListener{

  protected static CancelProjectBuildAction instance;

  /**
   * Initialize the action
   */
  private CancelProjectBuildAction() {
    super();
    putValue(Action.NAME, "Cancel Build");
    putValue(Action.SHORT_DESCRIPTION,
             "Cancels the current build");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.CancelBuildProject);
  }

  /**
   * Return the one and only instance
   * of the action
   * @return RunProjectAction
   */
  public static CancelProjectBuildAction getInstance() {
    if (instance == null) {
      synchronized (CancelProjectBuildAction.class) {
        if (instance == null) {
          instance = new CancelProjectBuildAction();
          ProjectBuildManager.getInstance().addProjectBuildListener(instance);
          instance.setEnabled(false);
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

    if (ProjectManager.getInstance().getCurrentProject() == null) return false;

    ProjectBuildManager.getInstance().cancelBuild();
    return true;
  }

  /**
   * buildCanceled
   */
  public void buildCanceled() {
    instance.setEnabled(false);
  }

  /**
   * buildCompleted
   */
  public void buildCompleted() {
    instance.setEnabled(false);
  }

  /**
   * buildFailed
   */
  public void buildFailed() {
    instance.setEnabled(false);
  }

  /**
   * buildStarted
   */
  public void buildStarted() {
    instance.setEnabled(true);
  }

  /**
   * progressUpdated
   */
  public void progressUpdated() {
  }
}
