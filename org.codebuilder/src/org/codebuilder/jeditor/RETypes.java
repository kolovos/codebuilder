package org.codebuilder.jeditor;
/*
=====================================================================

  RETypes.java
  
  Created by Claude Duguay
  Copyright (c) 2002
  
=====================================================================
*/

import java.awt.*;
import java.util.*;
import java.util.regex.*;
import javax.swing.text.*;

public class RETypes
{
  protected LinkedList typeList;
  
  public RETypes()
  {
    typeList = new LinkedList();
  }
  
  public void addTokenType(String name, String expr, Color color)
  {
    typeList.add(new Type(name, expr, color));
  }
  
  public int getTypeCount()
  {
    return typeList.size();
  }
  
  protected Type getType(int index)
  {
    return (Type)typeList.get(index);
  }
  
  public String getName(int index)
  {
    return getType(index).name;
  }

  public String getExpr(int index)
  {
    return getType(index).expr;
  }

  public Color getColor(int index)
  {
    return getType(index).color;
  }
  
  public void setStyles(StyledDocument doc)
  {
    for (int i = 0; i < getTypeCount(); i++)
    {
      String name = getName(i);
      Color color = getColor(i);
      if (color != null)
      {
        Style style = doc.addStyle(name, null);
        StyleConstants.setForeground(style, color);
      }
    }
  }

  public String getExpression()
  {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < getTypeCount(); i++)
    {
      if (i > 0) buffer.append('|');
      buffer.append('(');
      buffer.append(getExpr(i));
      buffer.append(')');
    }
    return buffer.toString();
  }
  
  public Pattern getPattern()
  {
    String expression = getExpression();
    return Pattern.compile(expression, Pattern.DOTALL);
  }
  
  public Matcher getMatcher(String text)
  {
    return getPattern().matcher(text);
  }

  public static class Type
  {
    protected String name;
    protected String expr;
    protected Color color;
    
    public Type(String name, String expr, Color color)
    {
      this.name = name;
      this.expr = expr;
      this.color = color;
    }
  }
}

