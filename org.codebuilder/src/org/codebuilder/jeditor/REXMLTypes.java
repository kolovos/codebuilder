package org.codebuilder.jeditor;
/*
=====================================================================

  REXMLTypes.java

  Created by Claude Duguay
  Copyright (c) 2002

=====================================================================
*/

import java.awt.*;
import javax.swing.text.*;

public class REXMLTypes extends RETypes
{
  public static final String TAG = "tag";
  public static final String TEXT = "text";
  public static final String COMMENT = "comment";

  public REXMLTypes()
  {
    addTokenType(COMMENT, "<!--.*-->", Color.gray);
    addTokenType(TAG, "<[^ ]*|[\\?]?>", Color.blue);
    addTokenType(TEXT, "\"(?:\\\\.|[^\"\\\\])*\"", Color.red);
  }

  public static class Kit extends NumberedEditorKit
  {
    public Document createDefaultDocument()
    {
      return new EditorDocument(new REXMLTypes());
    }
  }
}

