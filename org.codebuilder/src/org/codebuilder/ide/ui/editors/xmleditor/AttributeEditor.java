package org.codebuilder.ide.ui.editors.xmleditor;

import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.table.TableModel;
import javax.swing.event.TableModelListener;
import java.util.List;
import org.jdom.Attribute;
import org.codebuilder.ide.ui.swing.*;

public class AttributeEditor
    extends JPanel {

  private JTable table = new JTable();
  private List attributes;

  public AttributeEditor() {
    init();
  }

  public AttributeEditor(List attributes) {
    this.setAttributes(attributes);
    init();
  }

  private void init() {
    this.setLayout(new BorderLayout());
    this.add(ComponentFactory.createJScrollPane(table), BorderLayout.CENTER);
    table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setModel(new AttributesModel());
    table.getColumnModel().getColumn(0).setCellRenderer(new AttributeCellRenderer());
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    table.setRowHeight(20);
  }

  public List getAttributes() {
    return attributes;
  }

  public Attribute getSelectedAttribute(){
    if (table.getSelectedRow() >= 0 && table.getSelectedRow() < table.getRowCount())
      return (Attribute) attributes.get(table.getSelectedRow());
    else
      return null;
  }

  public void setAttributes(List attributes) {
    this.attributes = attributes;
    table.updateUI();
  }

  public void stopEditing(){

  }

  class AttributesModel
      implements TableModel {

    /**
     * getColumnCount
     *
     * @return int
     */
    public int getColumnCount() {
      return 2;
    }

    /**
     * getRowCount
     *
     * @return int
     */
    public int getRowCount() {

      if (attributes == null) {
        return 0;
      }
      else {
        return attributes.size();
      }
    }

    /**
     * isCellEditable
     *
     * @param rowIndex int
     * @param columnIndex int
     * @return boolean
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      //if (columnIndex == 1)
        return true;
      //else
      //  return false;
    }

    /**
     * getColumnClass
     *
     * @param columnIndex int
     * @return Class
     */
    public Class getColumnClass(int columnIndex) {
      return "".getClass();
    }

    /**
     * getValueAt
     *
     * @param rowIndex int
     * @param columnIndex int
     * @return Object
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
      if (columnIndex == 0)
        return ((Attribute) attributes.get(rowIndex)).getName();
      else
        return ((Attribute) attributes.get(rowIndex)).getValue();
    }

    /**
     * setValueAt
     *
     * @param aValue Object
     * @param rowIndex int
     * @param columnIndex int
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      if (columnIndex == 0)
        ((Attribute) attributes.get(rowIndex)).setName(aValue.toString());
      else
        ((Attribute) attributes.get(rowIndex)).setValue(aValue.toString());
    }

    /**
     * getColumnName
     *
     * @param columnIndex int
     * @return String
     */
    public String getColumnName(int columnIndex) {
      if (columnIndex == 0)
        return "Attribute";
      else
        return "Value";
    }

    /**
     * addTableModelListener
     *
     * @param l TableModelListener
     */
    public void addTableModelListener(TableModelListener l) {
    }

    /**
     * removeTableModelListener
     *
     * @param l TableModelListener
     */
    public void removeTableModelListener(TableModelListener l) {
    }

  }

}
