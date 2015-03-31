package org.codebuilder.template.handwritten;

/**
 * @todo Document this class
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class HandWrittenRegion {
  public HandWrittenRegion() {
  }

  private String content;
  private int startIndex;
  private int endIndex;
  private String name;

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public void setContent(String content){
    this.content = content;
  }

  public String getContent(){
    return content;
  }

  public void setStartIndex(int startIndex){
    this.startIndex = startIndex;
  }

  public int getStartIndex(){
    return startIndex;
  }

  public void setEndIndex(int endIndex){
    this.endIndex = endIndex;
  }

  public int getEndIndex(){
    return endIndex;
  }

  public String toString(){
    return
        "Region name: " + name +  "\r\n" +
        "Start index: " + startIndex + "\r\n" +
        "End index: " + endIndex + "\r\n" +
        "Content: " + content;
  }
}
