package org.codebuilder.ide.ui;

import java.awt.event.*;
import javax.swing.*;

import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.project.Document;
import java.awt.Component;
import org.codebuilder.project.GeneratedFile;

class GeneratedFilesTreeMouseListener
    implements MouseListener {

  private JTree tree;

  public GeneratedFilesTreeMouseListener(JTree tree) {
    this.tree = tree;
  }

  /**
   * mouseClicked
   *
   * @param e MouseEvent
   */
  public void mouseClicked(MouseEvent e) {
    try {
      Object selected = tree.getSelectionPath().getLastPathComponent();

      if (!(selected instanceof GeneratedFile)) return;

      if (e.getClickCount() == 2) {
        if (selected != null) {
          new EditDocumentAction((Document)selected).perform();
        }
      }
    }
    catch(NullPointerException ex){
    }
  }

  /**
   * mouseEntered
   *
   * @param e MouseEvent
   */
  public void mouseEntered(MouseEvent e) {
  }

  /**
   * mouseExited
   *
   * @param e MouseEvent
   */
  public void mouseExited(MouseEvent e) {
  }

  /**
   * mousePressed
   *
   * @param e MouseEvent
   */
  public void mousePressed(MouseEvent e) {
  }

  /**
   * mouseReleased
   *
   * @param e MouseEvent
   */
  public void mouseReleased(MouseEvent e) {
  }

}
