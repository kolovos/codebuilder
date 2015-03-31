package org.codebuilder.common.xml;

import org.jdom.Element;
import java.util.List;
import org.jdom.xpath.*;
import org.jdom.*;
import java.util.*;

public class XPath {

  public XPath() {
  }

  public String createExpression(String element, boolean global){
    return createExpression(element,null,null,global);
  }

  public String createExpression(String element, String attr, String attrValue, boolean global){
    StringBuffer xpath = new StringBuffer("");
    if (global) xpath.append("//");
    xpath.append(element);
    if (attr != null){
      xpath.append("[@" + attr + "='" + attrValue + "']");
    }
    return xpath.toString();
  }

  public List selectNodes(Element element, String xpath) {
      org.jdom.xpath.XPath xp = null;
      try {
        xp = org.jdom.xpath.XPath.newInstance(xpath); // org.jaxen.jdom.JDOMXPath(xpath);
        return xp.selectNodes(element);
      }
      catch (JDOMException ex) {
        return new ArrayList();
      }
  }

  public Element selectNode(Element element, String xpath) {
      org.jdom.xpath.XPath xp = null;
      try {
        xp = org.jdom.xpath.XPath.newInstance(xpath); // org.jaxen.jdom.JDOMXPath(xpath);
        return (Element) xp.selectSingleNode(element);
      }
      catch (JDOMException ex) {
        return null;
      }
  }

}
