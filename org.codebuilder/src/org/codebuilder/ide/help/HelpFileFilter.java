package org.codebuilder.ide.help;

import java.io.*;

public class HelpFileFilter
    implements FilenameFilter {
  public HelpFileFilter() {
  }

  /**
   * accept
   *
   * @param dir File
   * @param name String
   * @return boolean
   */
  public boolean accept(File dir, String name) {

    File file = new File(dir, name);

    if (file.isDirectory()){
      return (file.getName().compareTo("images") != 0);
    }
    else {
      return name.endsWith(".html");
    }
  }

}
