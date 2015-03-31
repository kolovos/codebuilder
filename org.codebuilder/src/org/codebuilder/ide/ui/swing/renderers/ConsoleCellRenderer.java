package org.codebuilder.ide.ui.swing.renderers;

import javax.swing.table.*;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.*;
import java.awt.*;
import org.codebuilder.ide.feedback.*;

public class ConsoleCellRenderer
    implements ListCellRenderer {

  public ConsoleCellRenderer() {
  }

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

    if (isSelected) {
      label.setBackground(SystemColor.textHighlight);
      label.setForeground(SystemColor.textHighlightText);
    }
    else {
      label.setBackground(SystemColor.text);
      label.setForeground(SystemColor.textText);
    }

    Feedback feedback = (Feedback) value;

    label.setText(feedback.getMessage());
    label.setToolTipText(feedback.getDetails());
    label.setIcon(feedback.getSeverity().getIcon());

    return label;
  }

}
