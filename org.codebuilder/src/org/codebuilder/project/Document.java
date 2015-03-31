package org.codebuilder.project;

import javax.swing.ImageIcon;
import javax.swing.*;

public abstract class Document {

  //protected String name = "";
  protected String path = "";
  protected DocumentGroup parent;
  protected Properties properties = new Properties();
  protected boolean dirty = false;

  public Document(){
    this.properties.add(new StringProperty("name", "untitled", "Identifier for the document"));
  }

  public String getName(){
    return this.properties.get("name").getValue().toString();
  }

  public void setName(String name){
    this.properties.get("name").setValue(name);
  }

  public abstract String getType();

  public abstract String getShortDescription();

  public abstract String getDescription();

  public abstract Icon getIcon();

  public abstract void validate() throws DocumentValidationException;

  public abstract String getSuffix();

  public abstract void reset();

  public String getPath(){
    return this.path;
  }

  public void setPath(String path){
    this.path = path;
  }


  public DocumentGroup getParent(){
    return parent;
  }

  public void setParent(DocumentGroup parent){
    this.parent = parent;
  }

  public Properties getProperties() {
    return properties;
  }

  public void setProperties(Properties properties) {
    this.properties = properties;
  }

  public void setDirty(boolean dirty){
    this.dirty = dirty;
  }

  public boolean isDirty(){
    return dirty;
  }


}
