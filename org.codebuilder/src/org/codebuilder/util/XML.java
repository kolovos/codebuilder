package org.codebuilder.util;

import org.jdom.Element;
import java.io.StringWriter;
import java.util.List;
import java.io.IOException;
import org.jdom.output.XMLOutputter;
import org.jdom.*;

public class XML {

  public XML() {
  }

  public String getTagName(Element element){
    return element.getName();
  }

  public boolean tagNameIs(Element element, String name){
    return (element.getName().compareTo(name) == 0);
  }
/*
  public String attribute_values_path(Element element, String attribute, Element start, String delimiter){
    if (element == start)
      return "";
    else
      return attribute_values_path((Element) element.getParent(), attribute, start, delimiter) + element.getAttributeValue(attribute) + delimiter;
  }
*/
  public String getAttributePath(Element element, String attribute, Element start, String delimiter){
    if (element.getParent() == start)
      return element.getAttributeValue(attribute);
    else if (element == start)
      return "";
    else
      return getAttributePath((Element) element.getParent(), attribute, start, delimiter) + delimiter + element.getAttributeValue(attribute);
  }

  public String getStaticPath(Element element, String staticString, Element start, String delimiter){
    //if (element.getParent() == start)
    //  return staticString;
    //else
    //  return getStaticPath((Element) element.getParent(), staticString, start, delimiter) + delimiter + staticString;
    String str = "";
    while (element.getParent() != start){
      element = (Element) element.getParent();
      str = str + staticString + delimiter;
    }
    return str;
  }

  public String getStaticPath(Element element, String staticString, String delimiter){
    return getStaticPath(element, staticString, element.getDocument().getRootElement(), delimiter);
  }

  public String getAttributePath(Element element, String attribute, String delimiter){
    return getAttributePath(element, attribute, element.getDocument().getRootElement(), delimiter);
  }

  public String getStaticAttributePath(Element element, String attribute, String staticString, String delimiter){
    return getStaticPath(element, staticString, delimiter) + delimiter + getAttributePath(element, attribute, delimiter);
  }

  public String getXml(Object o) throws IOException {
  StringWriter w = new StringWriter();
  XMLOutputter outputter = new XMLOutputter();
  outputter.getFormat().setIndent("    ");
  outputter.getFormat().setLineSeparator("\r\n");
  if (o instanceof Element){
    outputter.output((Element) o, w);
  }
  else if (o instanceof List){
    outputter.output((List) o, w);
  }
  else {
    w.write("Object " + o.toString() + " not a valid object");
  }
  return w.toString();
}

}
