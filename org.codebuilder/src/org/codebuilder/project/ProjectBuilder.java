package org.codebuilder.project;

import java.util.*;
import org.codebuilder.template.*;

public class ProjectBuilder {

  private Project project = null;

  public ProjectBuilder() {
  }

  public void build(Project project) throws TemplateException {
    this.project = project;

    TemplateEngine.reset();

    /**
     * Prepare the project context
     * The context contains:
     * 1. The project itself
     * 2. Tools
     */

    project.getContext().put("out", System.out);
    project.getContext().put("project", project);
    project.getContext().put("tools", new Tools());

    /**
     * Load the templates from their files
     */

    ListIterator li = getAllTemplates().listIterator();
    while (li.hasNext()) {
      Template template = (Template) li.next();
      template.load();
      template.setGlobalContext(project.getContext());
    }

    /**
     * Iterate the templates and execute
     * those which have the startup to true
     */
    li = getAllTemplates().listIterator();
    while (li.hasNext()) {
      Template template = (Template) li.next();
      if (template.isStartUp()) {
        template.process();
      }
    }

  }

  public List getAllModels() {
    ArrayList allModels = new ArrayList();
    ListIterator li = project.getAllDocuments().listIterator();
    while (li.hasNext()) {
      Object next = li.next();
      if (next instanceof Model) {
        allModels.add(next);
      }
    }
    return allModels;
  }

  public List getAllTemplates(){
    ListIterator li = project.getAllDocuments().listIterator();
    ArrayList allTemplates = new ArrayList();

    while (li.hasNext()){
      Object o = li.next();
      if (o instanceof Template){
        allTemplates.add(o);
      }
    }

    return allTemplates;
  }
}
