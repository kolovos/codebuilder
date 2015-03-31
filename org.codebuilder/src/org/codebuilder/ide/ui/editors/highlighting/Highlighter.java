package org.codebuilder.ide.ui.editors.highlighting;
/*
=====================================================================

  REJavaTypes.java

  Created by Claude Duguay
  Copyright (c) 2002

=====================================================================
*/

import java.awt.*;
import javax.swing.text.*;
import org.codebuilder.jeditor.*;
import java.util.*;

public class Highlighter extends RETypes
{

  private ArrayList highlightables = new ArrayList();
  private String[] extensions = new String[]{};

  private Highlighter thiz = this;
  private String language;

  public Highlighter()
  {

  }

  private void load(){
    ListIterator li = highlightables.listIterator();
    while (li.hasNext()){
      Highlightable highlightable = (Highlightable) li.next();
      addTokenType(highlightable.getName(), highlightable.getExpression(), highlightable.getColor());
    }
  }

  public StyledEditorKit getKit(){
    load();
    if (this.highlightables.size() == 0){
      return new NumberedEditorKit();
    }
    else{
      return new Kit();
    }
  }

  public ArrayList getHighlightables() {
    return highlightables;
  }
  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public Highlighter combine(Highlighter highlighter){
    Highlighter combination = new Highlighter();
    combination.getHighlightables().addAll(this.getHighlightables());
    combination.getHighlightables().addAll(highlighter.getHighlightables());
    return combination;
  }
  public String[] getExtensions() {
    return extensions;
  }
  public void setExtensions(String[] extensions) {
    this.extensions = extensions;
  }

  public boolean accept(String fileName){
    for (int i=0;i<extensions.length;i++){
      if (fileName.endsWith(extensions[i]))
        return true;
    }
    return false;
  }

  class Kit extends NumberedEditorKit
  {
    public Document createDefaultDocument()
    {
      return new EditorDocument(thiz);
    }
  }
}

