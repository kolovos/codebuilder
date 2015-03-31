package org.codebuilder.common.file;

import java.io.*;
import java.util.*;
import org.codebuilder.common.text.*;
import java.net.*;

public class PathResolver {
  public PathResolver() {
  }

  private static List getPathList(File f) {
          List l = new ArrayList();
          File r;
          try {
                  r = f.getCanonicalFile();
                  while(r != null) {
                          l.add(r.getName());
                          r = r.getParentFile();
                  }
          }
          catch (IOException e) {
                  e.printStackTrace();
                  l = null;
          }
          return l;
  }

  /**
   * figure out a string representing the relative path of
   * 'f' with respect to 'r'
   * @param r home path
   * @param f path of file
   */
  private static String matchPathLists(List r,List f) {
          int i;
          int j;
          String s;
          // start at the beginning of the lists
          // iterate while both lists are equal
          s = "";
          i = r.size()-1;
          j = f.size()-1;

          // first eliminate common root
          while((i >= 0)&&(j >= 0)&&(r.get(i).equals(f.get(j)))) {
                  i--;
                  j--;
          }

          // for each remaining level in the home path, add a ..
          for(;i>=0;i--) {
                  s += ".." + File.separator;
          }

          // for each level in the file path, add the path
          for(;j>=1;j--) {
                  s += f.get(j) + File.separator;
          }

          // file name
          s += f.get(j);
          return s;
  }

  /**
   * get relative path of File 'f' with respect to 'home' directory
   * example : home = /a/b/c
   *           f    = /a/d/e/x.txt
   *           s = getRelativePath(home,f) = ../../d/e/x.txt
   * @param home base path, should be a directory, not a file, or it doesn't
make sense
   * @param f file to generate path for
   * @return path from home to f as a string
   */
  public static String getRelativePath(File home,File f){
          File r;
          List homelist;
          List filelist;
          String s;

          homelist = getPathList(home);
          filelist = getPathList(f);
          s = matchPathLists(homelist,filelist);

          s = s.replaceAll(Escaper.escapeRegularExpression("\\"), Escaper.escapeRegularExpression("/"));
          return s;
  }
  /*
  public static String getAbsolutePath(String relativePath, String base){
    return relativePath;
  }

  public static String getRelativePath(String home, String filename){
    return filename;
  }
  */

  public static String getAbsolutePath(String relativePath, String base){

    if (!base.endsWith("\\"))
      base = base.concat("\\");

    base = base.replaceAll(Escaper.escapeRegularExpression("\\"), Escaper.escapeRegularExpression("/"));
    URI baseUri = null;
    try {
      baseUri = new URI(base);
    }
    catch (Exception ex) {

      return "";
    }
    return baseUri.resolve(relativePath).toString();
  }

  public static String getRelativePath(String home, String filename){
    if (!home.endsWith("\\"))
      home = home.concat("\\");

    File homeFile = new File(home);
    File file = new File(filename);
    return getRelativePath(homeFile, file);
  }

  public static void main(String[] args){
    String base = "C:\\Projects\\Personal\\CodeBuilder\\projects\\paths\\";
    String file = "C:\\Projects\\Personal\\CodeBuilder\\projects\\paths\\template.vm";
    String rel = PathResolver.getRelativePath(base,file);
    String abs = PathResolver.getAbsolutePath(rel,base);
  }
}
