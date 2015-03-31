package org.codebuilder.ide.ui.editors.xmleditor;

import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.SystemColor;
import org.codebuilder.ide.ui.resources.IconFactory;

public class AttributeCellRenderer implements TableCellRenderer{
  public AttributeCellRenderer() {
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
    label.setFocusable(false);
    label.setText(value.toString());

    if (isSelected) {
      label.setBackground(SystemColor.activeCaption);
      label.setForeground(SystemColor.textHighlightText);
    }
    else {
      label.setBackground(SystemColor.text);
      label.setForeground(SystemColor.textText);
    }

    label.setIcon(IconFactory.getIconFor(value.toString(), "Attribute"));

    return label;
  }
}
