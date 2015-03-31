package org.codebuilder.ide.ui.dialogs.tools;

import javax.swing.*;
import java.awt.Component;
import java.awt.*;
import javax.swing.table.*;

public class ToolsCellRenderer implements TableCellRenderer{
  public ToolsCellRenderer() {
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

    label.setText(value.toString());
    return label;

  }
}
