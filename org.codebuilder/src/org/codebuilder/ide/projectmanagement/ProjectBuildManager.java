package org.codebuilder.ide.projectmanagement;

import java.util.*;
import org.codebuilder.ide.feedback.*;
import org.codebuilder.ide.ui.*;
import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.project.*;
import org.codebuilder.template.*;
import org.apache.velocity.exception.*;
import org.codebuilder.project.ProjectBuilder;
import org.codebuilder.ide.ui.actions.project.*;

public class ProjectBuildManager
    extends Thread {

  private static ArrayList projectBuildListeners = new ArrayList();
  private ArrayList progress = new ArrayList();
  private static ProjectBuildManager instance;

  private ProjectBuildManager() {}

  public static ProjectBuildManager getInstance() {
    if (instance == null) {
      synchronized (ProjectBuildManager.class) {
        if (instance == null) {
          instance = new ProjectBuildManager();
        }
      }
    }
    return instance;
  }

  public void reset() {
    instance = new ProjectBuildManager();
  }

  public void startBuild() {
    super.start();
    ListIterator li = projectBuildListeners.listIterator();
    while (li.hasNext()) {
      ProjectBuildListener listener = (ProjectBuildListener) li.next();
      listener.buildStarted();
    }
  }

  /**
   * run
   */
  public void run() {

    IDE ide = IDE.getInstance();
    ProjectManager projectManager = ProjectManager.getInstance();
    Project project = projectManager.getCurrentProject();

    if (project == null) {
      return;
    }

    // ---------------------
    // Validate the open documents
    // (validation saves them too)
    // ---------------------

    //boolean validated = ValidateOpenDocumentsAction.getInstance().perform();

    //if (!validated) {
    //  return;
    //}

    SaveProjectAction.getInstance().perform();

    // ---------------------
    // Take care of the project
    // context
    // ---------------------

    project.reset();
    ProjectManager.getInstance().notifyProjectChangeListeners();
    project.getContext().put("debug", ide.getConsolePanel());
    project.getContext().put("objectViewer", ide.getObjectViewerPanel());
    project.getContext().put("build", ProjectBuildManager.getInstance());

    try {
      ProjectBuilder builder = new ProjectBuilder();
      builder.build(project);
      ProjectBuildManager.getInstance().buildCompleted();
    }
    catch (TemplateException ex) {
      FeedbackManager.getInstance().report("Error encountered in template " +
                                           ex.getTemplate().getPath(),
                                           ex.getMessage(),
                                           FeedbackSeverity.ERROR);
      ProjectBuildManager.getInstance().buildFailed();
    }
    finally {
      ProjectManager.getInstance().notifyProjectChangeListeners();
    }
  }

  private void buildCompleted() {
    ListIterator li = projectBuildListeners.listIterator();
    while (li.hasNext()) {
      ProjectBuildListener listener = (ProjectBuildListener) li.next();
      listener.buildCompleted();
    }
  }

  public void cancelBuild() {
    this.stop();
    //TemplateProcessCoordinator.getInstance().setCanceled(true);
    ListIterator li = projectBuildListeners.listIterator();
    while (li.hasNext()) {
      ProjectBuildListener listener = (ProjectBuildListener) li.next();
      listener.buildCanceled();
    }
  }

  public void buildFailed(){
    ListIterator li = projectBuildListeners.listIterator();
    while (li.hasNext()) {
      ProjectBuildListener listener = (ProjectBuildListener) li.next();
      listener.buildFailed();
    }

  }

  public void addProjectBuildListener(ProjectBuildListener listener) {
    this.projectBuildListeners.add(listener);
  }

  public void removeProjectBuildListener(ProjectBuildListener listener) {
    this.projectBuildListeners.remove(listener);
  }

  public void updateProgress(Object o) {
    progress.add(o);
    ListIterator li = projectBuildListeners.listIterator();
    while (li.hasNext()) {
      ProjectBuildListener listener = (ProjectBuildListener) li.next();
      listener.progressUpdated();
    }
    try {
      Thread.currentThread().sleep(1000);
    }
    catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }

  public List getProgress() {
    return progress;
  }

  public static void main(String[] args) {
    //ProjectBuild build = new ProjectBuild();
    //build.start();
    //build.cancel();
  }
}
