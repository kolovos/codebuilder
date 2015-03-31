package org.codebuilder.ide.ui.editors.intellisense;

import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import java.util.SortedSet;
import java.awt.event.*;

public class IntellisenseMenuItem extends AbstractAction implements Comparable, KeyListener{

  private String value = "";
  private String name = "";
  private String description = "";
  private int moveBack = 0;
  private JEditorPane editor;

  public IntellisenseMenuItem(String name, String value, String description, int moveBack){
    this.value = value;
    this.name = name;
    this.description = description;
    this.moveBack = moveBack;
    putValue(Action.NAME, name);
    putValue(Action.SHORT_DESCRIPTION, description);
  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    try {
      editor.getDocument().insertString(editor.getCaretPosition(), this.value, null);
      editor.setCaretPosition(editor.getCaretPosition() - this.moveBack);
    }
    catch (BadLocationException ex) {
    }
  }
  public JEditorPane getEditor() {
    return editor;
  }
  public void setEditor(JEditorPane editor) {
    this.editor = editor;
  }

  /**
   * compareTo
   *
   * @param o Object
   * @return int
   */
  public int compareTo(Object o) {
    return (this.getName().compareTo(((IntellisenseMenuItem)o).getName()));
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public void keyPressed(KeyEvent e) {

  }

  public void keyReleased(KeyEvent e) {
  }

  public void keyTyped(KeyEvent e) {
  }

}
