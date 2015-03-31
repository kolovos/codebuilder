package org.codebuilder.template.introspection;

import java.util.*;
import org.codebuilder.template.introspection.builtin.string.*;
import org.codebuilder.models.xml.dynamicfeatures.*;
import org.codebuilder.template.introspection.builtin.*;

public class DynamicIntrospectionManager {

  private static DynamicIntrospectionManager instance = new DynamicIntrospectionManager();
  private ArrayList propertyGets = new ArrayList();
  private ArrayList propertySets = new ArrayList();
  private ArrayList methods = new ArrayList();
  private ArrayList iteratorProviders = new ArrayList();

  public static DynamicIntrospectionManager getInstance() {
    return instance;
  }

  private DynamicIntrospectionManager() {
    init();
  }

  private void init() {

    //built-in
    propertyGets.add(new CamelPropertyGet());
    methods.add(new ChopFromEndMethod());
    methods.add(new FirstToUpperCaseMethod());
    methods.add(new FirstToLowerCaseMethod());
    methods.add(new DebugMethod());
    methods.add(new CallByNameMethod());

    //xml
    propertyGets.add(new ElementPropertyGet());
    propertyGets.add(new AttributePropertyGet());
    methods.add(new SelectMethod());
    methods.add(new SelectOneMethod());
    methods.add(new XmlMethod());
    methods.add(new PathMethod());
    iteratorProviders.add(new ElementIteratorProvider());
    propertySets.add(new AttributePropertySet());

  }

  public Class[] getConfiguredClasses() {
    return null;
  }

  public DynamicPropertyGet getPropertyGetFor(Object obj, String name) {
      ListIterator li = propertyGets.listIterator();
      while (li.hasNext()) {
        DynamicPropertyGet dpg = (DynamicPropertyGet) li.next();
        if (dpg.accept(obj, name)){
          return dpg;
        }
      }
    return null;
  }

  public DynamicPropertySet getPropertySetFor(Object obj, String name, Object value) {
    ListIterator li = propertySets.listIterator();
    while (li.hasNext()) {
      DynamicPropertySet dps = (DynamicPropertySet) li.next();
      if (dps.accept(obj, name, value)){
        return dps;
      }
    }
    return null;
  }

  public DynamicMethod getMethodFor(Object obj, String name, Object[] args) {
    ListIterator li = methods.listIterator();
    while (li.hasNext()) {
      DynamicMethod dm = (DynamicMethod) li.next();
      if (dm.accept(obj, name, args)){
        return dm;
      }
    }

    return null;
  }

  public DynamicIteratorProvider getIteratorProviderFor(Object obj) {
    ListIterator li = iteratorProviders.listIterator();
    while (li.hasNext()) {
      DynamicIteratorProvider dip = (DynamicIteratorProvider) li.next();
      if (dip.accept(obj)){
        return dip;
      }
    }

    return null;


  }
  public ArrayList getIteratorProviders() {
    return iteratorProviders;
  }
  public ArrayList getMethods() {
    return methods;
  }
  public ArrayList getPropertyGets() {
    return propertyGets;
  }
  public ArrayList getPropertySets() {
    return propertySets;
  }

}
