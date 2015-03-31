package org.codebuilder.ide.ui.editors.xmleditor;

import java.io.*;
import javax.swing.*;
import org.jdom.*;

public class Workbench
    extends JFrame {

  public Workbench() throws IOException, JDOMException {
    XmlEditor editor = new XmlEditor();
    this.getContentPane().add(editor);
    //editor.load("tools/tools.xml");
    this.setTitle("Visual XML Editor");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 100, 500, 500);
    this.setVisible(true);
  }

}
