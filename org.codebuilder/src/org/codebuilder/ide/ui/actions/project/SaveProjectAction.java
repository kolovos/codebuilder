package org.codebuilder.ide.ui.actions.project;

import javax.swing.*;

import org.codebuilder.project.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.feedback.FeedbackManager;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.project.writer.*;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.project.writer.ProjectWriter;

public class SaveProjectAction extends IDEAbstractAction{

  protected static SaveProjectAction instance;

  /**
   * Initialize the action
   */
  private SaveProjectAction() {
    super();
    putValue(Action.NAME, "Save Project");
    putValue(Action.SHORT_DESCRIPTION,"Saves the current project");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.Project.Save());
    putValue(Action.MNEMONIC_KEY,new Integer('S'));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return SaveProjectAction
   */
  public static SaveProjectAction getInstance(){
    if (instance == null){
      synchronized(SaveProjectAction.class){
        if (instance == null)
          instance = new SaveProjectAction();
      }
    }
    return instance;
  }

  /**
   * Performs the action
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object){
    IDE ide = IDE.getInstance();
    Project project = ProjectManager.getInstance().getCurrentProject();

    if (project == null) return false;

    SaveAllAction.getInstance().perform();

    try {
      ProjectWriter writer = new ProjectWriter();
      writer.write(project);
      project.setDirty(false);
    }
    catch (WriteException ex) {
      FeedbackManager.getInstance().reportException(ex);
      return false;
    }

    return true;
  }

}
