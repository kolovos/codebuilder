package org.codebuilder.jeditor;
/*
=====================================================================

  REJavaTypes.java

  Created by Claude Duguay
  Copyright (c) 2002

=====================================================================
*/

import java.awt.*;
import javax.swing.text.*;

public class REJavaTypes extends RETypes
{
  public static final String CHAR = "char";
  public static final String TEXT = "text";
  public static final String ATOM = "atom";
  public static final String NUMBER = "number";
  public static final String STAR_COMMENT = "starcomment";
  public static final String LINE_COMMENT = "linecomment";
  public static final String KEYWORDS = "keywords";

  public REJavaTypes()
  {
    addTokenType(LINE_COMMENT, "//[^\n]*", Color.gray);
    addTokenType(STAR_COMMENT,
      "/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/", Color.gray);
    addTokenType(TEXT, "\"(?:\\\\.|[^\"\\\\])*\"", Color.red);
    addTokenType(CHAR, "'(?:\\\\[^']+|[^'])'", Color.red);
    addTokenType(ATOM, "[a-zA-Z]\\w*", null);
    addTokenType(NUMBER, "[0-9]+(\\.[0-9]+)?", Color.magenta);
    addTokenType(KEYWORDS,"",Color.BLUE);
  }

  public static class Kit extends StyledEditorKit
  {
    public Document createDefaultDocument()
    {
      return new EditorDocument(new REJavaTypes());
    }
  }
}

