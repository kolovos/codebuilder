package org.codebuilder.util;

import java.io.File;

public class FileSystem {

  public FileSystem() {
  }

  public void createDirectory(String directory) {
    new File(directory).mkdirs();
  }

  public void main(){

  }

  public boolean fileExists(String directory){
    return (new File(directory)).exists();
  }

  public void deleteFile(String file){
    new File(file).delete();
  }

  public File getFile(String file){
    return new File(file);
  }

  public String getLegalFileName(String str){
    String result = str;
    char[] illegalChars = new char[]{'/','\\',':','?','*','\\','<','>'};
    for (int i = 0; i<illegalChars.length ; i++){
      result = result.replace(illegalChars[i], ' ');
    }
    if (result.trim().length() == 0){
      result = "untitled";
    }
    return result;
  }

  public static void main(String[] args){
    FileSystem fs = new FileSystem();
    System.out.println(fs.getLegalFileName("c/\\::?*\\<>b"));
  }

}
