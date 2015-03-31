package org.codebuilder.ide.ui.editors.objectbrowser;

import java.lang.reflect.*;

public class ObjectMethod implements ObjectFeature{
  private Object object;
  private java.lang.reflect.Method method;
  public ObjectMethod() {
  }
  public Object getObject() {
    return object;
  }
  public void setObject(Object object) {
    this.object = object;
  }
  public java.lang.reflect.Method getMethod() {
    return method;
  }
  public void setMethod(java.lang.reflect.Method method) {
    this.method = method;
  }

  public ObjectMethod(Object object, Method method){
    this.object = object;
    this.method = method;
  }

  public Object invoke(){
    Object retval = new Object();
    try {
      retval = method.invoke(object, null);
    }
    catch (Exception ex) {
      retval = ex;
    }
    return new BrowsableObject(retval);
  }
}
