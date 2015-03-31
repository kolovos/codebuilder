package org.codebuilder.util;

import java.util.*;

public class ObjectUtilities {

  public ObjectUtilities() {
  }

  public Object createObject(String classname) {
    try {
      return Class.forName(classname).newInstance();
    }
    catch (Exception ex) {
      return null;
    }
  }

  public boolean isTypeOf(Object obj, String classname) {
    return obj.getClass().getName().compareTo(classname) == 0;
  }

  public boolean isNull(Object obj) {
    return obj == null;
  }

  public void setNull(Object obj) {
    obj = null;
  }

  public String getClassName(Object obj){
    return obj.getClass().getName();
  }

  public boolean isEmpty(Object obj){
    if (obj == null)
      return true;
    if (obj instanceof Collection)
      return ((Collection) obj).size() == 0;
    if (obj instanceof String)
      return ((String) obj).length() == 0;
    return false;
  }
}
