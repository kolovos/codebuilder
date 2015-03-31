package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import org.jdom.*;

public class AttributePropertySet implements DynamicPropertySet{

  private String name = "";
  private Object value = null;

  public AttributePropertySet() {
  }

  /**
   * invoke
   *
   * @param object Object
   * @param object1 Object
   * @return Object
   */
  public Object invoke(Object object, Object object1) {
    Element el = (Element) object;
    if (value == null) return "";
    if (el.getAttribute(this.name) == null){
      el.getAttributes().add(new Attribute(name,value.toString()));
    }
    else {
      el.getAttribute(this.name).setValue(value.toString());
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
   * accept
   *
   * @param obj Object
   * @param name String
   * @param value Object
   * @return boolean
   */
  public boolean accept(Object obj, String name, Object value) {

    if (obj instanceof Element){
      this.name = name;
      this.value = value;
      return true;
    }
    else {
      return false;
    }
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
