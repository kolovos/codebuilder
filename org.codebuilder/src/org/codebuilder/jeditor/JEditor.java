package org.codebuilder.jeditor;

/*
 =====================================================================

  JEditor.java

  Created by Claude Duguay
  Copyright (c) 2002

 =====================================================================
 */

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class JEditor
    extends JEditorPane
    implements KeyListener {

  public JEditor(StyledEditorKit kit) {
    this("", kit);
  }

  public JEditor(String text, StyledEditorKit kit) {
    setEditorKit(kit);
    setText(text);
    this.addKeyListener(this);
  }

  /**
   * keyPressed
   * Looks how many leading tabs
   * the previous line has and
   * inserts the same number in
   * the new line
   * @param e KeyEvent
   */
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 10) {
      try {
        int offset = this.getCaretPosition();
        int rowStart = Utilities.getRowStart(this, offset);
        int rowEnd = Utilities.getRowEnd(this, offset);
        String text = this.getText(rowStart, rowEnd - rowStart);
        Pattern pattern = Pattern.compile("(\t)");
        Matcher matcher = pattern.matcher(text);
        int n = 0;
        while (matcher.find()){
          n++;
        }
        String tabs = "\n";
        for (int i=0;i<n;i++){
          tabs += "\t";
        }
        this.getDocument().insertString(offset, tabs, null);
        e.setKeyCode(0);
      }
      catch (BadLocationException ex) {
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
}
