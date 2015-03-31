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
import org.codebuilder.ide.ui.swing.filefilters.*;

public class BrowseOpenProjectAction
    extends IDEAbstractAction {

  protected static BrowseOpenProjectAction instance;

  /**
   * Initialize the action
   */
  private BrowseOpenProjectAction() {
    super();
    putValue(Action.NAME, "Open Project...");
    putValue(Action.SHORT_DESCRIPTION, "Opens a project from the disk");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Project.Open());
    putValue(Action.MNEMONIC_KEY, new Integer('O'));
    putValue(Action.ACCELERATOR_KEY,
             KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
  }

  /**
   * Return the one and only instance
   * of the action
   * @return OpenProjectAction
   */
  public static BrowseOpenProjectAction getInstance() {
    if (instance == null) {
      synchronized (BrowseOpenProjectAction.class) {
        if (instance == null) {
          instance = new BrowseOpenProjectAction();
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
    IDE ide = IDE.getInstance();
    ProjectManager projectManager = ProjectManager.getInstance();

    /**
     * @todo Add a filter to limit the
     * selection to valid project files
     * only
     */

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new ProjectFileFilter());

    if (fileChooser.showOpenDialog(ide) != JFileChooser.APPROVE_OPTION) {
      return false;
    }

    OpenProjectAction openProjectAction = new OpenProjectAction(fileChooser.getSelectedFile().getAbsolutePath());
    return openProjectAction.perform();
  }

}
