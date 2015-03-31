package org.codebuilder.template.introspection.builtin.string;

import org.codebuilder.template.introspection.*;
import org.codebuilder.template.introspection.AbstractDynamicMethod;

public class FirstToUpperCaseMethod
    extends AbstractDynamicMethod {
  public FirstToUpperCaseMethod() {
  }

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @param args Object[]
   * @return boolean
   */
  public boolean accept(Object obj){
    return obj instanceof String;
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "Converts the first letter of the string to uppercase. e.g. abc -> Abc";
  }

  /**
   * invoke
   *
   * @param object Object
   * @param objectArray Object[]
   * @return Object
   */
  public Object invoke(Object object, Object[] objectArray) {
    if (object == null) return "";
    String str = object.toString();
    if (str.length() == 0) {
      return str;
    }
    str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    return str;
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "firstToUpperCase";
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
