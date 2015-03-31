package org.codebuilder.ide.ui.editors;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class TextEditorChangeListener implements DocumentListener{

  private TextEditor editor;

  public TextEditorChangeListener(TextEditor editor) {
    this.editor = editor;
  }

  /**
   * changedUpdate
   *
   * @param e DocumentEvent
   */
  public void changedUpdate(DocumentEvent e) {
  }

  /**
   * insertUpdate
   *
   * @param e DocumentEvent
   */
  public void insertUpdate(DocumentEvent e) {
  }

  /**
   * removeUpdate
   *
   * @param e DocumentEvent
   */
  public void removeUpdate(DocumentEvent e) {
  }
}
