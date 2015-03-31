package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import org.jdom.*;
import java.util.*;

public class ElementPropertyGet implements DynamicPropertyGet{

  private String name = "";

  public ElementPropertyGet() {
  }

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @return boolean
   */
  public boolean accept(Object obj, String name) {
    if (obj instanceof Element && ((Element) obj).getChildren(name).size() > 0){
      this.name = name;
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * invoke
   *
   * @param object Object
   * @return Object
   */
  public Object invoke(Object object) {
    List children = ((Element) object).getChildren(name);
    if (children.size() > 1){
      return children;
    }
    else{
      return ((Element) object).getChild(name);
    }
  }

  /**
   * isCacheable
   *
   * @return boolean
   */
  public boolean isCacheable() {
    return false;
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "";
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "";
  }
}
