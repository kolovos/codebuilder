package org.codebuilder.ide.ui.dialogs.tools;

import org.codebuilder.ide.ui.dialogs.*;
import javax.swing.*;
import java.awt.*;
import org.codebuilder.ide.ui.actions.*;
import org.codebuilder.ide.ui.resources.*;
import org.jdom.*;
import org.jdom.input.*;
import org.codebuilder.ide.feedback.*;
import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import org.jdom.Element;
import org.codebuilder.ide.ui.swing.*;
import java.util.*;
import javax.swing.border.*;
import java.io.StringWriter;
import org.jdom.output.XMLOutputter;
import org.jdom.output.Format;
import java.io.IOException;
import org.codebuilder.common.file.BatchWriter;

public abstract class FlatXMLConfigurationDialog
    extends AbstractDialog {

  protected String configurationFile; // = "config/tools.xml";
  protected String[] columnNames;
  protected String[] columnCaptions;
  protected String configurable;

  protected JTable table = new JTable();
  protected JToolBar toolbar = new JToolBar();
  protected Document document = null;

  public FlatXMLConfigurationDialog() {
    setConfiguration();
    loadConfiguration();
  }

  /**
   * To implement this method one must set:
   * title - The title of the dialog
   * description - The description of the dialog
   * columnNames - A collection of column names
   * columnCaptions - A collection of column captions
   * configurationFile - The path to the flat configuration file
   * configurable - A title for the object under configuration
   */
  public abstract void setConfiguration();

  public void loadConfiguration() {

    this.contentPane.setBorder(new EtchedBorder());
    this.contentPane.setLayout(new BorderLayout());
    this.contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
    this.contentPane.add(toolbar, BorderLayout.NORTH);
    toolbar.add(new AddClassAction());
    toolbar.add(new RemoveClassAction());
    toolbar.addSeparator();
    toolbar.add(new MoveClassUpAction());
    toolbar.add(new MoveClassDownAction());
    toolbar.addSeparator();
    toolbar.add(new TestClassAction());
    toolbar.setRollover(true);

    /**
     * Load the xml configuration file
     */

    SAXBuilder builder = new SAXBuilder();
    try {
      builder.setIgnoringElementContentWhitespace(true);
      document = builder.build(configurationFile);
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
    }

    table.setModel(new ClassConfigurationTableModel());
    for (int i = 0; i < table.getColumnCount(); i++) {
      table.getColumnModel().getColumn(i).setCellRenderer(new ToolsCellRenderer());
    }
    table.setRowHeight(20);
    table.getSelectionModel().setSelectionMode(table.getSelectionModel().
                                               SINGLE_SELECTION);

  }

  /**
   * ok
   */
  protected void ok() {
    if (document == null) return;
    XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
    StringWriter writer = new StringWriter();
    try {
      outputter.output(document, writer);
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
    }
    BatchWriter bw = new BatchWriter();
    try{
      bw.writeAll(writer.toString(), configurationFile);
    }
    catch (Exception ex){
      FeedbackManager.getInstance().reportException(ex);
    }
    cancel();
  }

  class ClassConfigurationTableModel
      implements TableModel {
    /**
     * getColumnCount
     *
     * @return int
     */
    public int getColumnCount() {
      return columnNames.length;
    }

    /**
     * getRowCount
     *
     * @return int
     */
    public int getRowCount() {
      return document.getRootElement().getChildren().size();
    }

    /**
     * isCellEditable
     *
     * @param rowIndex int
     * @param columnIndex int
     * @return boolean
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
      return true;
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
      Element el = (Element) document.getRootElement().getChildren().get(
          rowIndex);
      return ( (Attribute) el.getAttributes().get(columnIndex)).getValue();
    }

    /**
     * setValueAt
     *
     * @param aValue Object
     * @param rowIndex int
     * @param columnIndex int
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
      Element el = (Element) document.getRootElement().getChildren().get(
          rowIndex);
      ( (Attribute) el.getAttributes().get(columnIndex)).setValue(aValue.
          toString());
    }

    /**
     * getColumnName
     *
     * @param columnIndex int
     * @return String
     */
    public String getColumnName(int columnIndex) {
      return columnCaptions[columnIndex];
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

  class AddClassAction
      extends IDEAbstractAction {

    public AddClassAction() {
      putValue(Action.NAME, "Add " + configurable);
      putValue(Action.SHORT_DESCRIPTION, "Adds a new " + configurable);
      putValue(Action.LONG_DESCRIPTION, "");
      putValue(Action.SMALL_ICON, IconFactory.AddProperty);
    }

    /**
     * perform
     *
     * @param object Object
     * @return boolean
     */
    public boolean perform(Object object) {
      Element el = new Element("tool");
      for (int i = 0; i < columnNames.length; i++) {
        el.getAttributes().add(new Attribute(columnNames[i], ""));
      }
      document.getRootElement().addContent(el);
      JComponentUtilities.updateUI(table);
      return true;
    }

  }

  class RemoveClassAction
      extends IDEAbstractAction {

    public RemoveClassAction() {
      putValue(Action.NAME, "Remove " + configurable);
      putValue(Action.SHORT_DESCRIPTION, "Removes the selected " + configurable);
      putValue(Action.LONG_DESCRIPTION, "");
      putValue(Action.SMALL_ICON, IconFactory.RemoveProperty);
    }

    /**
     * perform
     *
     * @param object Object
     * @return boolean
     */
    public boolean perform(Object object) {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
        document.getRootElement().removeContent( (Element) document.
                                                getRootElement().getChildren().
                                                get(selectedRow));
        JComponentUtilities.updateUI(table);
        return true;
      }
      return false;
    }

  }

  class TestClassAction
      extends IDEAbstractAction {

    public TestClassAction() {
      putValue(Action.NAME, "Test " + configurable);
      putValue(Action.SHORT_DESCRIPTION,
               "Tests if the selected " + configurable + " can be created");
      putValue(Action.LONG_DESCRIPTION, "");
      putValue(Action.SMALL_ICON, IconFactory.TestClass);
    }

    /**
     * perform
     *
     * @param object Object
     * @return boolean
     */
    public boolean perform(Object object) {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0 && selectedRow < table.getRowCount()) {
        String clazz = ( (Attribute) ( (Element) document.getRootElement().
                                      getChildren().get(
            selectedRow)).getAttributes().get(columnNames.length - 1)).getValue();
        try {
          Object o = Class.forName(clazz).newInstance();
          JOptionPane.showMessageDialog(thiz, "Instance created successfully!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
          o = null;
        }
        catch (Exception ex) {
          JOptionPane.showMessageDialog(thiz, "Instance could not be created",
                                        "Failure", JOptionPane.ERROR_MESSAGE);
        }
      }
      return true;
    }
  }

  class MoveClassUpAction
      extends IDEAbstractAction {

    public MoveClassUpAction() {
      putValue(Action.NAME, "Move up");
      putValue(Action.SHORT_DESCRIPTION,
               "Moves the selected " + configurable + " up in the list");
      putValue(Action.LONG_DESCRIPTION, "");
      putValue(Action.SMALL_ICON, IconFactory.MoveUp);
    }

    /**
     * perform
     *
     * @param object Object
     * @return boolean
     */
    public boolean perform(Object object) {
      int selectedRow = table.getSelectedRow();
      if (selectedRow > 0 && selectedRow <= table.getRowCount()) {
        Element el = (Element) document.getRootElement().getChildren().get(
            selectedRow);

        swap( (Element) document.getRootElement().getChildren().get(selectedRow),
             (Element) document.getRootElement().getChildren().get(selectedRow -
            1));
        table.getSelectionModel().setLeadSelectionIndex(selectedRow - 1);
        JComponentUtilities.updateUI(table);
      }
      return true;
    }
  }

  class MoveClassDownAction
      extends IDEAbstractAction {

    public MoveClassDownAction() {
      putValue(Action.NAME, "Move down");
      putValue(Action.SHORT_DESCRIPTION,
               "Moves the selected " + configurable + " up in the list");
      putValue(Action.LONG_DESCRIPTION, "");
      putValue(Action.SMALL_ICON, IconFactory.MoveDown);
    }

    /**
     * perform
     *
     * @param object Object
     * @return boolean
     */
    public boolean perform(Object object) {
      int selectedRow = table.getSelectedRow();
      if (selectedRow >= 0 && selectedRow < table.getRowCount() - 1) {
        Element el = (Element) document.getRootElement().getChildren().get(
            selectedRow);

        swap( (Element) document.getRootElement().getChildren().get(selectedRow),
             (Element) document.getRootElement().getChildren().get(selectedRow +
            1));
        table.getSelectionModel().setLeadSelectionIndex(selectedRow + 1);
        JComponentUtilities.updateUI(table);
      }
      return true;
    }
  }

  public void swap(Element el1, Element el2) {
    Element temp = new Element("temp");
    ListIterator li = el1.getAttributes().listIterator();
    while (li.hasNext()) {
      Attribute attr = (Attribute) li.next();
      temp.getAttributes().add(new Attribute(attr.getName(), attr.getValue()));
    }
    li = el2.getAttributes().listIterator();
    while (li.hasNext()) {
      Attribute attr = (Attribute) li.next();
      el1.getAttribute(attr.getName()).setValue(attr.getValue());
    }
    li = temp.getAttributes().listIterator();
    while (li.hasNext()) {
      Attribute attr = (Attribute) li.next();
      el2.getAttribute(attr.getName()).setValue(attr.getValue());
    }
    temp = null;
  }

}
