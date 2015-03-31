package org.codebuilder.ide.ui.editors.highlighting;

import java.awt.*;

public class Highlightable {

  private String name;
  private String expression;
  private java.awt.Color color;

  public Highlightable() {
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getExpression() {
    return expression;
  }
  public void setExpression(String expression) {
    this.expression = expression;
  }
  public java.awt.Color getColor() {
    return color;
  }
  public void setColor(java.awt.Color color) {
    this.color = color;
  }
  public void setColor(String color){
    this.color = Color.decode("0x"+color);
  }
}
