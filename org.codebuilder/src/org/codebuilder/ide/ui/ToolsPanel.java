package org.codebuilder.ide.ui;

import javax.swing.*;
import java.awt.*;
import org.codebuilder.project.*;
import java.util.*;
import javax.swing.event.ListDataListener;
import org.codebuilder.ide.ui.swing.*;
import java.lang.reflect.*;
import org.codebuilder.ide.ui.resources.*;
import javax.swing.border.*;
import org.codebuilder.util.ReflectionUtil;

public class ToolsPanel
    extends JPanel {

  JComboBox contextComboBox = new JComboBox();
  Object selectedObject = new Object();
  JList contextList = new JList();
  TreeMap tools = new Tools().getTools();

  public ToolsPanel() {

    this.setLayout(new BorderLayout());
    contextComboBox.setRenderer(new ToolsComboBoxRenderer());
    contextComboBox.setModel(new ToolsComboBoxModel());
    contextList.setModel(new ToolsListModel());
    contextList.setCellRenderer(new ToolsListCellRenderer());
    contextComboBox.setSelectedIndex(0);
    this.add(contextComboBox, BorderLayout.NORTH);
    JScrollPane scrollToolsList = new JScrollPane(contextList);
    scrollToolsList.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(2,0,0,0), scrollToolsList.getBorder()));
    this.add(scrollToolsList, BorderLayout.CENTER);



  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception ex) {
    }
    JFrame d = ComponentFactory.createJFrame("ContextPanel test");
    d.getContentPane().add(new ToolsPanel(), BorderLayout.CENTER);
    d.setVisible(true);
  }

  class ToolsComboBoxModel
      implements ComboBoxModel {

    /**
     * getSelectedItem
     *
     * @return Object
     */
    public Object getSelectedItem() {
      return selectedObject;
    }

    /**
     * setSelectedItem
     *
     * @param anItem Object
     */
    public void setSelectedItem(Object anItem) {
      selectedObject = anItem;
      JComponentUtilities.updateUI(contextList);
    }

    /**
     * getSize
     *
     * @return int
     */
    public int getSize() {
      return tools.keySet().size();
    }

    /**
     * getElementAt
     *
     * @param index int
     * @return Object
     */
    public Object getElementAt(int index) {
      return tools.keySet().toArray()[index];
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

  class ToolsComboBoxRenderer
      implements ListCellRenderer {
    /**
     * getListCellRendererComponent
     *
     * @param list JList
     * @param value Object
     * @param index int
     * @param isSelected boolean
     * @param cellHasFocus boolean
     * @return Component
     */
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected,
                                                  boolean cellHasFocus) {
      JLabel label = new JLabel();
      label.setOpaque(true);
      if (cellHasFocus || isSelected) {
        label.setBackground(SystemColor.textHighlight);
        label.setForeground(SystemColor.textHighlightText);
      }
      else {
        label.setBackground(SystemColor.text);
        label.setForeground(SystemColor.textText);
      }
      label.setIcon(IconFactory.Clazz);
      label.setText(value + " - " + tools.get(value).getClass().getName());
      return label;
    }

  }

  class ToolsListModel
      implements ListModel {
    /**
     * getSize
     *
     * @return int
     */
    public int getSize() {
      return tools.get(selectedObject).getClass().getDeclaredMethods().length;
    }

    /**
     * getElementAt
     *
     * @param index int
     * @return Object
     */
    public Object getElementAt(int index) {
      return tools.get(selectedObject).getClass().getDeclaredMethods()[index];
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

  class ToolsListCellRenderer
      implements ListCellRenderer {
    /**
     * getListCellRendererComponent
     *
     * @param list JList
     * @param value Object
     * @param index int
     * @param isSelected boolean
     * @param cellHasFocus boolean
     * @return Component
     */
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected,
                                                  boolean cellHasFocus) {
      JLabel label = new JLabel();
      label.setOpaque(true);
      if (cellHasFocus || isSelected) {
        label.setBackground(SystemColor.textHighlight);
        label.setForeground(SystemColor.textHighlightText);
      }
      else {
        label.setBackground(SystemColor.text);
        label.setForeground(SystemColor.textText);
      }

      Method method = (Method) value;
      String signature = ReflectionUtil.getMethodSignature(method);
      label.setIcon(IconFactory.Method);
      label.setText(signature);
      return label;

    }

  }
}
