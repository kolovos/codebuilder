package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import org.jdom.*;
import org.jdom.output.*;
import java.io.*;
import java.util.*;

public class XmlMethod extends AbstractDynamicMethod{
  public XmlMethod() {
  }

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @param args Object[]
   * @return boolean
   */
  public boolean accept(Object obj) {
    return (obj instanceof Element);
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "";
  }

  /**
   * invoke
   *
   * @param object Object
   * @param objectArray Object[]
   * @return Object
   */
  public Object invoke(Object object, Object[] objectArray) {
    XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
    StringWriter stringWriter = new StringWriter();
    if (object instanceof Element){
      try {
        outputter.output( (Element) object, stringWriter);
      }
      catch (IOException ex) {
      }
    }
    if (object instanceof List){
      try {
        outputter.output( (List) object, stringWriter);
      }
      catch (IOException ex) {
      }
    }
    return stringWriter.toString();
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "getXml";
  }

  /**
   * getReturnType
   *
   * @return Class
   */
  public Class getReturnType() {
    return String.class;
  }
}
