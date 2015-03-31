package org.codebuilder.ide.ui.editors.objectbrowser;

import java.util.*;
import java.lang.reflect.*;
import org.codebuilder.template.introspection.DynamicIntrospectionManager;
import org.codebuilder.template.introspection.DynamicMethod;

public class BrowsableObject {
  private Object object;
  private ArrayList fields = new ArrayList();
  private ArrayList methods = new ArrayList();
  private ArrayList collectionItems = new ArrayList();

  public BrowsableObject() {
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public BrowsableObject(Object object) {
    this.object = object;
  }

  public List getMethods() {
    if (methods.size() > 0) return methods;

    Method[] objectMethods = object.getClass().getMethods();
    for (int i = 0; i < objectMethods.length; i++) {
      if (objectMethods[i].getReturnType().getName().compareTo("void") != 0 &&
          objectMethods[i].getParameterTypes().length == 0) {
        methods.add(new ObjectMethod(object, objectMethods[i]));
      }
    }
    return methods;
  }

  public List getDynamicMethods(){
      List list = new ArrayList();
      ListIterator li = DynamicIntrospectionManager.getInstance().getMethods().listIterator();
      while (li.hasNext()){
        DynamicMethod method = (DynamicMethod) li.next();
        if (method.accept(object)) list.add(method);
      }
      return list;
  }

  public List getFields() {
    if (fields.size() > 0) return fields;

    Field[] objectFields = object.getClass().getFields();
    for (int i = 0; i < objectFields.length; i++) {
      //if (objectFields[i].getModifiers() == Field.PUBLIC) {
        fields.add(new ObjectField(object, objectFields[i]));
      //}
    }
    return fields;
  }

  public List getFieldsAndMethods(){
    ArrayList fieldsAndMethods = new ArrayList();
    //fieldsAndMethods.addAll(getFields());
    fieldsAndMethods.addAll(getDynamicMethods());
    fieldsAndMethods.addAll(getMethods());
    return fieldsAndMethods;
  }

  public boolean isPrimitive(){
    return object == null || object.getClass().getName().startsWith("java.lang");
  }

  public boolean isCollection(){
    return (object instanceof Enumeration || object instanceof Collection || object instanceof Array);
  }

  public List getCollectionItems(){
    if (collectionItems.size() == 0){
      if (object instanceof Collection) {
        Iterator iterator = ( (Collection) object).iterator();
        while (iterator.hasNext()) {
          collectionItems.add(new BrowsableObject(iterator.next()));
        }
      }
      if (object instanceof Enumeration) {
        Enumeration enumeration = (Enumeration) object;
        while (enumeration.hasMoreElements()) {
          collectionItems.add(new BrowsableObject(enumeration.nextElement()));
        }
      }
    }
    return collectionItems;
  }
}
