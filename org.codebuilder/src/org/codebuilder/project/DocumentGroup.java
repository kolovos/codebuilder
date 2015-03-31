package org.codebuilder.project;

import java.util.ArrayList;
import java.util.ListIterator;
import javax.swing.ImageIcon;
import org.codebuilder.ide.ui.resources.*;
import java.util.*;
import javax.swing.*;

public class DocumentGroup
    extends Document {

  private ArrayList documents = new ArrayList();

  public DocumentGroup() {
  }

  public DocumentGroup(String name) {
    setName(name);
  }

  public void addDocument(Document document) {
    documents.add(document);
    document.setParent(this);
  }

  public void removeDocument(Document document) {
    documents.remove(document);
    document.setParent(null);
  }

  public List getDocuments() {
    return documents;
  }

  public List getAllDocuments() {
    ArrayList allDocuments = new ArrayList();
    allDocuments.addAll(this.getDocuments());
    ListIterator li = getDocuments().listIterator();
    while (li.hasNext()) {
      Object doc = (Object) li.next();
      if (doc instanceof DocumentGroup) {
        allDocuments.addAll( ( (DocumentGroup) doc).getAllDocuments());
      }
    }
    return allDocuments;
  }

  public List getAllDocuments(Class clazz) {
    ArrayList allDocuments = new ArrayList();
    allDocuments.addAll(this.getDocuments());
    ListIterator li = getDocuments().listIterator();
    while (li.hasNext()) {
      Object doc = (Object) li.next();
      if (doc instanceof DocumentGroup) {
        allDocuments.addAll( ( (DocumentGroup) doc).getAllDocuments(clazz));
      }
      else if (clazz == doc.getClass()){
        allDocuments.add(doc);
      }
    }
    return allDocuments;
  }

  public Document getDocument(int index) {
    return (Document) documents.get(index);
  }

  public Document get(String name) {
    String remainder = "";

    /**
     * @todo Check for . as last letter
     */
    if (name.indexOf('.') > -1){
      remainder = name.substring(name.indexOf('.')+1, name.length());
      name = name.substring(0, name.indexOf('.'));
    }

    ListIterator li = documents.listIterator();
    while (li.hasNext()) {
      Document document = (Document) li.next();
      if (document.getName().compareTo(name) == 0) {
        if (document instanceof DocumentGroup && remainder.length() > 0)
          return ((DocumentGroup) document).get(remainder);
        else
        return document;
      }
    }
    return null;
  }

  public boolean contains(String name) {
    return get(name) != null;
  }

  /**
   * getIcon
   *
   * @return ImageIcon
   */
  public Icon getIcon() {
    return IconFactory.DocumentGroup;
  }

  /**
   * validate
   */
  public void validate() {
  }

  /**
   * reset
   */
  public void reset() {
  }

  /**
   * getType
   *
   * @return String
   */
  public String getType() {
    return "DocumentGroup";
  }

  public boolean isDirty() {
    boolean dirty = false;
    ListIterator li = getAllDocuments().listIterator();
    while (li.hasNext()) {
      dirty = dirty && ( (Document) li.next()).isDirty();
    }
    return dirty;
  }

  /**
   * getSuffix
   *
   * @return String
   */
  public String getSuffix() {
    return "";
  }

  public String getDescription(){
    return "Conceptual group of related documents";
  }

  /**
   * getShortDescription
   *
   * @return String
   */
  public String getShortDescription() {
    return "Document group";
  }
}
