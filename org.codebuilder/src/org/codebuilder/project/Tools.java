package org.codebuilder.project;

import java.io.PrintStream;
import java.util.TreeMap;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import java.util.ListIterator;
import org.jdom.Element;
import org.codebuilder.ide.feedback.FeedbackManager;
import java.util.TreeMap;

public class Tools {

  private TreeMap tools;

  public Tools() {
    tools = getTools();
  }

  public PrintStream getOut(){
    return System.out;
  }

  public TreeMap getLib(){
    return tools;
  }

  public Object get(Object obj){
    return tools.get(obj);
  }

  public TreeMap getTools() {
    Document doc;
    TreeMap tools = new TreeMap();
    try {
      SAXBuilder builder = new SAXBuilder();
      doc = builder.build("config/tools.xml");
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
      return null;
    }
    ListIterator li = doc.getRootElement().getChildren("tool").
        listIterator();
    while (li.hasNext()) {
      Element el = (Element) li.next();
      String clazz = el.getAttributeValue("class");
      String name = el.getAttributeValue("name");
      if (clazz != null && name != null) {
        try {
          Object object = Class.forName(clazz).newInstance();
          tools.put(name,object);
        }
        catch (Exception ex) {
          FeedbackManager.getInstance().reportException(ex);
        }
      }
    }
    return tools;
  }

}
