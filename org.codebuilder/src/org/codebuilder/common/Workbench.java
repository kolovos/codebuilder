package org.codebuilder.common;

import org.codebuilder.common.file.PathResolver;

public class Workbench {
  public Workbench() {
  }

  public static void main(String[] args) {
    System.out.println(PathResolver.getAbsolutePath("Templates/Template1.vm",
        "C:\\VTExample\\DemoProject\\Project.xml"));
  }

  }
