package org.codebuilder.models.xml;

import java.io.*;
import javax.swing.*;
import org.codebuilder.ide.ui.resources.*;
import org.jdom.*;
import org.jdom.input.*;
import org.codebuilder.project.Model;
import org.codebuilder.project.DocumentValidationException;
import org.jdom.xpath.*;
import java.util.*;
import org.codebuilder.project.BooleanProperty;

public class XMLModel
    extends Model {

  protected Element content = null;
  protected org.jdom.Document document = null;

  public XMLModel() {
    properties.add(new BooleanProperty("simplify", "true",
        "Remove namespaces and simplify element and attribute names"));
  }

  /**
   * getIcon
   *
   * @return ImageIcon
   */
  public Icon getIcon() {
    return IconFactory.XMLModel;
  }

  /**
   * validate
   */
  public void validate() throws DocumentValidationException {

  }

  public void reloadContent() throws DocumentValidationException {
    SAXBuilder builder = new SAXBuilder();
    if (properties.get("simplify").getBooleanValue()){
      builder.setFactory(new IdrefDocumentFactory());
    }

    try {
      document = builder.build(this.getPath());
      if (document != null) {
        if (properties.get("simplify").getBooleanValue()){
          simplify();
        }
        content = document.getRootElement();
      }
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    catch (JDOMException ex) {
      if (ex instanceof JDOMParseException) {
        JDOMParseException pex = (JDOMParseException) ex;
        throw new DocumentValidationException(pex.getLineNumber(),
                                              pex.getColumnNumber(),
                                              pex.getMessage(), pex);
      }
      ex.printStackTrace();
    }
  }

  private void simplify() throws JDOMException{
    XPath xpath = XPath.newInstance("//*");
    List allNodes = xpath.selectNodes(document);
    ListIterator li = allNodes.listIterator();
    while (li.hasNext()) {
      Element el = (Element) li.next();
      el.setNamespace(null);
      ListIterator lia = el.getAttributes().listIterator();
      while (lia.hasNext()) {
        Attribute attr = (Attribute) lia.next();
        attr.setNamespace(null);
        String[] parts = attr.getName().split("\\.");
        if (parts.length > 0) {
          attr.setName(parts[parts.length - 1]);
        }
      }
      String[] parts = el.getName().split("\\.");
      if (parts.length > 0) {
        el.setName(parts[parts.length - 1]);
      }
    }
  }

  public Object getContent() {
    if (content == null) {
      try {
        reloadContent();
      }
      catch (DocumentValidationException ex) {
        return ex;
      }
    }
    return content;
  }

  public void setContent(Element content) {
    this.content = content;
  }

  public void reset() {
    this.content = null;
  }

  public static void main(String[] args) {
    Model model = new XMLModel();
    model.setPath("demo.xml");
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "eXtensible Markup Language (XML) document";
  }

  /**
   * getType
   *
   * @return String
   */
  public String getType() {
    return "XMLModel";
  }

  /**
   * getSuffix
   */
  public String getSuffix() {
    return "xml";
  }

  /**
   * getShortDescription
   *
   * @return String
   */
  public String getShortDescription() {
    return "XML document";
  }

}
