package org.codebuilder.ide.ui;

import java.util.*;
import java.io.*;

/**
 * <p>Title: CodeBuilder</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: Dimitrios Kolovos</p>
 *
 * @author Dimitrios S. Kolovos
 * @version 1.0
*  @todo Remove dublicates from list - promote instead
 */

public class LastRecentlyUsed extends ArrayList{

  private int maxSize;
  private String storePath;

  public LastRecentlyUsed() {
    this.storePath = "config/lru.config";
    this.maxSize = 10;
  }

  public LastRecentlyUsed(int maxSize, String storePath){
    this.maxSize = maxSize;
    this.storePath = storePath;
  }

  public void save(){
    try {
      FileWriter fw = new FileWriter(storePath);
      BufferedWriter bw = new BufferedWriter(fw);
      ListIterator li = listIterator();
      while (li.hasNext()){
        bw.write(li.next().toString() + "\n");
      }
      bw.flush();
      fw.flush();
      bw.close();
      fw.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void load(){
    try {
      FileReader fr = new FileReader(storePath);
      BufferedReader br = new BufferedReader(fr);
      String line;
      super.clear();
      while ((line = br.readLine())!=null){
        if (new File(line).exists() && !(this.contains(line)))
        super.add(line);
      }
      fr.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public boolean add(Object o){
    if (contains(o)) {
      super.remove(o);
    }
    super.add(0,o);
    if (this.size() > maxSize){
      remove(maxSize);
    }
    return true;
  }

  public int getMaxSize(){
    return maxSize;
  }

  public void setMaxSize(int maxSize){
    this.maxSize = maxSize;
  }

  public void setStorePath(String storePath){
    this.storePath = storePath;
  }

  public String getStorePath(){
    return storePath;
  }

  public static void main(String[] args){
    LastRecentlyUsed lru = new LastRecentlyUsed();
//    lru.add("Malaka1");
//    lru.add("Malaka2");
//    lru.add("Malaka2");
//    lru.add("Malaka4");
//    lru.add("Malaka5");
//    lru.add("Malaka6");
    lru.load();
    lru.save();
    lru.load();
    ListIterator li = lru.listIterator();
    while (li.hasNext()){
      System.err.println(li.next().toString());
    }
    System.err.println(lru.size());
  }
}
