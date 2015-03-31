package org.codebuilder.project;

import java.util.List;
import java.util.ArrayList;
import org.jdom.input.*;
import org.jdom.*;
import org.codebuilder.ide.feedback.*;
import java.util.*;

public class DocumentFactory {

  private static DocumentFactory instance = new DocumentFactory();
  protected org.jdom.Document doc;
  protected List documents;

  private DocumentFactory() {
    SAXBuilder builder = new SAXBuilder();
    try {
      doc = builder.build("config/documents.xml");
      ListIterator li = doc.getRootElement().getChildren().listIterator();
      documents = new ArrayList();
      while(li.hasNext()){
        Element el = (Element) li.next();
        Document document = (Document) Class.forName(el.getAttributeValue("class")).newInstance();
        documents.add(document);
      }
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
    }
  }

  public static DocumentFactory getInstance(){
    return instance;
  }

  public Document createDocument(String type){
    if (type.compareTo("Project") == 0){
      return new Project();
    }
    else if (type.compareTo("DocumentGroup") == 0){
      return new DocumentGroup();
    }
    else if (type.compareTo("Template") == 0){
      return new Template();
    }
    else {
      ListIterator li = documents.listIterator();
      while (li.hasNext()){
        Document document = (Document) li.next();
        if (document.getType().equals(type))
          try {
            return (Document) document.getClass().newInstance();
          }
          catch (Exception ex) {
            FeedbackManager.getInstance().reportException(ex);
          }
      }
    }

    return null;
  }

  public java.util.List createDocuments(){
    ArrayList allDocuments = new ArrayList();
    allDocuments.add(new Template());
    allDocuments.addAll(documents);
    return allDocuments;
  }

}
