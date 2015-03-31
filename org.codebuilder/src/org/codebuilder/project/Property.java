package org.codebuilder.project;

import java.util.*;

public abstract class Property {

  protected String name = "";
  protected String description = "";

  public Property() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public abstract String getValue();

  public abstract void setValue(String value);

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String toString(){
    return getValue();
  }

  public boolean getBooleanValue(){
    return new Boolean(getValue()).booleanValue();
  }

}
