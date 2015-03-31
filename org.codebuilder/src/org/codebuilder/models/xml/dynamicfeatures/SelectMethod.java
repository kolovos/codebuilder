package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import org.jdom.*;
import org.codebuilder.common.xml.*;
import org.codebuilder.template.introspection.Parameter;
import java.util.List;

public class SelectMethod extends AbstractDynamicMethod{
  public SelectMethod() {
    parameters.add(new Parameter("query", String.class));
  }

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @param args Object[]
   * @return boolean
   */
  public boolean accept(Object obj) {
    return (obj instanceof Element);
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
    XPath xp = new XPath();
    return xp.selectNodes((Element) object, objectArray[0].toString());
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "select";
  }

  /**
   * getReturnType
   *
   * @return Class
   */
  public Class getReturnType() {
    return List.class;
  }
}
