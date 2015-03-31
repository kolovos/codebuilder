package org.codebuilder.ide.plugins.demo;

import org.codebuilder.ide.plugins.Plugin;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.ide.feedback.FeedbackManager;
import javax.swing.ImageIcon;
import org.codebuilder.ide.ui.editors.IEditor;
import javax.swing.JOptionPane;
import javax.swing.*;

public class DemoPlugin implements Plugin{

  IDE ide;

  public DemoPlugin() {
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
    this.ide = ide;

    IEditor editor = ide.getEditorsPanel().getCurrentEditor();

    if (editor == null){
      JOptionPane.showMessageDialog(ide,"No document is currently being edited");
    }
    else{
      JOptionPane.showMessageDialog(ide,"Document " + editor.getDocument().getName() + " is currently being edited");
    }
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
    return "Demo plug-in";
  }

  /**
   * getShortDescription
   *
   * @return String
   */
  public String getShortDescription() {
    return "Displays a message box with the name of the currently edited document";
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "";
  }
}
