package org.codebuilder.ide.ui.actions.project;

import java.awt.event.*;
import javax.swing.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.actions.*;
import org.codebuilder.ide.ui.resources.*;

public class BuildProjectAction
    extends IDEAbstractAction implements ProjectBuildListener{

  protected static BuildProjectAction instance;

  /**
   * Initialize the action
   */
  private BuildProjectAction() {
    super();
    putValue(Action.NAME, "Build Project");
    putValue(Action.SHORT_DESCRIPTION,
             "Builds the current project");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.BuildProject);
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return RunProjectAction
   */
  public static BuildProjectAction getInstance() {
    if (instance == null) {
      synchronized (BuildProjectAction.class) {
        if (instance == null) {
          instance = new BuildProjectAction();
          ProjectBuildManager.getInstance().addProjectBuildListener(instance);
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

    SaveAllAction.getInstance().perform();
    if (ProjectManager.getInstance().getCurrentProject() == null) return false;

    ProjectBuildManager.getInstance().reset();
    //ProjectBuildDialog dialog = new ProjectBuildDialog();
    ProjectBuildManager.getInstance().startBuild();
    //dialog.showDialog();
    return true;

    /*
         IDE ide = IDE.getInstance();
         ProjectManager projectManager = ProjectManager.getInstance();
         Project project = projectManager.getCurrentProject();

         if (project == null) return false;

         // ---------------------
         // Validate the open documents
         // (validation saves them too)
         // ---------------------

     boolean validated = ValidateOpenDocumentsAction.getInstance().perform();

         if (!validated) {
      return false;
         }

         // ---------------------
         // Take care of the project
         // context
         // ---------------------

         project.reset();
         ProjectManager.getInstance().notifyProjectChangeListeners();
     project.getContext().put("debug", ide.getLogsPanel().getDebugLogPanel());

         try {
      project.run();
      FeedbackManager.getInstance().report("Project \"" + project.getName() + "\" run successfully","",FeedbackSeverity.SUCCESS);
         }
         catch (TemplateException ex) {
      //FeedbackManager.getInstance().reportException(ex);
      FeedbackManager.getInstance().report("Error encountered in template " + ex.getTemplate().getPath(),ex.getMessage(),FeedbackSeverity.ERROR);
         }
         finally{
      ProjectManager.getInstance().notifyProjectExcecutionListeners();
         }

         return true;
     */
  }

  /**
   * buildCanceled
   */
  public void buildCanceled() {
    instance.setEnabled(true);
  }

  /**
   * buildCompleted
   */
  public void buildCompleted() {
    instance.setEnabled(true);
  }

  /**
   * buildFailed
   */
  public void buildFailed() {
    instance.setEnabled(true);
  }

  /**
   * buildStarted
   */
  public void buildStarted() {
    instance.setEnabled(false);
  }

  /**
   * progressUpdated
   */
  public void progressUpdated() {

  }
}
