package org.codebuilder.ide.ui;

import javax.swing.*;
import org.codebuilder.project.Document;
import org.codebuilder.project.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import org.codebuilder.ide.ui.swing.*;
import org.codebuilder.ide.projectmanagement.*;
import javax.swing.border.*;
import javax.swing.event.*;
import org.codebuilder.ide.ui.resources.*;

public class PropertiesEditor
    extends JPanel
    implements DocumentSelectionListener, ProjectChangeListener {

  Document document = null;
  JTable table = new JTable();
  JLabel documentLabel = new JLabel();
  JLabel descriptionLabel = new JLabel(" ");

  public PropertiesEditor() {

    DocumentSelectionManager.getInstance().addDocumentSelectionListener(this);
    ProjectManager.getInstance().addProjectChangeListener(this);

    this.setLayout(new BorderLayout());
    table.getSelectionModel().setSelectionMode(table.getSelectionModel().SINGLE_SELECTION);
    table.setModel(new PropertiesModel());
    table.getColumnModel().getColumn(0).setCellRenderer(new
        PropertyNameCellRenderer());
    table.getColumnModel().getColumn(1).setCellRenderer(new
        PropertyNameCellRenderer());
    table.getColumnModel().getColumn(1).setCellEditor(new
            PropertyValueCellEditor());
    table.getSelectionModel().addListSelectionListener(new
        PropertiesSelectionListener());
    table.setOpaque(true);
    table.setRowHeight(20);
    table.setBackground(Color.WHITE);
    this.add(new JScrollPane(table), BorderLayout.CENTER);

    documentLabel.setBackground(SystemColor.WHITE);
    documentLabel.setOpaque(true);
    documentLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
    //documentLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(),BorderFactory.createCompoundBorder(new LineBorder(SystemColor.windowBorder), new EmptyBorder(4,4,4,4))));
    JScrollPane documentLabelScrollPane = new JScrollPane(documentLabel);
    documentLabelScrollPane.setBorder(BorderFactory.createCompoundBorder(new
        EmptyBorder(0, 0, 4, 0), documentLabelScrollPane.getBorder()));
    documentLabelScrollPane.setPreferredSize(new Dimension(1000, 25));

    JScrollPane descriptionLabelScrollPane = new JScrollPane(descriptionLabel);
    descriptionLabelScrollPane.setBorder(BorderFactory.createCompoundBorder(new
        EmptyBorder(4, 0, 0, 0), documentLabelScrollPane.getBorder()));
    descriptionLabelScrollPane.setPreferredSize(new Dimension(1000, 35));

    descriptionLabel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(4,0,0,0), new EtchedBorder()));

    this.add(documentLabelScrollPane, BorderLayout.NORTH);
    this.add(descriptionLabel, BorderLayout.SOUTH);
  }

  /**
   * documentSelected
   *
   * @param newDocument Document
   * @param oldDocument Document
   */
  public void documentSelected(Document newDocument, Document oldDocument) {
    document = newDocument;
    update();
  }

  public void update() {

    if (document != null) {
      documentLabel.setText(document.getName() + " - " + document.getShortDescription());
      documentLabel.setIcon(document.getIcon());
    }
    else {
      documentLabel.setText("No document selected");
      documentLabel.setIcon(IconFactory.Empty);
    }
    JComponentUtilities.updateUI(table);
    updateDescription();
  }

  public void updateDescription() {
    int row = table.getSelectedRow();
    String description = "<html><b>Description:</b><br>";
    if (document == null || row <0 || row+1 > table.getRowCount() || table.getModel().getValueAt(row, 1)==null){
      description += "No description available";
    }
    else{
      Property property = (Property) table.getModel().getValueAt(row, 1);
      description += property.getDescription();
    }
    description += "</html>";
    descriptionLabel.setText(description);
  }

  /**
   * projectChanged
   */
  public void projectChanged() {
    if (ProjectManager.getInstance().getCurrentProject() == null) document = null;
    update();
  }

  class PropertiesModel
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
      if (document == null) {
        return 0;
      }
      return document.getProperties().size();
    }

    /**
     * isCellEditable
     *
     * @param rowIndex int
     * @param columnIndex int
     * @return boolean
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return (columnIndex != 0);
    }

    /**
     * getColumnClass
     *
     * @param columnIndex int
     * @return Class
     */
    public Class getColumnClass(int columnIndex) {
      return Object.class;
    }

    /**
     * getValueAt
     *
     * @param rowIndex int
     * @param columnIndex int
     * @return Object
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
      if (rowIndex + 1 > table.getRowCount()) return null;
      if (columnIndex == 0) {
        return ( (Property) document.getProperties().get(rowIndex)).getName();
      }
      else {
        return ( (Property) document.getProperties().get(rowIndex));
      }
    }

    /**
     * setValueAt
     *
     * @param aValue Object
     * @param rowIndex int
     * @param columnIndex int
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      if (columnIndex == 0) {
        ( (Property) document.getProperties().get(rowIndex)).setName(aValue.
            toString());
      }
      else {
        ( (Property) document.getProperties().get(rowIndex)).setValue(aValue.
            toString());
        ProjectManager.getInstance().notifyProjectChangeListeners();
      }

    }

    /**
     * getColumnName
     *
     * @param columnIndex int
     * @return String
     */
    public String getColumnName(int columnIndex) {
      if (columnIndex == 0) {
        return "Name";
      }
      else {
        return "Value";
      }
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

  class PropertyNameCellRenderer

      implements TableCellRenderer {
    /**
     * getTableCellRendererComponent
     *
     * @param table JTable
     * @param value Object
     * @param isSelected boolean
     * @param hasFocus boolean
     * @param row int
     * @param column int
     * @return Component
     */
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {

      JLabel label = new JLabel();
      label.setOpaque(true);

      // ---------------------
      // Arrange the back and
      // fore color
      // ---------------------

      if (isSelected) {
        label.setBackground(SystemColor.textHighlight);
        label.setForeground(SystemColor.textHighlightText);
      }
      else {
        label.setBackground(SystemColor.text);
        label.setForeground(SystemColor.textText);
      }

      label.setBorder(new EmptyBorder(0, 4, 0, 0));

      if (column == 1) {
        label.setFont(label.getFont().deriveFont(Font.BOLD));
      }
      if (value!= null)
      label.setText(value.toString());

      return label;
    }

  }

  class PropertiesSelectionListener
      implements ListSelectionListener {
    /**
     * valueChanged
     *
     * @param e ListSelectionEvent
     */
    public void valueChanged(ListSelectionEvent e) {
      updateDescription();
    }

  }

  class PropertyValueCellEditor extends AbstractCellEditor implements TableCellEditor{

    Property property = null;
    JComboBox combo = new JComboBox();
    JTextField text = new JTextField();

    /**
     * getTableCellEditorComponent
     *
     * @param table JTable
     * @param value Object
     * @param isSelected boolean
     * @param row int
     * @param column int
     * @return Component
     */
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row,
                                                 int column) {
      property = (Property) value;
      if (property instanceof CollectionProperty){
        combo = new JComboBox();
        combo.setModel(new CollectionPropertyModel());
        combo.setSelectedItem(property.getValue());
        return combo;
      }
      else {
        text = new JTextField();
        text.setText(property.getValue());
        return text;
      }
    }

    /**
     * getCellEditorValue
     *
     * @return Object
     */
    public Object getCellEditorValue() {
      if (property instanceof CollectionProperty){
        return combo.getSelectedItem();
      }
      else{
        return text.getText();
      }
    }

    class CollectionPropertyModel implements ComboBoxModel{

      Object item;

      /**
       * getSelectedItem
       *
       * @return Object
       */
      public Object getSelectedItem() {
        return item;
      }

      /**
       * setSelectedItem
       *
       * @param anItem Object
       */
      public void setSelectedItem(Object anItem) {
        this.item = anItem;
      }

      /**
       * getSize
       *
       * @return int
       */
      public int getSize() {
        return ((CollectionProperty) property).getCollection().size();
      }

      /**
       * getElementAt
       *
       * @param index int
       * @return Object
       */
      public Object getElementAt(int index) {
        return ((java.util.List)((CollectionProperty) property).getCollection()).get(index);
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


}
