package org.codebuilder.ide.ui.editors.xmleditor;

import javax.swing.tree.*;
import java.util.EventObject;
import javax.swing.event.CellEditorListener;
import java.awt.Component;
import javax.swing.JTree;
import org.jdom.*;
import javax.swing.*;
import java.awt.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class DocumentTreeCellEditor implements TreeCellEditor{

  JTextField text = new JTextField();

  public DocumentTreeCellEditor() {
  }

  /**
   * cancelCellEditing
   */
  public void cancelCellEditing() {
  }

  /**
   * stopCellEditing
   *
   * @return boolean
   */
  public boolean stopCellEditing() {
    return false;
  }

  /**
   * getCellEditorValue
   *
   * @return Object
   */
  public Object getCellEditorValue() {
    return text.getText();
  }

  /**
   * isCellEditable
   *
   * @param anEvent EventObject
   * @return boolean
   */
  public boolean isCellEditable(EventObject anEvent) {
    return true;
  }

  /**
   * shouldSelectCell
   *
   * @param anEvent EventObject
   * @return boolean
   */
  public boolean shouldSelectCell(EventObject anEvent) {
    return false;
  }

  /**
   * addCellEditorListener
   *
   * @param l CellEditorListener
   */
  public void addCellEditorListener(CellEditorListener l) {
  }

  /**
   * removeCellEditorListener
   *
   * @param l CellEditorListener
   */
  public void removeCellEditorListener(CellEditorListener l) {
  }

  /**
   * getTreeCellEditorComponent
   *
   * @param tree JTree
   * @param value Object
   * @param isSelected boolean
   * @param expanded boolean
   * @param leaf boolean
   * @param row int
   * @return Component
   */
  public Component getTreeCellEditorComponent(JTree tree, Object value,
                                              boolean isSelected,
                                              boolean expanded, boolean leaf,
                                              int row) {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.add(text,BorderLayout.CENTER);
    Element e = (Element) value;
    JLabel label = new JLabel();
    label.setOpaque(false);
    label.setIcon(IconFactory.getIconFor(e.getName()));
    panel.add(label, BorderLayout.WEST);
    text.setText(e.getName());
    text.selectAll();
    text.setBorder(null);
    return panel;
  }

}
