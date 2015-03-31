package org.codebuilder.template.introspection;

public class Parameter {
  private Class clazz;
  private String name;
  public Parameter() {
  }

  public Parameter(String name, Class clazz){
    this.name = name;
    this.clazz = clazz;
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Class getClazz() {
    return clazz;
  }
  public void setClazz(Class clazz) {
    this.clazz = clazz;
  }
}
