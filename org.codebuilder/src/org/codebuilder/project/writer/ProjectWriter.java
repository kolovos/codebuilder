package org.codebuilder.project.writer;

import org.jdom.input.SAXBuilder;
import org.codebuilder.project.*;
import org.codebuilder.common.file.PathResolver;
import java.util.ListIterator;
import org.jdom.output.XMLOutputter;
import java.io.StringWriter;
import java.io.*;
import org.codebuilder.common.file.BatchWriter;
import java.util.Enumeration;
import org.codebuilder.project.reader.ProjectReader;
import org.codebuilder.project.reader.ReadException;

public class ProjectWriter {

  private String filename = "";

  public ProjectWriter() {
  }

  public String write(Project project) throws WriteException{
    return write(project.getPath(), project);
  }

  public String write(String filename, Project project) throws WriteException {
    this.filename = filename;
    project.setPath(filename);
    org.jdom.Document document = new org.jdom.Document();
    document.setRootElement(documentToElement(project));
    XMLOutputter outputter = new XMLOutputter();
    outputter.setFormat(org.jdom.output.Format.getPrettyFormat());
    StringWriter stringWriter = new StringWriter();
    try {
      outputter.output(document, stringWriter);

      BatchWriter writer = new BatchWriter();
      writer.writeAll(stringWriter.toString(), filename);
    }
    catch (IOException ex) {
      throw new WriteException(ex);
    }
    return stringWriter.toString();
  }


  private org.jdom.Element documentToElement(Document document){

  org.jdom.Element documentElement = new org.jdom.Element(document.getType());
  //org.jdom.Attribute nameAttr = new org.jdom.Attribute("name", document.getName());
  //documentElement.getAttributes().add(nameAttr);
  if (document.getProperties().size() > 0){
    org.jdom.Element propertiesElement = new org.jdom.Element("Properties");
    ListIterator li = document.getProperties().listIterator();
    while (li.hasNext()){
      Property property = (Property) li.next();
      org.jdom.Element propertyElement = new org.jdom.Element("Property");
      propertyElement.getAttributes().add(new org.jdom.Attribute("name", property.getName()));
      String value = "";
      if (property.getValue() != null) value = property.getValue().toString();
      propertyElement.getAttributes().add(new org.jdom.Attribute("value", value));
      propertiesElement.getChildren().add(propertyElement);
    }
    documentElement.getChildren().add(propertiesElement);
  }
  if (document instanceof DocumentGroup){
    ListIterator li = ((DocumentGroup) document).getDocuments().listIterator();
    while (li.hasNext()){
      documentElement.getChildren().add(documentToElement((Document)li.next()));
    }
  }
  else {
    org.jdom.Attribute pathAttr = new org.jdom.Attribute("path", PathResolver.getRelativePath( new File(filename).getParentFile().getAbsolutePath(),document.getPath()));
    documentElement.getAttributes().add(pathAttr);
  }
  return documentElement;
}

public static void main(String[] args) throws WriteException, ReadException {
  Project project = new Project();
  project.setName("Serialization example");
  for (int k=0; k<5; k++){

    DocumentGroup documentGroup = new DocumentGroup();
    project.addDocument(documentGroup);
    documentGroup.setName("DocumentGroup" + k);

    for (int i = 0; i < 10; i++) {
      Document template = new Template();
      template.setName("Template" + i);
      documentGroup.addDocument(template);
      for (int j = 1; j < 3; j++) {
        template.getProperties().add(new StringProperty("Name" + j, "Value" + j));
      }
    }
  }
  ProjectWriter writer = new ProjectWriter();
  writer.write("C:/test.txt", project);

  ProjectReader reader = new ProjectReader();
  Project projectFromDisk = reader.read("C:/test.txt");

  writer.write("C:/test.txt", projectFromDisk);
}

}
