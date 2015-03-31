package org.codebuilder.models.xml;

import java.util.*;
import org.codebuilder.ide.feedback.*;
import org.jdom.*;
import org.jdom.input.*;
import org.codebuilder.common.xml.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.project.Project;
import org.codebuilder.project.Model;
import org.codebuilder.models.xml.XMLModel;

public class ReferenceManager {

  protected ArrayList ids = new ArrayList();
  protected ArrayList idrefs = new ArrayList();
  protected ArrayList idrefss = new ArrayList();
  protected static ReferenceManager instance = null;
  protected XPath xpath = new XPath();

  public ReferenceManager() {
    try {
      //FeedbackManager.getInstance().report("Loading reference resolving configuration ...","",FeedbackSeverity.INFORMATION);
      init();
    }
    catch (Exception ex) {
      //FeedbackManager.getInstance().report("Loading reference resolving configuration failed","",FeedbackSeverity.ERROR);
      ex.printStackTrace();
    }
  }

  /**
   * For use as singleton
   * @return ReferenceManager
   */
  public static ReferenceManager getInstance() {
    if (instance == null) {
      synchronized (ReferenceManager.class) {
        if (instance == null) {
          instance = new ReferenceManager();
        }
      }
    }
    return instance;
  }

  public static void reload() {
    try {
      instance.init();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Initializes the ReferenceManager:
   * Loads the configuration from the ids-refs.xml
   * Constructs the xpath queries that will locate
   * the referenced elements
   * @throws Exception
   */
  private void init() throws Exception {

    SAXBuilder builder = new SAXBuilder();
    Document doc = builder.build("config/ids-refs.xml");

    // ---------------------
    // Store the ids
    // ---------------------

    ListIterator li = doc.getRootElement().getChildren("id").listIterator();
    while (li.hasNext()) {
      Element el = (Element) li.next();
      ids.add(el.getAttributeValue("name"));
    }

    // ---------------------
    // Store the idrefs
    // ---------------------

    li = doc.getRootElement().getChildren("idref").listIterator();
    while (li.hasNext()) {
      Element el = (Element) li.next();
      idrefs.add(el.getAttributeValue("name"));
    }

    // ---------------------
    // Store the idrefss
    // ---------------------

    li = doc.getRootElement().getChildren("idrefs").listIterator();
    while (li.hasNext()) {
      Element el = (Element) li.next();
      idrefss.add(el.getAttributeValue("name"));
    }

  }

  public Element getElementById(Attribute attribute) {
    Element el = attribute.getDocument().getRootElement();
    ListIterator idsIterator = ids.listIterator();
    while (idsIterator.hasNext()) {
      String id = idsIterator.next().toString();
      String query = "//*[@" + id + "='" + attribute.getValue() + "']";
      Element result = xpath.selectNode(el, query);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  public List getElementsById(Attribute attribute) {
    List results = new ArrayList();
    StringTokenizer tokenizer = new StringTokenizer(attribute.getValue(), " ");
    while (tokenizer.hasMoreElements()) {
      String token = tokenizer.nextElement().toString();
      Element el = attribute.getDocument().getRootElement();
      ListIterator idsIterator = ids.listIterator();
      while (idsIterator.hasNext()) {
        String id = idsIterator.next().toString();
        String query = "//*[@" + id + "='" + token + "']";
        List result = xpath.selectNodes(el, query);
        if (result != null) {
          results.addAll(result);
        }
      }
    }
    return results;
  }

  public boolean isId(String name) {
    return ids.contains(name);
  }

  public boolean isIdref(String name) {
    return idrefs.contains(name);
  }

  public boolean isIdrefs(String name) {
    return idrefss.contains(name);
  }

}
