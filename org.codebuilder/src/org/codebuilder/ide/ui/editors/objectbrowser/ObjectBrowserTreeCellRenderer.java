package org.codebuilder.ide.ui.editors.objectbrowser;

import javax.swing.tree.*;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.*;
import java.lang.reflect.*;
import java.awt.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.template.introspection.DynamicMethod;

public class ObjectBrowserTreeCellRenderer
    implements TreeCellRenderer {
  public ObjectBrowserTreeCellRenderer() {
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

    if (value instanceof ObjectMethod) {
      Method method = ( (ObjectMethod) value).getMethod();
      label.setText(method.getName() + "() : " + method.getReturnType());
      label.setIcon(IconFactory.getIconFor("method"));
    }
    else if (value instanceof DynamicMethod){
      label.setText(((DynamicMethod) value).getSignature());
      label.setIcon(IconFactory.getIconFor("DynamicMethod"));
    }
    else if (value instanceof ObjectField) {
      Field field = ( (ObjectField) value).getField();
      label.setText(field.getName() + " : " + field.getType());
      label.setIcon(IconFactory.getIconFor("field"));
    }
    else if (value instanceof BrowsableObject) {
      Object object = ( (BrowsableObject) value).getObject();
      if (object == null) {
        label.setText("null");
        label.setIcon(IconFactory.getIconFor("null"));
      }
      else {
        if (object.getClass().getName().startsWith("java.lang")) {
          label.setText(object.toString());
          label.setIcon(IconFactory.getIconFor("value"));
        }
        else {
          if (object instanceof Exception) {
            label.setText( ( (Exception) object).getMessage());
            label.setIcon(IconFactory.getIconFor("null"));
          }
          else {
            label.setText(object.toString());
            if ( ( (BrowsableObject) value).isCollection()) {
              label.setIcon(IconFactory.getIconFor("collection"));
            }
            else {
              label.setIcon(IconFactory.getIconFor("class"));
            }
          }
        }

      }
    }
    return label;
  }
}
