package org.codebuilder.ide.ui.editors.objectbrowser;

import javax.swing.*;
import java.awt.*;
import org.codebuilder.ide.ui.swing.*;

public class ObjectViewerPanel
    extends JPanel {

  private JTree tree = ComponentFactory.createJTree();
  private Object object;

  public ObjectViewerPanel() {
    this.removeAll();
    this.setLayout(new BorderLayout());
    this.add(new JScrollPane(tree), BorderLayout.CENTER);
    tree.setCellRenderer(new ObjectBrowserTreeCellRenderer());
    tree.setModel(new ObjectBrowserTreeModel("Use $objectViewer.setObject($anObject) to view it here"));
  }

  public void setObject(Object object) {
    try{
      this.object = object;
      tree.setModel(new ObjectBrowserTreeModel(object));
      JComponentUtilities.updateUI(tree);
    }
    catch (Exception ex){
      setObject(ex);
    }
  }

  public Object getObject(){
    return object;
  }

  public static void main(String[] args){
    JFrame frame = ComponentFactory.createJFrame("Object viewer test");
    ObjectViewerPanel objectViewer = new ObjectViewerPanel();
    objectViewer.setObject(null);
    frame.getContentPane().add(objectViewer, BorderLayout.CENTER);
    frame.setVisible(true);
  }

}
