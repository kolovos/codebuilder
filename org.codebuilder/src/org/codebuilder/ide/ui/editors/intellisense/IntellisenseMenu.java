package org.codebuilder.ide.ui.editors.intellisense;

import javax.swing.JPopupMenu;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ListIterator;
import javax.swing.Action;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import java.awt.Rectangle;
import java.util.Collections;

public class IntellisenseMenu
    implements KeyListener {

  private ArrayList items = new ArrayList();
  private JPopupMenu menu = new JPopupMenu();
  private boolean sorted;

  public IntellisenseMenu() {
    super();
  }

  public IntellisenseMenu(boolean sorted){
    super();
    this.sorted = sorted;
  }

  public void add(IntellisenseMenuItem item) {
    items.add(item);
  }

  public void addAll(List actions) {
    items.addAll(actions);
    ListIterator li = actions.listIterator();
    while (li.hasNext()) {
      add( (IntellisenseMenuItem) li.next());
    }
  }

  public void showMenu(Component component) {
    menu = new JPopupMenu();
    menu.setBorder(BorderFactory.createEtchedBorder());
    if (sorted)
      Collections.sort(items);
    ListIterator li = items.listIterator();
    while (li.hasNext()) {
      IntellisenseMenuItem item = (IntellisenseMenuItem) li.next();
      item.setEditor( (JEditorPane) component);
      JMenuItem popupItem = menu.add(item);
      popupItem.setBackground(Color.white);
    }
    try {
      JEditorPane editor = (JEditorPane) component;
      Rectangle r = editor.modelToView(editor.getCaretPosition());
      menu.addKeyListener(this);
      menu.show(editor, (int) r.getX() + 10, (int) r.getY());
    }
    catch (BadLocationException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * keyPressed
   *
   * @param e KeyEvent
   */
  public void keyPressed(KeyEvent e) {
    Component[] components = menu.getComponents();
    for (int i = 0, n = components.length; i < n; i++) {
      JMenuItem item = (JMenuItem) components[i];
      if (item.getText().charAt(0) == e.getKeyChar()) {
        menu.setSelected(item);
        break;
      }
    }
  }

  /**
   * keyReleased
   *
   * @param e KeyEvent
   */
  public void keyReleased(KeyEvent e) {

  }

  /**
   * keyTyped
   *
   * @param e KeyEvent
   */
  public void keyTyped(KeyEvent e) {

  }
  public boolean isSorted() {
    return sorted;
  }
  public void setSorted(boolean sorted) {
    this.sorted = sorted;
  }
}
