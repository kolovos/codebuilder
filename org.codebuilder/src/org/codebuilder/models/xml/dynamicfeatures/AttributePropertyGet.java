package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import org.jdom.*;
import org.codebuilder.models.xml.*;
import java.util.*;

public class AttributePropertyGet implements DynamicPropertyGet{

  private String name = "";

  public AttributePropertyGet() {
  }

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @return boolean
   */
  public boolean accept(Object obj, String name) {
    if (obj instanceof Element && ((Element) obj).getAttribute(name) != null){
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
    try{
      Attribute attribute = ( (Element) object).getAttribute(this.name);

      if (attribute instanceof IdrefAttribute) {
        return ReferenceManager.getInstance().getElementById(attribute);
      }
      else if (attribute instanceof IdrefsAttribute) {
        List list = ReferenceManager.getInstance().getElementsById(attribute);
        return list;
      }
      else {
        return attribute.getValue();
      }
    }
    catch(Exception ex){
      ex.printStackTrace();
    }
    return "";
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
