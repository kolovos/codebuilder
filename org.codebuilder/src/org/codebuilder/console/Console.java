package org.codebuilder.console;

import org.codebuilder.project.Project;
import org.codebuilder.project.reader.*;
import org.codebuilder.template.*;
import org.codebuilder.project.ProjectBuilder;

public class Console {
  public Console() {
  }

  public static void main(String[] args){
    Console console = new Console();
    if (args.length != 1){
      console.printusage();
    }
    String filename = args[0];
    Project project = new Project();
    try {
      ProjectReader reader = new ProjectReader();
      project = reader.read(filename);
      //project.load(filename);
    }
    catch (ReadException ex) {
      System.err.println("Could not load from file: " + filename);
      ex.printStackTrace(System.err);
      System.exit(2);
    }
    try {
      project.getContext().put("debug", System.out);
      ProjectBuilder builder = new ProjectBuilder();
      builder.build(project);
      //project.run();
    }
    catch (TemplateException ex) {
      System.err.println("An error occured in template " + ex.getTemplate().getPath());
      ex.printStackTrace(System.err);
      System.exit(3);
    }
    System.out.println("Processing of the project succeeded");
    System.exit(0);
  }

  public void printusage(){
    System.err.print("Usage: Console <project filename>");
    System.exit(1);
  }


}
