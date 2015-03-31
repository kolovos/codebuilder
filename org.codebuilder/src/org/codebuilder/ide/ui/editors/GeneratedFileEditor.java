package org.codebuilder.ide.ui.editors;

import org.codebuilder.project.GeneratedFile;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.jeditor.NumberedEditorKit;
import org.codebuilder.jeditor.*;

public class GeneratedFileEditor extends TextEditor implements ProjectBuildListener{

  protected GeneratedFileEditor(){
    this.kit = new REJavaTypes.Kit();
    ProjectBuildManager.getInstance().addProjectBuildListener(this);
  }

  /**
   * buildCanceled
   */
  public void buildCanceled() {
    super.load(document);
  }

  /**
   * buildCompleted
   */
  public void buildCompleted() {
    super.load(document);
  }

  /**
   * buildStarted
   */
  public void buildStarted() {
  }

  /**
   * progressUpdated
   */
  public void progressUpdated() {
  }

  /**
   * buildFailed
   */
  public void buildFailed() {
  }
}
