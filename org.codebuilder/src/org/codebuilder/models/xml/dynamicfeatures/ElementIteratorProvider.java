package org.codebuilder.models.xml.dynamicfeatures;

import org.codebuilder.template.introspection.*;
import java.util.Iterator;
import org.apache.velocity.util.introspection.Info;
import org.jdom.*;
import java.util.*;

public class ElementIteratorProvider implements DynamicIteratorProvider{
  public ElementIteratorProvider() {
  }

  /**
   * accept
   *
   * @param obj Object
   * @return boolean
   */
  public boolean accept(Object obj) {
    return (obj instanceof Element);
  }

  /**
   * getIterator
   *
   * @param obj Object
   * @param i Info
   * @return Iterator
   */
  public Iterator getIterator(Object obj, Info i) {
    ArrayList list = new ArrayList();
    list.add(obj);
    return list.listIterator();
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "";
  }
}
