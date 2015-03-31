package org.codebuilder.project;

import java.util.*;

public class CollectionProperty extends StringProperty{

  private Collection collection;

  public CollectionProperty() {

  }

  public Collection getCollection(){
    return collection;
  }

  public void setCollection(Collection collection){
    this.collection = collection;
  }
}
