package org.codebuilder.project;

import java.io.*;
import java.util.*;

import javax.swing.*;

import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.template.*;
import org.codebuilder.ide.projectmanagement.*;

public class Project extends DocumentGroup implements ProjectChangeListener{

  private Context context = new Context();
  private boolean dirty = false;
  private String lastSaved = "";

  public Project() {
    ProjectManager.getInstance().addProjectChangeListener(this);
  }

  //public boolean load(String filename) throws ReadException {
  //  setPath(filename);
  //  ProjectReader reader = new ProjectReader();
  //  reader.read(filename, this);
  //  return true;
  //}

  //public boolean save() throws WriteException {
  //  return save(getPath());
  //}

  //public boolean save(String filename) throws WriteException {
  //  ProjectWriter writer = new ProjectWriter();
  //  writer.write(filename, this);
  // return true;
  //}

  public String getBasePath(){
    return (new File(getPath())).getParentFile().getAbsolutePath();
  }

  public void reset() {
    context = new Context();
    ListIterator li = getAllDocuments().listIterator();
    while (li.hasNext()) {
      ( (Document) li.next()).reset();
    }
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  /**
   * getIcon
   *
   * @return ImageIcon
   */
  public Icon getIcon() {
    return IconFactory.Project;
  }

  /**
   * validate
   */
  public void validate() {
    return;
  }

  public String getType(){
    return "Project";
  }

  public boolean isDirty(){
    return dirty;
  }

  public void setDirty(boolean dirty){
    this.dirty = dirty;
  }

  /**
   * projectChanged
   */
  public void projectChanged() {
    setDirty(true);
  }
}
