package org.codebuilder.jeditor;
/*
=====================================================================

  KeywordList.java
  
  Created by Claude Duguay
  Copyright (c) 2002
  
=====================================================================
*/

import java.io.*;
import java.awt.*;
import java.util.*;

public class KeywordList
{
  protected String name;
  protected Color foreground;
  protected Color background;
  protected Set wordlist;
  
  public KeywordList(String name)
  {
    this(name, Color.black, Color.white, new HashSet());
  }
  
  public KeywordList(String name,
    Color foreground, Color background)
  {
    this(name, foreground, background, new HashSet());
  }
  
  public KeywordList(String name,
    Color foreground, Color background,
    Set wordlist)
  {
    this.name = name;
    this.wordlist = wordlist;
    this.foreground = foreground;
    this.background = background;
  }
  
  public String getName()
  {
    return name;
  }
  
  public Color getForeground()
  {
    return foreground;
  }
  
  public Color getBackground()
  {
    return background;
  }
  
  public boolean isMember(String word)
  {
    return wordlist.contains(word);
  }
  
  public void readFile(String filename)
  {
    BufferedReader reader = null;
    try
    {
      wordlist.clear();
      String line = null;
      reader = new BufferedReader(new FileReader(filename));
      String fore = reader.readLine().trim();
      foreground = Color.decode(fore);
      String back = reader.readLine().trim();
      background = Color.decode(back);
      while ((line = reader.readLine()) != null)
      {
        String word = line.trim();
        if (!word.equals(""))
        {
          wordlist.add(word);
        }
      }
    }
    catch (IOException e)
    {
      throw new IllegalArgumentException(
        "Unable to read keyword file");
    }
    finally
    {
      try
      {
        reader.close();
      }
      catch (IOException e) {}
    }
  }
}

