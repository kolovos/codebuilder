package org.codebuilder.project;

import java.util.*;

public class BooleanProperty extends CollectionProperty{

  Collection collection = new ArrayList();

  public BooleanProperty(String name, String value, String description){
    this.name = name;
    this.value = value;
    this.description = description;
    init();
  }


  public BooleanProperty() {
    init();
  }

  private void init(){
    collection.add("true");
    collection.add("false");
  }

  public Collection getCollection(){
    return collection;
  }

  public void setCollection(Collection collection){
    this.collection = collection;
  }

}
