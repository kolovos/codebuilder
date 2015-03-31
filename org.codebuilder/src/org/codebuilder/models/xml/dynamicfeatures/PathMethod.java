package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import org.jdom.*;
import org.codebuilder.template.introspection.AbstractDynamicMethod;

public class PathMethod extends AbstractDynamicMethod{
  public PathMethod() {
    parameters.add(new Parameter("attribute", String.class));
    parameters.add(new Parameter("delimiter", String.class));
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
    Element element = (Element) object;
    String attribute = objectArray[0].toString();
    String delimiter = objectArray[1].toString();
    String path = "";
    while (element.getParent() != null && element.getParent() instanceof Element){
      path = element.getAttribute(attribute).getValue() + delimiter + path;
      element = (Element) element.getParent();
    }
    if (path.endsWith(delimiter)){
      path = path.substring(0, path.length() - 1);
    }
    return path;
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "getPath";
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
