package org.codebuilder.template.introspection.builtin.string;

import org.codebuilder.template.introspection.*;
import org.codebuilder.template.introspection.AbstractDynamicMethod;

public class FirstToLowerCaseMethod
    extends AbstractDynamicMethod {
  public FirstToLowerCaseMethod() {
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
    return "Converts the first letter of the string to lowercase. e.g. Abc -> abc";
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
    str = str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
    return str;
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "firstToLowerCase";
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
