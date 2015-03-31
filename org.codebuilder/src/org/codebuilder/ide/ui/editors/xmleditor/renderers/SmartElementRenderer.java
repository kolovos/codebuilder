package org.codebuilder.ide.ui.editors.xmleditor.renderers;

import javax.swing.tree.TreeCellRenderer;
import java.awt.Component;
import javax.swing.JTree;
import javax.swing.JLabel;
import org.jdom.Element;
import javax.swing.BorderFactory;
import java.awt.SystemColor;
import org.codebuilder.ide.ui.editors.xmleditor.*;
import java.util.*;
import org.jdom.input.*;
import java.io.IOException;
import org.jdom.JDOMException;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class SmartElementRenderer
    implements TreeCellRenderer {

  private List prefferedAttributes = null;

  private void readPrefferedAttributes() throws Exception {
      SAXBuilder builder = new SAXBuilder();
      Document document = builder.build("config/xmleditor/preffered_attributes.xml");
      ListIterator li = document.getRootElement().getChildren().listIterator();
      prefferedAttributes = new ArrayList();
      while (li.hasNext()){
        Element el = (Element) li.next();
        prefferedAttributes.add(el.getAttributeValue("name"));
      }
  }

  public SmartElementRenderer() {
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

    if (prefferedAttributes == null){
      try {
        readPrefferedAttributes();
      }
      catch (Exception ex) {
        ex.printStackTrace();
      }
    }

    ListIterator li = prefferedAttributes.listIterator();
    boolean found = false;
    String text = "";

    while (li.hasNext() && !found){
      String attr = (String) li.next();
      if (element.getAttributeValue(attr) != null && element.getAttributeValue(attr).length() > 0){
        text = element.getAttributeValue(attr);
        found = true;
      }
    }

    //if (!found) {
    //  text = element.getName();
    //}

    text = "<" + element.getName() + "> " + text;

    label.setText(text);
    label.setIcon(IconFactory.getIconFor(element.getName(),"Element"));
    return label;
  }
}
