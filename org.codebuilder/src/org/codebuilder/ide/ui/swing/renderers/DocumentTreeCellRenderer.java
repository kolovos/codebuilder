package org.codebuilder.ide.ui.swing.renderers;

import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import org.codebuilder.project.*;

public class DocumentTreeCellRenderer
    implements TreeCellRenderer {

  public DocumentTreeCellRenderer() {
  }

  /**
   * getTreeCellRendererCompo
   * nent
   *
   * @param tree JTree
   * @param value Object
   * @param selected boolean
   * @param expanded boolean
   * @param leaf boolean
   * @param row int
   * @param hasFocus boolean
   * @return Component
   */
  public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                boolean selected,
                                                boolean expanded, boolean leaf,
                                                int row, boolean hasFocus) {
    JLabel label = new JLabel();
    label.setOpaque(true);

    // ---------------------
    // Arrange the back and
    // fore color
    // ---------------------

    if (selected) {
      label.setBackground(SystemColor.textHighlight);
      label.setForeground(SystemColor.textHighlightText);
    }
    else {
      label.setBackground(SystemColor.text);
      label.setForeground(SystemColor.textText);
    }

    Document document = (Document) value;

    label.setText(document.getName());
    label.setIcon(document.getIcon());
    label.setToolTipText(document.getPath());
    if (document instanceof Project) label.setFont(label.getFont().deriveFont(Font.BOLD));
    if (document instanceof GeneratedFile) label.setText(label.getText() + " (" + document.getPath() + ")");
    return label;
  }
}
