package org.codebuilder.jeditor;

/*
 =====================================================================

  EditorDocument.java

  Created by Claude Duguay
  Copyright (c) 2002

 =====================================================================
 */

import java.awt.*;
import javax.swing.text.*;

public class EditorDocument
    extends DefaultStyledDocument {

  protected RETypes types;

  public EditorDocument(RETypes types) {
    Style defaultStyle = getStyle("default");
    StyleConstants.setFontFamily(defaultStyle, "Courier New");
    StyleConstants.setFontSize(defaultStyle, 11);
    StyleConstants.setLeftIndent(defaultStyle, 25);
    StyleConstants.setSpaceBelow(defaultStyle, 5);

    TabStop[] tabStops = new TabStop[100];
    for (int i=0; i<100; i++){
      tabStops[i] = new TabStop(20*i);
    }
    TabSet tabSet = new TabSet(tabStops);

    StyleConstants.setTabSet(defaultStyle, tabSet);
    this.types = types;
    types.setStyles(this);
    KeywordManager.setStyles(this);
  }

  public void insertString(int offset, String text, AttributeSet style) throws
      BadLocationException {
    super.insertString(offset, text, style);
    highlightSyntax();
  }

  public void remove(int offset, int length) throws BadLocationException {
    super.remove(offset, length);
    highlightSyntax();
  }

  public void highlightSyntax() {
    try {
      String text = getText(0, getLength());
      setCharacterAttributes(0, getLength(),
                             getStyle("default"), true);
      RETokenizer.Token token;
      RETokenizer tokenizer = new RETokenizer(types, text);
      int typeCount = types.getTypeCount();
      while ( (token = tokenizer.nextToken()) != null) {
        int pos = token.getPos();
        String type = token.getType();
        String word = token.getText();
        int len = word.length();
        for (int i = 0; i < typeCount; i++) {
          String name = types.getName(i);
          if (type.equals(name)) {
            if (types.getColor(i) == null) {
              String style = KeywordManager.getStyleName(word);
              if (style != null) {
                setCharacterAttributes(
                    pos, len, getStyle(style), false);
              }
            }
            else {
              setCharacterAttributes(
                  pos, len, getStyle(name), false);
            }
          }
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
