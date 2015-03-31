package org.codebuilder.ide.ui.swing.renderers;

import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.util.EventObject;
import javax.swing.event.CellEditorListener;
import java.awt.SystemColor;
import java.awt.Color;

public class SystemLogCellRenderer
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
                                                 boolean isSelected,
                                                 boolean hasFocus, int row,
                                                 int column) {

    String text = value.toString();
    JLabel label = new JLabel();

    // ---------------------
    // Arrange the back and
    // fore color
    // ---------------------

    /*
    if (isSelected) {
      //label.setBackground(SystemColor.textHighlight);
      label.setBackground(Color.black);
      label.setForeground(SystemColor.textHighlightText);
    }
    else {
      label.setBackground(SystemColor.text);
      label.setForeground(SystemColor.textText);
    }
*/
    label.setText(text);
    label.setToolTipText("<html><div border='5' width='210'>"
                         + text +
                         "</div></html>");
    return label;
  }

}
