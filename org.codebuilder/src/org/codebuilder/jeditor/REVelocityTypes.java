package org.codebuilder.jeditor;
/*
=====================================================================

  REVelocityTypes.java

  Created by Claude Duguay
  Copyright (c) 2002

=====================================================================
*/

import java.awt.*;
import javax.swing.text.*;

public class REVelocityTypes extends RETypes
{
  public static final String TAG = "tag";
  public static final String DIRECTIVE = "directive";
  public static final String COMMENT = "comment";
  public static final String VARIABLE = "variable";
  public static final String BUILTIN = "builtinvariable";
  public static final String STRING = "builtinvariable";

  public REVelocityTypes()
  {
    addTokenType(COMMENT, "##[^\n]*", new Color(0,128,0));
    addTokenType(COMMENT, "#\\*.*?\\*#", new Color(0,128,0));
    addTokenType(VARIABLE, "\\$", new Color(255,0,0));
    addTokenType(DIRECTIVE, "#(foreach|end|if|else|elseif|include|set|in|set|parse|stop|macro)", new Color(0,0,255));
    //addTokenType(STRING, "\".*?\"", Color.PINK);

    //addTokenType(VARIABLE, "x", new Color(128,0,0));
    //addTokenType(TEXT, "\"(?:\\\\.|[^\"\\\\])*\"", Color.red);
  }

  public static class Kit extends NumberedEditorKit
  {
    public Document createDefaultDocument()
    {
      return new EditorDocument(new REVelocityTypes());
    }
  }
}

