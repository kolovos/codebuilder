package org.codebuilder.jeditor;
/*
=====================================================================

  RETokenizer.java
  
  Created by Claude Duguay
  Copyright (c) 2002
  
=====================================================================
*/

import java.util.*;
import java.util.regex.*;

public class RETokenizer
{
  protected RETypes types;
  protected Matcher matcher;
  
  public RETokenizer(RETypes types, String text)
  {
    this.types = types;
    matcher = types.getMatcher(text);
  }
  
  protected Token getToken(int pos)
  {
    int count = types.getTypeCount();
    for (int i = 1; i <= count; i++)
    {
      String token = matcher.group(i);
      if (token != null)
      {
        String type = types.getName(i - 1);
        return new Token(token, type, pos);
      }
    }
    return null;
  }
  
  public Token nextToken()
  {
    if (matcher.find())
    {
      return getToken(matcher.start());
    }
    return null;
  }
  
  public static class Token
  {
    public String token;
    public String type;
    protected int pos;
    
    public Token(String token, String type, int pos)
    {
      this.token = token;
      this.type = type;
      this.pos = pos;
    }
    
    public String getText()
    {
      return token;
    }
    
    public String getType()
    {
      return type;
    }
    
    public int getPos()
    {
      return pos;
    }
    
    public String toString()
    {
      return type + "(" + token + ", " + pos + ')';
    }
  }
}

