package org.codebuilder.template.introspection.builtin.string;

import org.codebuilder.template.introspection.*;

public class ChopFromEndMethod
    extends AbstractDynamicMethod {
  public ChopFromEndMethod() {
    parameters.add(new Parameter("numberOfCharacters", int.class));
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
    String str = object.toString();
    int howMany = new Integer(objectArray[0].toString()).intValue();
    if (str.length() > howMany) {
      return str.substring(0, str.length() - howMany);
    }
    else {
      return str;
    }

  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "chopFromEnd";
  }

  /**
   * getReturnType
   *
   * @return Class
   */
  public Class getReturnType() {
    return null;
  }
}
