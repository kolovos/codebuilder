package org.codebuilder.project;

import java.util.ArrayList;
import java.util.ListIterator;

public class Properties
    extends ArrayList
    implements Cloneable {
  public Properties() {
  }

  public boolean add(Object o) {
    add(this.size(), o);
    return true;
  }

  public void add(int index, Object o) {
    if (o instanceof Property) {
      if (get( ( (Property) o).getName()) == null) {
        super.add(index, o);
      }
      else {
        ( (Property) get( ( (Property) o).getName())).setValue( ( (Property) o).
            getValue());
      }
    }
  }

  public Property get(String name) {
    ListIterator li = listIterator();
    while (li.hasNext()) {
      Property property = (Property) li.next();
      if (property.getName().compareTo(name) == 0) {
        return property;
      }
    }
    return null;
  }

  public boolean remove(Object o) {
    return super.remove(o);
  }

  public Object remove(int index) {
    Object o = this.get(index);
    if (remove(o)) {
      return o;
    }
    else {
      return null;
    }
  }
}
