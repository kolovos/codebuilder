package org.codebuilder.ide.ui.editors.highlighting;

import java.util.*;
import org.jdom.*;
import org.jdom.input.*;
import java.io.*;
import org.codebuilder.ide.feedback.*;
import org.codebuilder.project.*;

public class HighlighterManager {

  private static HighlighterManager instance = new HighlighterManager();
  private TreeMap highlighters = new TreeMap();

  public HighlighterManager() {
    org.jdom.Document document = null;
    SAXBuilder builder = new SAXBuilder();
    try {
      document = builder.build("config/highlighters.xml");
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
      return;
    }
    ListIterator li = document.getRootElement().getChildren().listIterator();
    while (li.hasNext()) {
      Element highlighterElement = (Element) li.next();
      Highlighter highlighter = new Highlighter();
      highlighter.setLanguage(highlighterElement.getAttributeValue("language"));
      highlighter.setExtensions(highlighterElement.getAttributeValue("extensions").split(","));
      ListIterator hi = highlighterElement.getChildren().listIterator();
      while (hi.hasNext()) {
        Element highlightableElement = (Element) hi.next();
        Highlightable highlightable = new Highlightable();
        highlightable.setName(highlightableElement.getAttributeValue("name"));
        highlightable.setExpression(highlightableElement.getAttributeValue(
            "expression"));
        highlightable.setColor(highlightableElement.getAttributeValue("color"));
        highlighter.getHighlightables().add(highlightable);
      }
      highlighters.put(highlighter.getLanguage(), highlighter);

    }
  }

  public static HighlighterManager getInstance() {
    return instance;
  }

  public Set getSupportedLanguages() {
    return highlighters.keySet();
  }

  public Highlighter getHighlighterFor(String language) {
    if (highlighters.containsKey(language)) {
      return (Highlighter) highlighters.get(language);
    }
    else {
      return new Highlighter();
    }
  }

  public Highlighter getHighlighterFor(org.codebuilder.project.Document document){
    if (document instanceof Template){
      Highlighter base = instance.getHighlighterFor(((Template) document).getLanguage());
      Highlighter velocity = instance.getHighlighterFor("vtl");
      return base.combine(velocity);
    }
    else {
      Iterator it = highlighters.values().iterator();
      while (it.hasNext()){
        Highlighter highlighter = (Highlighter) it.next();
        if (highlighter.accept(document.getPath())) return highlighter;
      }
    }
    return new Highlighter();
  }

  public static void main(String args[]){

  }

}
