package org.codebuilder.ide.help;

import javax.swing.tree.*;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.*;
import java.io.*;
import org.codebuilder.ide.ui.resources.*;
import java.awt.*;

public class HelpTreeCellRenderer
    implements TreeCellRenderer {

  public HelpTreeCellRenderer() {
  }

  /**
   * getTreeCellRendererComponent
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

    if (selected) {
      label.setBackground(SystemColor.textHighlight);
      label.setForeground(SystemColor.textHighlightText);
    }
    else {
      label.setBackground(SystemColor.text);
      label.setForeground(SystemColor.textText);
    }

    File file = (File) value;

    if (file.isDirectory()) {
      if (expanded) {
        label.setIcon(IconFactory.OpenFolder);
      }
      else {
        label.setIcon(IconFactory.ClosedFolder);
      }
    }
    else {
      label.setIcon(IconFactory.HelpFile);
    }

    String caption = file.getName().replaceAll(".html", "");
    if (caption.length() > 4 && caption.indexOf("_") == 4)
      caption = caption.substring(5, caption.length());
    label.setText(caption);


    if (value == tree.getModel().getRoot()){
      label.setIcon(IconFactory.Help);
      label.setText("CodeBuilder help");
    }

    return label;
  }

}
