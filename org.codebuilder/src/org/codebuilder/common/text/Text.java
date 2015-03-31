package org.codebuilder.common.text;

public class Text {

  private String text;

  public Text(String text){
    this.text = text;
  }

  public void setText(String text){
    this.text = text;
  }

  public String getText(){
    return text;
  }

  public Text() {
  }

  public Text getFirstuppercase() {
    if (text.length() > 0) {
      return new Text(text.substring(0, 1).toUpperCase() + text.substring(1, text.length()));
    }
    else {
      return new Text("");
    }
  }

  public Text getFirstlowercase() {
    if (text.length() > 0) {
      return new Text(text.substring(0, 1).toLowerCase() + text.substring(1, text.length()));
    }
    else {
      return new Text("");
    }
  }

  public String toString(){
    return text;
  }

  public int getInt(){
    return Integer.parseInt(text);
  }

  public boolean getBoolean(){
    return new Boolean(text).booleanValue();
  }

  public boolean equals(String str){
    return (str.compareTo(text) == 0);
  }

  public boolean notequals(String str){
    return (str.compareTo(text) != 0);
  }

  public boolean equals(Text text){
    return equals(text.getText());
  }

  public boolean notequals(Text text){
    return notequals(text.getText());
  }


}
