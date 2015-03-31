package org.codebuilder.template.introspection.builtin.string;

import org.codebuilder.template.introspection.DynamicPropertyGet;

public class CamelPropertyGet implements DynamicPropertyGet{
  public CamelPropertyGet() {
  }

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @return boolean
   */
  public boolean accept(Object obj, String name) {
    return name.compareTo("camel") == 0;
  }

  /**
   * invoke
   *
   * @param object Object
   * @return Object
   */
  public Object invoke(Object object) {
    String str = object.toString();
    if (str.length() == 0) return str;
    str = str.substring(0,1).toUpperCase() + str.substring(1, str.length());
    return str;
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
    return "camel";
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "Applies to java.lang.Object instances. Returns the string representation of the object with the first letter capitalized (e.g returns Abc for abc)";
  }

  /**
   * getSignature
   *
   * @return String
   */
  public String getSignature() {
    return "";
  }

  public static void main(String[] args){
  }

}
