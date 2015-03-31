package org.codebuilder.models.xml;

import org.jdom.*;

public class IdrefDocumentFactory
    extends DefaultJDOMFactory {

  ReferenceManager referenceManager = ReferenceManager.getInstance();

  public IdrefDocumentFactory() {
    super();
  }

  /**
   * attribute
   *
   * @param string String
   * @param string1 String
   * @return Attribute
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Attribute attribute(String string, String string1) {
    string = simplify(string);
    if (referenceManager.isIdref(string)) {
      return new IdrefAttribute(string, string1);
    }
    if (referenceManager.isIdrefs(string)) {
      return new IdrefsAttribute(string, string1);
    }

    return new Attribute(string, string1);
  }

  /**
   * attribute
   *
   * @param string String
   * @param string1 String
   * @param namespace Namespace
   * @return Attribute
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Attribute attribute(String string, String string1, Namespace namespace) {
    string = simplify(string);
    if (referenceManager.isIdref(string)) {
      return new IdrefAttribute(string, string1, namespace);
    }
    if (referenceManager.isIdrefs(string)) {
      return new IdrefsAttribute(string, string1, namespace);
    }

    return new Attribute(string, string1, namespace);

  }

  /**
   * attribute
   *
   * @param string String
   * @param string1 String
   * @param int2 int
   * @return Attribute
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Attribute attribute(String string, String string1, int int2) {
    string = simplify(string);
    if (referenceManager.isIdref(string)) {
      return new IdrefAttribute(string, string1, int2);
    }
    if (referenceManager.isIdrefs(string)) {
      return new IdrefsAttribute(string, string1, int2);
    }

    return new Attribute(string, string1, int2);

  }

  /**
   * attribute
   *
   * @param string String
   * @param string1 String
   * @param int2 int
   * @param namespace Namespace
   * @return Attribute
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Attribute attribute(String string, String string1, int int2,
                             Namespace namespace) {
    string = simplify(string);
    if (referenceManager.isIdref(string)) {
      return new IdrefAttribute(string, string1, int2, namespace);
    }
    if (referenceManager.isIdrefs(string)) {
      return new IdrefsAttribute(string, string1, int2, namespace);
    }

    return new Attribute(string, string1, int2, namespace);

  }

  public String simplify(String name) {
  String[] parts = name.split("\\.");
  if (parts.length > 0) {
    name = parts[parts.length - 1];
  }
  return name;

}

}
