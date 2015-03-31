package org.codebuilder.template.introspection.builtin.string;

import org.codebuilder.template.introspection.*;
import org.codebuilder.template.introspection.AbstractDynamicMethod;
import org.codebuilder.ide.feedback.*;

public class DebugMethod
    extends AbstractDynamicMethod {
  public DebugMethod() {
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
    return true;
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "Prints the object into the debug console";
  }

  /**
   * invoke
   *
   * @param object Object
   * @param objectArray Object[]
   * @return Object
   */
  public Object invoke(Object object, Object[] objectArray) {
    FeedbackManager.getInstance().report(object.toString(), "", FeedbackSeverity.DEBUG);
    return "";
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "debug";
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
