package org.codebuilder.project.reader;

import java.io.*;
import java.util.*;
import org.codebuilder.common.file.*;
import org.codebuilder.project.*;
import org.jdom.input.*;

public class ProjectReader {

  private String filename = "";

  public ProjectReader() {
  }

  /**
   * Reads a project descriptor file and returns
   * a project object
   * @param filename String
   * @throws ReadException if the file is not found or is not well structured
   * @return Project
   * @todo Add schema validation to the project file
   */

  public Project read(String filename) throws ReadException {
    this.filename = filename;
    SAXBuilder builder = new SAXBuilder();
    org.jdom.Document document = new org.jdom.Document();
    try {
      document = builder.build(filename);
      Project project = (Project) elementToDocument(document.getRootElement());
      project.setPath(filename);
      return project;
    }
    catch (Exception ex) {
      throw new ReadException(ex);
    }
}

  private Document elementToDocument(org.jdom.Element element){
    Document document = DocumentFactory.getInstance().createDocument(element.getName());
    if (element.getChild("Properties") != null){
      ListIterator li = element.getChild("Properties").getChildren("Property").
          listIterator();
      while (li.hasNext()) {
        org.jdom.Element property = (org.jdom.Element) li.next();
          document.getProperties().add(new StringProperty(property.getAttributeValue("name"),
                                       property.getAttributeValue("value")));
        }
    }
    if (document instanceof DocumentGroup){
      ListIterator li = element.getChildren().listIterator();
      while (li.hasNext()){
        org.jdom.Element containedDocument = (org.jdom.Element) li.next();
        if (containedDocument.getName().compareTo("Properties") != 0){
          ((DocumentGroup) document).addDocument(elementToDocument(containedDocument));
        }
      }
    }
    else {
      document.setPath(PathResolver.getAbsolutePath(element.getAttributeValue("path"), new File(filename).getParentFile().getAbsolutePath()));
    }
    return document;
  }
}
