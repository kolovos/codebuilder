package org.codebuilder.project;

public class StringProperty extends Property{

  protected String value = "";

  public StringProperty(String name, String value){
    this.name = name;
    this.value = value;
  }

  public StringProperty(String name, String value, String description){
    this.name = name;
    this.value = value;
    this.description = description;
  }

  public StringProperty() {
  }

  /**
   * getValue
   *
   * @return String
   */
  public String getValue() {
    return value;
  }

  /**
   * setValue
   *
   * @param value String
   */
  public void setValue(String value) {
    this.value = value;
  }

}
