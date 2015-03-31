package org.codebuilder.ide.ui.editors.intellisense;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.ListDataListener;
import javax.swing.text.*;
import javax.swing.border.*;

public class IntellisenseWorkbench extends JFrame implements KeyListener{

  JEditorPane editor = new JEditorPane();
  JList list = new JList();

  public IntellisenseWorkbench() {


    this.setTitle("Intellisense Workbench");
    this.getContentPane().setLayout(new BorderLayout());
    this.editor.addKeyListener(this);
    this.getContentPane().add(editor, BorderLayout.CENTER);
    this.setBounds(100,100,500,500);
    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
    this.setVisible(true);

  }

  /**
   * keyPressed
   *
   * @param e KeyEvent
   */
  public void keyPressed(KeyEvent e) {

    if (e.getKeyChar() == '$'){
      editor.setLayout(null);
      editor.add(list,null);
      list.setModel(new PopupListModel());
      list.setBorder(new EtchedBorder());
      Rectangle r = null;
      try {
        r = editor.modelToView(editor.getCaretPosition());
        list.setBounds((int)r.getX() + 10,(int)r.getY(),200,200);
        list.setVisible(true);
      }
      catch (BadLocationException ex) {
      }
    }


    if (e.getKeyCode() == e.VK_DOWN){
      if (list.getSelectedIndex() < list.getModel().getSize())
      list.setSelectedIndex(list.getSelectedIndex() + 1);
      e.setKeyCode(0);
    }

    if (e.getKeyCode() == e.VK_UP){
      if (list.getSelectedIndex() > 0)
      list.setSelectedIndex(list.getSelectedIndex() - 1);
      e.setKeyCode(0);
    }

    if (list.isVisible() && (e.getKeyCode() == e.VK_SPACE || e.getKeyCode() == e.VK_ENTER)){
      try {
        editor.getDocument().insertString(editor.getCaretPosition(),
                                          list.getSelectedValue().toString(), null);
        list.setVisible(false);
        e.setKeyCode(0);
      }
      catch (BadLocationException ex1) {
      }
    }

    if (list.isVisible()){
      try {
        Rectangle r = editor.modelToView(editor.getCaretPosition());
        list.setBounds((int)r.getX() + 10,(int)r.getY(),200,200);
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

  public static void main(String[] args){
    IntellisenseWorkbench wb = new IntellisenseWorkbench();
  }


    class PopupListModel implements ListModel{

      String[] listContents = {"out","tools","project"};

      /**
       * getSize
       *
       * @return int
       */
      public int getSize() {
        return listContents.length;
      }

      /**
       * getElementAt
       *
       * @param index int
       * @return Object
       */
      public Object getElementAt(int index) {
        return listContents[index];
      }

      /**
       * addListDataListener
       *
       * @param l ListDataListener
       */
      public void addListDataListener(ListDataListener l) {
      }

      /**
       * removeListDataListener
       *
       * @param l ListDataListener
       */
      public void removeListDataListener(ListDataListener l) {
      }

    }
  }
