package org.codebuilder.ide.ui.swing.renderers;

import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.*;

public class IconCellRenderer implements TableCellRenderer{
  public IconCellRenderer() {
  }

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
                                                 boolean isSelected,
                                                 boolean hasFocus, int row,
                                                 int column) {

    JLabel label = new JLabel();

    label.setIcon((ImageIcon) value);

    return label;
  }
}
