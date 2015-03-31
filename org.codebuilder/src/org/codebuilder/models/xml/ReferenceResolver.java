package org.codebuilder.models.xml;

import java.util.*;
import org.jdom.*;

public class ReferenceResolver {

  public ReferenceResolver() {
  }

  /**
   * Fetches an element from a document
   * given its id
   * @param document Document
   * @param id String
   * @return Element
   */
  public Element getElementById(Document document, String id){
    Element element = document.getRootElement();
    return getElementById(element, id);
  }

  /**
   * Fetches an element from an element
   * given its id
   * @param element Element
   * @param id String
   * @return Element
   */
  private Element getElementById(Element element, String id){
    ListIterator attrs = element.getAttributes().listIterator();

    // ---------------------
    // First try to find if one
    // of the attributes of the
    // element is ID and its value
    // is the same with the requested
    // one
    // ---------------------

    while (attrs.hasNext()){
      Attribute attr = (Attribute) attrs.next();
      if (attr.getAttributeType() == Attribute.ID_TYPE && attr.getValue().compareTo(id) == 0)
        return element;
    }

    // ---------------------
    // If not, recursively search its
    // children
    // ---------------------

    ListIterator children = element.getChildren().listIterator();
    while (children.hasNext()){
      Element child = (Element) children.next();
      Element idElement = getElementById(child, id);
      if (idElement != null)
        return idElement;
    }

    // ---------------------
    // The requested ID was not
    // found in the context of this
    // element
    // ---------------------

    return null;
  }

  /**
   * Fetches a list of elements
   * based on their space delimited
   * ids
   * @param document Document
   * @param ids String
   * @return List
   */
  public List getElementsByIds(Document document, String ids){
    ArrayList idElements = new ArrayList();
    StringTokenizer tokenizer = new StringTokenizer(ids, " ");
    while (tokenizer.hasMoreElements()){
      String id = (String) tokenizer.nextElement();
      Element idElement = getElementById(document, id);
      if (idElement != null)
        idElements.add(idElement);
    }
    return idElements;
  }

}
