package org.codebuilder.ide.ui.editors.xmleditor.xml;

import org.jdom.*;

public class PrettyFactory extends DefaultJDOMFactory{
  public PrettyFactory() {
  }

  /**
   * element
   *
   * @param string String
   * @return Element
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Element element(String string) {
    return new PrettyElement(string);
  }

  /**
   * element
   *
   * @param string String
   * @param namespace Namespace
   * @return Element
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Element element(String string, Namespace namespace) {
    return new PrettyElement(string,namespace);
  }

  /**
   * element
   *
   * @param string String
   * @param string1 String
   * @return Element
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Element element(String string, String string1) {
    return new PrettyElement(string,string1);
  }

  /**
   * element
   *
   * @param string String
   * @param string1 String
   * @param string2 String
   * @return Element
   * @todo Implement this org.jdom.JDOMFactory method
   */
  public Element element(String string, String string1, String string2) {
    return new PrettyElement(string,string1,string2);
  }


}
