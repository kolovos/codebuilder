package org.codebuilder.ide.projectmanagement;

import org.codebuilder.project.*;
import org.codebuilder.project.reader.*;

public class ProjectManager
    extends ProjectManagerBase {

  private Project project;
  private static ProjectManager instance;

  private ProjectManager() {
  }

  public static ProjectManager getInstance() {
    if (instance == null) {
      synchronized (ProjectManager.class) {
        if (instance == null) {
          instance = new ProjectManager();
        }
      }
    }
    return instance;
  }

  public Project getCurrentProject() {
    return project;
  }

  public void setCurrentProject(Project project) {
    this.project = project;
  }

  /**
   * Try to open a project from a filename
   * @param filename String
   * @throws ReadException
   */
  public void openProject(String filename) throws ReadException {
    project = new Project();
    try {
      ProjectReader reader = new ProjectReader();
      project = reader.read(filename);
      notifyProjectChangeListeners();
      project.setDirty(false);
    }
    catch (ReadException ex) {
      project = null;
      throw ex;
    }
  }

  public void closeProject(){
    project = null;
    notifyProjectChangeListeners();
  }

  public String getDefaultProjectPath(){
    return System.getProperty("user.dir") + "\\projects";
  }

}
