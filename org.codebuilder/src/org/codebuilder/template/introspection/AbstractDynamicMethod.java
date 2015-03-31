package org.codebuilder.template.introspection;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

abstract public class AbstractDynamicMethod implements DynamicMethod{

  protected ArrayList parameters = new ArrayList();

  public AbstractDynamicMethod() {
  }

  /**
   * getParameters
   *
   * @return List
   */
  public List getParameters() {
    return parameters;
  }

  /**
   * accept
   *
   * @param obj Object
   * @return boolean
   */
  public abstract boolean accept(Object obj);

  /**
   * accept
   *
   * @param obj Object
   * @param name String
   * @param args Object[]
   * @return boolean
   */
  public boolean accept(Object obj, String name, Object[] args) {
    return accept(obj) && args.length == parameters.size() && name.compareTo(getMethodName()) == 0;
  }

  /**
   * getDescription
   *
   * @return String
   */
  public abstract String getDescription();

  /**
   * invoke
   *
   * @param object Object
   * @param objectArray Object[]
   * @return Object
   */
  public abstract Object invoke(Object object, Object[] objectArray);

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
  public abstract String getMethodName();

  /**
   * getReturnType
   *
   * @return Class
   */
  public abstract Class getReturnType();


  public String getSignature(){
    StringBuffer signature = new StringBuffer();
    signature.append(this.getMethodName());
    signature.append("(");
    ListIterator li = parameters.listIterator();
    while (li.hasNext()){
      Parameter parameter = (Parameter) li.next();
      signature.append(parameter.getClazz().getName());
      signature.append(" ");
      signature.append(parameter.getName());
      if (li.hasNext()) signature.append(", ");
    }
    signature.append(")");
    signature.append(" : ");
    signature.append(getReturnType().getName());
    return signature.toString();
  }
}
