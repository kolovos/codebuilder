package org.codebuilder.ide.ui.editors.xmleditor;

import javax.swing.tree.TreeCellRenderer;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.JLabel;
import org.jdom.Element;
import javax.swing.BorderFactory;
import java.awt.SystemColor;
import org.codebuilder.ide.ui.resources.IconFactory;

public class DocumentTreeCellRenderer
    implements TreeCellRenderer {
  public DocumentTreeCellRenderer() {
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
    Element element = (Element) value;
    label.setOpaque(true);

    if (selected) {
      label.setBackground(SystemColor.activeCaption);
      label.setForeground(SystemColor.textHighlightText);
    }
    else {
      label.setBackground(SystemColor.text);
      label.setForeground(SystemColor.textText);
    }

    String text = "";

    if (text == null || text.length() == 0) {
      text = element.getAttributeValue("path");
    }
    if (text == null || text.length() == 0) {
      text = element.getAttributeValue("name");
    }
    //if (text == null || text.length() == 0) {
    //  text = element.getName();
    //}

    text = "<" + element.getName() + "> " + text;

    label.setText(text);
    label.setIcon(IconFactory.getIconFor(element.getName()));
    return label;
  }
}
