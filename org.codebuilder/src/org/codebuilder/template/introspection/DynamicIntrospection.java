package org.codebuilder.template.introspection;

/*
 * Copyright 2002,2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.reflect.*;
import java.util.*;
import org.apache.velocity.runtime.*;
import org.apache.velocity.runtime.parser.node.*;
import org.apache.velocity.util.*;
import org.apache.velocity.util.introspection.*;

/**
 *  Implementation of Uberspect to provide the default introspective
 *  functionality of Velocity
 *
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @version $Id: UberspectImpl.java,v 1.2.4.1 2004/03/03 23:23:08 geirm Exp $
 */

public class DynamicIntrospection
    implements Uberspect, UberspectLoggable {
  /**
   *  Our runtime logger.
   */
  private RuntimeLogger rlog;

  /**
   *  the default Velocity introspector
   */
  private static Introspector introspector;

  /**
   * init - does nothing - we need to have setRuntimeLogger called before getting our introspector, as the default vel introspector depends upon it.
   *
   * @throws Exception
   */
  public void init() throws Exception {
  }

  /**
   * Sets the runtime logger - this must be called before anything else besides init() as to get the logger. Makes the pull model appealing...
   *
   * @param runtimeLogger RuntimeLogger
   */
  public void setRuntimeLogger(RuntimeLogger runtimeLogger) {
    rlog = runtimeLogger;
    introspector = new Introspector(rlog);
  }

  /**
   * To support iteratives - #foreach()
   *
   * @param obj Object
   * @param i Info
   * @throws Exception
   * @return Iterator
   */
  public Iterator getIterator(Object obj, Info i) throws Exception {

    DynamicIntrospectionManager dim = DynamicIntrospectionManager.getInstance();
    DynamicIteratorProvider dip = dim.getIteratorProviderFor(obj);
    if (dip != null) {
      return dip.getIterator(obj, i);
    }

    if (obj == null) {
      return new ArrayList().listIterator();
    }

    if (obj.getClass().isArray()) {
      return new ArrayIterator(obj);
    }

    if (obj instanceof List) {
      List list = (List) obj;
      return list.listIterator();
    }

    if (obj instanceof Collection) {
      //System.out.println(((Collection) obj).iterator());
      return ( (Collection) obj).iterator();
    }

    if (obj instanceof Map) {
      return ( (Map) obj).values().iterator();
    }

    if (obj instanceof Iterator) {
      rlog.warn("Warning! The iterative "
                + " is an Iterator in the #foreach() loop at ["
                + i.getLine() + "," + i.getColumn() + "]"
                + " in template " + i.getTemplateName()
                + ". Because it's not resetable,"
                + " if used in more than once, this may lead to"
                + " unexpected results.");

      return ( (Iterator) obj);
    }

    if (obj instanceof Enumeration) {
      rlog.warn("Warning! The iterative "
                + " is an Enumeration in the #foreach() loop at ["
                + i.getLine() + "," + i.getColumn() + "]"
                + " in template " + i.getTemplateName()
                + ". Because it's not resetable,"
                + " if used in more than once, this may lead to"
                + " unexpected results.");

      return new EnumerationIterator( (Enumeration) obj);
    }

    return null;

  }

  /**
   * Method
   *
   * @param obj Object
   * @param methodName String
   * @param args Object[]
   * @param i Info
   * @throws Exception
   * @return VelMethod
   */
  public VelMethod getMethod(Object obj, String methodName, Object[] args,
                             Info i) throws Exception {
    try {
      if (obj == null) {
        return null;
      }

      DynamicIntrospectionManager dim = DynamicIntrospectionManager.getInstance();
      DynamicMethod dm = dim.getMethodFor(obj, methodName, args);
      if (dm != null) {
        return dm;
      }

      Method m = introspector.getMethod(obj.getClass(), methodName, args);
      if (m != null) {
        return new VelMethodImpl(m);
      }

    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

    return null;
  }

  /**
   * Property getter
   *
   * @param obj Object
   * @param identifier String
   * @param i Info
   * @throws Exception
   * @return VelPropertyGet
   */
  public VelPropertyGet getPropertyGet(Object obj, String identifier, Info i) throws
      Exception {
    AbstractExecutor executor;

    Class claz = obj.getClass();

    DynamicIntrospectionManager dim = DynamicIntrospectionManager.getInstance();
    DynamicPropertyGet dpg = dim.getPropertyGetFor(obj, identifier);
    if (dpg != null) {
      return dpg;
    }

    //DynamicMethod dm = dim.getMethodFor(obj, "get" + new CamelPropertyGet().invoke(identifier).toString(), new Object[]{});
    //if (dm != null){
    //  return new DynamicPropertyGetMethod(dm);
    //}

    //dm = dim.getMethodFor(obj, "is" + new CamelPropertyGet().invoke(identifier).toString(), new Object[]{});
    //if (dm != null){
    //  return new DynamicPropertyGetMethod(dm);
    //}

    /*
     *  if that didn't work, look for get("foo")
     */

    executor = new GetExecutor(rlog, introspector, claz, identifier);

    /*
     *  first try for a getFoo() type of property
     *  (also getfoo() )
     */

    if (executor.isAlive() == false) {
      //executor = new GetExecutor(rlog, introspector, claz, identifier);
      executor = new PropertyExecutor(rlog, introspector, claz, identifier);
    }

    /*
     *  finally, look for boolean isFoo()
     */

    if (executor.isAlive() == false) {
      executor = new BooleanPropertyExecutor(rlog, introspector, claz,
                                             identifier);
    }

    if (executor != null) {
      return new VelGetterImpl(executor);
    }

    return null;
  }

  /**
   * Property setter
   *
   * @param obj Object
   * @param identifier String
   * @param arg Object
   * @param i Info
   * @throws Exception
   * @return VelPropertySet
   */
  public VelPropertySet getPropertySet(Object obj, String identifier,
                                       Object arg, Info i) throws Exception {

    DynamicIntrospectionManager dim = DynamicIntrospectionManager.getInstance();
    DynamicPropertySet dps = dim.getPropertySetFor(obj, identifier, arg);
    if (dps != null) {
      return dps;
    }


    Class claz = obj.getClass();

    VelPropertySet vs = null;
    VelMethod vm = null;
    try {
      /*
       *  first, we introspect for the set<identifier> setter method
       */

      Object[] params = {
          arg};

      try {
        vm = getMethod(obj, "set" + identifier, params, i);

        if (vm == null) {
          throw new NoSuchMethodException();
        }
      }
      catch (NoSuchMethodException nsme2) {
        StringBuffer sb = new StringBuffer("set");
        sb.append(identifier);

        if (Character.isLowerCase(sb.charAt(3))) {
          sb.setCharAt(3, Character.toUpperCase(sb.charAt(3)));
        }
        else {
          sb.setCharAt(3, Character.toLowerCase(sb.charAt(3)));
        }

        vm = getMethod(obj, sb.toString(), params, i);

        if (vm == null) {
          throw new NoSuchMethodException();
        }
      }
    }
    catch (NoSuchMethodException nsme) {
      /*
       *  right now, we only support the Map interface
       */

      if (Map.class.isAssignableFrom(claz)) {
        Object[] params = {
            new Object(), new Object()};

        vm = getMethod(obj, "put", params, i);

        if (vm != null) {
          return new VelSetterImpl(vm, identifier);
        }
      }
    }

    if (vm != null) {
      return new VelSetterImpl(vm);
    }


    return null;
  }

  /**
   *  Implementation of VelMethod
   */
  public class VelMethodImpl
      implements VelMethod {
    Method method = null;

    public VelMethodImpl(Method m) {
      method = m;
    }

    private VelMethodImpl() {
    }

    public Object invoke(Object o, Object[] params) throws Exception {
      return method.invoke(o, params);
    }

    public boolean isCacheable() {
      return true;
    }

    public String getMethodName() {
      return method.getName();
    }

    public Class getReturnType() {
      return method.getReturnType();
    }
  }

  public class VelGetterImpl
      implements VelPropertyGet {
    AbstractExecutor ae = null;

    public VelGetterImpl(AbstractExecutor exec) {
      ae = exec;
    }

    private VelGetterImpl() {
    }

    public Object invoke(Object o) throws Exception {
      return ae.execute(o);
    }

    public boolean isCacheable() {
      return true;
    }

    public String getMethodName() {
      return ae.getMethod().getName();
    }

  }

  public class VelSetterImpl
      implements VelPropertySet {
    VelMethod vm = null;
    String putKey = null;

    public VelSetterImpl(VelMethod velmethod) {
      this.vm = velmethod;
    }

    public VelSetterImpl(VelMethod velmethod, String key) {
      this.vm = velmethod;
      putKey = key;
    }

    private VelSetterImpl() {
    }

    public Object invoke(Object o, Object value) throws Exception {
      ArrayList al = new ArrayList();

      if (putKey != null) {
        al.add(putKey);
        al.add(value);
      }
      else {
        al.add(value);
      }

      return vm.invoke(o, al.toArray());
    }

    public boolean isCacheable() {
      return true;
    }

    public String getMethodName() {
      return vm.getMethodName();
    }

  }
}
