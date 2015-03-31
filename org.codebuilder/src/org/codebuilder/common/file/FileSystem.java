package org.codebuilder.common.file;

import java.io.File;

public class FileSystem {
  public FileSystem() {

  }

  public boolean createDir(String path){
    return new File(path).mkdirs();
  }

  public boolean createDirForFile(String path){
    File f = new File(path);
    return new File(f.getParent()).mkdirs();
  }

  public static void main(String args[]){
    FileSystem fs = new FileSystem();
    fs.createDir("c:/b/c");
    fs.createDirForFile("c:/x/y/a.txt");
  }
}
