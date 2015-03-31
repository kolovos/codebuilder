package org.codebuilder.ide.ui.dialogs.document;

import javax.swing.ListCellRenderer;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.*;
import org.codebuilder.project.*;
import java.awt.*;
import javax.swing.border.*;

public class DocumentTypesListCellRenderer
    implements ListCellRenderer {
  public DocumentTypesListCellRenderer() {
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
    label.setBorder(new EmptyBorder(2,2,2,2));

    label.setOpaque(true);
    if (cellHasFocus || isSelected) {
      label.setBackground(SystemColor.textHighlight);
      label.setForeground(SystemColor.textHighlightText);
    }
    else {
      label.setBackground(SystemColor.text);
      label.setForeground(SystemColor.textText);
    }

    Document document = (Document) value;
    label.setText(document.getShortDescription());
    label.setIcon(document.getIcon());
    return label;
  }
}
