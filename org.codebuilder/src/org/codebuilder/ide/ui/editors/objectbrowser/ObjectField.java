package org.codebuilder.ide.ui.editors.objectbrowser;

import java.lang.reflect.*;

public class ObjectField implements ObjectFeature{
  private Object object;
  private java.lang.reflect.Field field;
  public ObjectField() {
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public java.lang.reflect.Field getField() {
    return field;
  }

  public void setField(java.lang.reflect.Field field) {
    this.field = field;
  }

  public ObjectField(Object object, Field field) {
    this.object = object;
    this.field = field;
  }

  public Object invoke(){
    Object retval = null;
    try {
      retval = field.get(object);
    }
    catch (Exception ex) {
      retval = ex;
    }
    return new BrowsableObject(retval);
  }
}
