package org.codebuilder.ide.ui.editors;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import org.codebuilder.common.file.*;
import org.codebuilder.ide.feedback.*;
import org.codebuilder.ide.ui.actions.edit.*;
import org.codebuilder.ide.ui.widgets.*;
import org.codebuilder.jeditor.*;
import org.codebuilder.project.Document;
import org.codebuilder.ide.ui.swing.*;
import javax.swing.border.*;
import org.codebuilder.ide.ui.editors.highlighting.*;

public class TextEditor
    extends IEditor
    implements UndoableEditListener, MouseListener {

  /**
   * @todo Implement find and replace
   */

  protected JEditor editor;
  protected String lastSavedContent = "";
  protected UndoManager undoManager = new UndoManager();
  protected JMenu contextMenu;
  protected JToolBar toolbar = new JToolBar();
  protected StyledEditorKit kit;

  public TextEditor() {

  }

  private void createContextMenu() {
    contextMenu = new JCustomizedMenu();
    contextMenu.add(UndoAction.getInstance());
    contextMenu.add(RedoAction.getInstance());
    contextMenu.addSeparator();
    contextMenu.add(SelectAllAction.getInstance());
    contextMenu.addSeparator();
    contextMenu.add(CopyAction.getInstance());
    contextMenu.add(PasteAction.getInstance());
    contextMenu.add(CutAction.getInstance());
    contextMenu.add(DeleteAction.getInstance());
  }

  public void init() {
    this.setLayout(new BorderLayout());

    JPanel panel = new JPanel(new BorderLayout());
    //JTabbedPane tabs = new JTabbedPane();
    //tabs.addTab("Source",new JScrollPane(editor));
    //tabs.addTab("Desing",new JLabel());
    //tabs.addTab("Outline",new JLabel());
    //tabs.addTab("UML",new JLabel());
    //tabs.setTabPlacement(tabs.BOTTOM);
    //panel.add(tabs, BorderLayout.CENTER);
    panel.add(editor, BorderLayout.CENTER);

    // ---------------------
    // Speed up the scrollbar
    // a bit
    // ---------------------
    //editor.setBorder(null);
    JScrollPane scrollPane = ComponentFactory.createJScrollPane(panel);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    this.add(scrollPane, BorderLayout.CENTER);
    //this.add(new JScrollPane(editor), BorderLayout.CENTER);
    // ---------------------
    // Set up the toolbar
    // ---------------------
    toolbar.setRollover(true);
    toolbar.setFloatable(false);

  }

  public void showContextMenu() {

  }

  /**
   * cut
   */
  public void cut() {
    editor.cut();
  }

  /**
   * copy
   */
  public void copy() {
    editor.copy();
  }

  /**
   * paste
   */
  public void paste() {
    editor.paste();
  }

  /**
   * delete
   */
  public void delete() {
    editor.cut();
  }

  /**
   * find
   */
  public void find() {

  }

  /**
   * replace
   */
  public void replace() {

  }

  public void undo() {
    if (undoManager.canUndo()) {
      undoManager.undo();
    }
  }

  public void redo() {
    if (undoManager.canRedo()) {
      undoManager.redo();
    }
  }

  public void goTo(int line, int column) {
    /*
         int caretPos = 0;
         try {
      caretPos = editor.getLineStartOffset(line - 1);
      caretPos += column;
      editor.requestFocus();
      editor.setCaretPosition(caretPos);
         }
         catch (BadLocationException ex) {
      ex.printStackTrace();
         }
     */
  }

  /**
   * save
   *
   * @return boolean
   */
  public boolean save() {
    try {
      BatchWriter writer = new BatchWriter();
      String content = editor.getText();
      writer.writeAll(content, document.getPath());
      this.lastSavedContent = content;
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
      return false;
    }
    return true;
  }

  /**
   * load
   *
   * @param filename String
   * @return boolean
   */
  public boolean load(Document document) {
    try {
      kit = HighlighterManager.getInstance().getHighlighterFor(document).getKit();
      editor = new JEditor(kit);
      this.document = document;
      init();
      createContextMenu();
      editor.addMouseListener(this);
      BatchReader reader = new BatchReader();
      String content = reader.readAll(document.getPath());
      editor.setText(content);
      this.lastSavedContent = content;
      editor.getDocument().addUndoableEditListener(this);
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
      return false;
    }
    return true;
  }

  /**
   * isDirty
   *
   * @return boolean
   */
  public boolean isDirty() {
    return (lastSavedContent.compareTo(editor.getText()) != 0);
  }

  public void update() {

  }

  /**
   * getCaption
   *
   * @return String
   */
  public String getCaption() {
    return document.getName();
  }

  /**
   * getIcon
   *
   * @return ImageIcon
   */
  public Icon getIcon() {
    return document.getIcon();
  }

  /**
   * undoableEditHappened
   *
   * @param e UndoableEditEvent
   */
  public void undoableEditHappened(UndoableEditEvent e) {
    if (e.getEdit().getPresentationName().compareTo("style change") != 0) {
      undoManager.addEdit(e.getEdit());
    }
  }

  /**
   * mouseClicked
   *
   * @param e MouseEvent
   */
  public void mouseClicked(MouseEvent e) {
    if (e.getButton() == e.BUTTON3) {
      contextMenu.getPopupMenu().show( (Component) e.getSource(), e.getX(),
                                      e.getY());
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

  /**
   * selectAll
   */
  public void selectAll() {
    editor.selectAll();
  }

  /**
   * accept
   *
   * @param document Document
   * @return boolean
   */
  public boolean accept(Document document) {
    return true;
  }

  /**
   * getName
   *
   * @return String
   */
  public String getName() {
    return "Text editor";
  }

}
