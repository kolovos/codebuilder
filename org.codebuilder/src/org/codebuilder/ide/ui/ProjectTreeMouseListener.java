package org.codebuilder.ide.ui;

import java.awt.event.*;
import javax.swing.*;

import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.project.Document;
import java.awt.Component;
import org.codebuilder.project.*;

class ProjectTreeMouseListener
    implements MouseListener {

  private JTree tree;

  public ProjectTreeMouseListener(JTree tree) {
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
      if (e.getClickCount() == 2 && !(selected instanceof DocumentGroup)) {
        if (selected != null) {
          (new EditDocumentAction((Document)selected)).perform();
        }
      }
      if (e.getButton() == e.BUTTON3){
        JMenu menu = ContextMenuFactory.getMenuFor((Document)selected);
        if (selected !=null)
          menu.getPopupMenu().show((Component) e.getSource(), e.getX(),e.getY());
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
