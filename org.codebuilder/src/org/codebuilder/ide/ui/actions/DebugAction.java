package org.codebuilder.ide.ui.actions;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.project.Project;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.project.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.project.Template;
import java.io.File;
import org.codebuilder.common.file.BatchWriter;
import java.io.*;
import org.codebuilder.ide.feedback.FeedbackManager;
import org.codebuilder.ide.ui.actions.document.EditDocumentAction;
import java.applet.AudioClip;
import java.applet.Applet;
import java.net.*;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import java.awt.*;

public class DebugAction
    extends IDEAbstractAction {

  protected static DebugAction instance;

  /**
   * Initialize the action
   */
  private DebugAction() {
    super();
    putValue(Action.NAME, "Action for debugging purposes");
    putValue(Action.SHORT_DESCRIPTION, "Action for debugging purposes");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Debug);
  }

  /**
   * Return the one and only instance
   * of the action
   * @return NewTemplateAction
   */
  public static DebugAction getInstance() {
    if (instance == null) {
      synchronized (DebugAction.class) {
        if (instance == null) {
          instance = new DebugAction();
        }
      }
    }
    return instance;
  }

  /**
   * Performs the action
   * @param Object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {
    Project project = ProjectManager.getInstance().getCurrentProject();
    IDE ide = IDE.getInstance();

    return true;
  }

  public void msgbox(String str) {
    JOptionPane.showMessageDialog(IDE.getInstance(), str, "Debug Action", 0);
  }

  public void msgbox(boolean bool) {
    msgbox(Boolean.toString(bool));
  }
}
