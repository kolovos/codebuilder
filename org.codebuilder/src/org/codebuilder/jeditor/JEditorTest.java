package org.codebuilder.jeditor;
/*
=====================================================================

  JEditorTest.java

  Created by Claude Duguay
  Copyright (c) 2002

=====================================================================
*/

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class JEditorTest extends JPanel
{
  public JEditorTest(File file)
    throws IOException
  {
    KeywordManager.loadKeywordFiles();
    StyledEditorKit kit = null;
    if (file.getName().toLowerCase().endsWith(".xml"))
    {
       kit = new REXMLTypes.Kit();
    }
    if (file.getName().toLowerCase().endsWith(".java"))
    {
       kit = new REJavaTypes.Kit();
    }
    JEditor editor = new JEditor(kit);
    editor.read(new FileReader(file), null);
    setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    setLayout(new GridLayout());
    add(new JScrollPane(editor));
  }

  public static void main(String[] args)
    throws IOException
  {
    File file = new File("example.java");
    JFrame frame = new JFrame("JEditor Test");
    frame.getContentPane().setLayout(new GridLayout());
    frame.getContentPane().add(new JEditorTest(file));
    frame.setSize(640, 480);
    frame.show();
  }
}

