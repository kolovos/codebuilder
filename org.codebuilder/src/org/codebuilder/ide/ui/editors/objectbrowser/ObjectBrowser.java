package org.codebuilder.ide.ui.editors.objectbrowser;

import javax.swing.*;
import java.awt.*;
import org.codebuilder.ide.ui.editors.*;
import org.codebuilder.project.*;
import org.codebuilder.ide.ui.swing.*;

public class ObjectBrowser
    extends IEditor {

  JTree tree = ComponentFactory.createJTree();

  public ObjectBrowser(Model model) {
    this.removeAll();
    this.setLayout(new BorderLayout());
    this.add(new JScrollPane(tree), BorderLayout.CENTER);
    tree.setCellRenderer(new ObjectBrowserTreeCellRenderer());
    this.document = model;
    this.load(document);
  }

  /**
   * copy
   */
  public void copy() {
  }

  /**
   * cut
   */
  public void cut() {
  }

  /**
   * delete
   */
  public void delete() {
  }

  /**
   * find
   */
  public void find() {
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
    return null;
  }

  /**
   * goTo
   *
   * @param line int
   * @param column int
   */
  public void goTo(int line, int column) {
  }

  /**
   * isDirty
   *
   * @return boolean
   */
  public boolean isDirty() {
    return false;
  }

  /**
   * load
   *
   * @param filename String
   * @return boolean
   */
  public boolean load(Document document) {
    this.document = document;
    ((Model)document).reset();
    tree.setModel(new ObjectBrowserTreeModel(((Model)document).getContent()));
    JComponentUtilities.updateUI(tree);
    return true;
  }

  /**
   * paste
   */
  public void paste() {
  }

  /**
   * redo
   */
  public void redo() {
  }

  /**
   * replace
   */
  public void replace() {
  }

  /**
   * save
   *
   * @return boolean
   */
  public boolean save() {
    return false;
  }

  /**
   * selectAll
   */
  public void selectAll() {
  }

  /**
   * undo
   */
  public void undo() {
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
    return "Object browser";
  }

}
