package org.codebuilder.template.introspection;

public class DynamicPropertyGetMethod implements DynamicPropertyGet{

  private DynamicMethod method;
  private String name = "";

  public DynamicPropertyGetMethod(DynamicMethod method) {
    this.method = method;
  }

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @return boolean
   */
  public boolean accept(Object obj, String name) {
    return false;
  }

  /**
   * invoke
   *
   * @param object Object
   * @return Object
   */
  public Object invoke(Object object) {
    Object result = null;
    try{
      result = method.invoke(object, new Object[] {});
    }
    catch(Exception ex){ex.printStackTrace();}
    return result;
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
