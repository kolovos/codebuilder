package org.codebuilder.util;

import java.lang.reflect.Method;
import org.apache.bcel.Repository;
import org.apache.bcel.classfile.JavaClass;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.ListIterator;

public class ReflectionUtil {
  public ReflectionUtil() {
  }

  private static org.apache.bcel.classfile.Method getMatchingMethod(Method
      method) {
    JavaClass clazz = Repository.lookupClass(method.getDeclaringClass().getName());
    org.apache.bcel.classfile.Method bcelMethod = null;
    ArrayList qualifiers = new ArrayList();
    for (int i = 0; i < clazz.getMethods().length; i++) {
      if (signaturesMatch(clazz.getMethods()[i], method)) {
        qualifiers.add( clazz.getMethods()[i]);
      }
    }
    if (qualifiers.size() == 1){
      bcelMethod = (org.apache.bcel.classfile.Method) qualifiers.get(0);
    }
    else {
      int distance = 1000;
      ListIterator li = qualifiers.listIterator();
      while (li.hasNext()){
        org.apache.bcel.classfile.Method bMethod = (org.apache.bcel.classfile.Method) li.next();
        if (bMethod.getLocalVariableTable().getTableLength() - method.getParameterTypes().length < distance){
          distance = bMethod.getLocalVariableTable().getTableLength() - method.getParameterTypes().length;
          bcelMethod = bMethod;
        }
      }
    }
    return bcelMethod;
  }

  private static boolean signaturesMatch(org.apache.bcel.classfile.Method
                                        bcelMethod,
                                        Method method) {
   //try {
     if (method.getName().compareTo(bcelMethod.getName()) == 0) {
       if (method.getParameterTypes().length <=
           bcelMethod.getLocalVariableTable().getTableLength() - 1) {
         return true;
      }
     }
   //}
   //catch (Exception ex){

   //}
   //finally {
     return false;
   //}
  }

  public static String getMethodSignature(Method method) {
    String signature = "";
    org.apache.bcel.classfile.Method bcelMethod = getMatchingMethod(method);

    if (bcelMethod == null) {
      return getSimpleMethodSignature(method);
    }
    signature += method.getName();
    signature += "(";
    for (int i = 0; i < method.getParameterTypes().length; i++) {
      signature += method.getParameterTypes()[i].getName();
      signature += " " +
          bcelMethod.getLocalVariableTable().getLocalVariable(i + 1).getName();
      if (i != method.getParameterTypes().length - 1) {
        signature += ", ";
      }
    }
    signature += ") : ";
    signature += method.getReturnType().getName();

    return signature;
  }

  private static String getSimpleMethodSignature(Method method) {
    return method.getName();
  }

  public static ArrayList getNonObjectMethods(Class clazz){
    ArrayList methods = new ArrayList();
    for (int i=0;i<clazz.getMethods().length;i++){
      if (clazz.getMethods()[i].getDeclaringClass().getName().indexOf("org.codebuilder") >= 0){
        methods.add(clazz.getMethods()[i]);
      }
    }
    return methods;
  }

}
