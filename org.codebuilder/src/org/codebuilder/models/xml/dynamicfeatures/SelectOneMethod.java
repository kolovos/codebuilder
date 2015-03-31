package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import org.jdom.*;
import org.codebuilder.common.xml.*;

public class SelectOneMethod
    extends AbstractDynamicMethod{
  public SelectOneMethod() {
    parameters.add(new Parameter("xpath", String.class));
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
    return xp.selectNode((Element) object, objectArray[0].toString());
  }

  /**
   * getMethodName
   *
   * @return String
   */
  public String getMethodName() {
    return "selectOne";
  }

  /**
   * getReturnType
   *
   * @return Class
   */
  public Class getReturnType() {
    return Element.class;
  }
}
