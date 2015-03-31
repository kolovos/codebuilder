package org.codebuilder.template.introspection.builtin;

import org.codebuilder.template.introspection.*;
import org.codebuilder.template.introspection.AbstractDynamicMethod;
import java.lang.reflect.*;

public class CallByNameMethod
    extends AbstractDynamicMethod {

  public CallByNameMethod() {
    parameters.add(new Parameter("methodName", String.class));
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
    return obj instanceof Object;
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "Calls the method of the object that matches the specified name";
  }

  /**
   * invoke
   *
   * @param object Object
   * @param objectArray Object[]
   * @return Object
   */
  public Object invoke(Object object, Object[] objectArray) {

    String methodName = objectArray[0].toString();

    try {
      Method method = object.getClass().getMethod(methodName, new Class[] {});
      return method.invoke(object, new Object[]{});
    }
    catch (Exception ex) {
    }

    return null;
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "callByName";
  }

  /**
   * getReturnType
   *
   * @return Class
   */
  public Class getReturnType() {
    return Object.class;
  }
}
