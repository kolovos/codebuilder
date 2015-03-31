package org.codebuilder.ide.ui.actions.project;

import javax.swing.*;

import org.codebuilder.project.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.feedback.FeedbackManager;
import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.project.writer.*;
import java.util.ListIterator;
import org.codebuilder.ide.ui.editors.IEditor;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import org.codebuilder.ide.projectmanagement.ProjectManager;

public class SaveAllAction extends IDEAbstractAction{

  protected static SaveAllAction instance;

  /**
   * Initialize the action
   */
  private SaveAllAction() {
    super();
    putValue(Action.NAME, "Save All");
    putValue(Action.SHORT_DESCRIPTION,"Save all the open documents");
    putValue(Action.LONG_DESCRIPTION,"");
    putValue(Action.SMALL_ICON,IconFactory.SaveAll);
    putValue(Action.MNEMONIC_KEY,new Integer('S'));
    putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

  }

  /**
   * Return the one and only instance
   * of the action
   * @return SaveAllAction
   */
  public static SaveAllAction getInstance(){
    if (instance == null){
      synchronized(SaveAllAction.class){
        if (instance == null)
          instance = new SaveAllAction();
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
    ProjectManager projectManager = ProjectManager.getInstance();
    Project project = projectManager.getCurrentProject();

    if (project == null) return false;

    try {
      ProjectWriter writer = new ProjectWriter();
      writer.write(project);
      ListIterator li = ide.getEditorsPanel().getEditors().listIterator();
      while (li.hasNext()){
        ((IEditor) li.next()).save();
      }
    }
    catch (WriteException ex) {
      FeedbackManager.getInstance().reportException(ex);
      return false;
    }
    return true;
  }

}
