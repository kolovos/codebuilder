package org.codebuilder.ide.help;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;
import org.codebuilder.ide.ui.swing.*;

public class HelpTree extends JPanel implements TreeSelectionListener{

  private JTree tree = ComponentFactory.createJTree();

  public HelpTree() {
    init();
  }

  public void init(){
    this.setLayout(new BorderLayout());
    tree.addTreeSelectionListener(this);
    tree.setCellRenderer(new HelpTreeCellRenderer());
    tree.setModel(new HelpTreeModel());
    this.add(ComponentFactory.createJScrollPane(tree), BorderLayout.CENTER);
  }

  /**
   * valueChanged
   *
   * @param e TreeSelectionEvent
   */
  public void valueChanged(TreeSelectionEvent e) {
    File file = (File) e.getPath().getLastPathComponent();
    if (file.isFile())
      HelpViewer.getInstance().navigate(file);
  }

}
