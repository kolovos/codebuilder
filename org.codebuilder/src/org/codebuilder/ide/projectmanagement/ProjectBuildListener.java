package org.codebuilder.ide.projectmanagement;

public interface ProjectBuildListener {

  public void buildStarted();

  public void buildCompleted();

  public void buildCanceled();

  public void progressUpdated();

  public void buildFailed();
}
