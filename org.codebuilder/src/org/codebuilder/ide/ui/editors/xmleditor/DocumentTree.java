package org.codebuilder.ide.ui.editors.xmleditor;

import javax.swing.*;
import java.awt.BorderLayout;
import org.jdom.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.util.ArrayList;
import java.util.ListIterator;
import org.codebuilder.ide.ui.editors.xmleditor.renderers.*;
import org.codebuilder.ide.ui.swing.*;

public class DocumentTree
    extends JPanel
    implements TreeSelectionListener {

  private JTree tree = ComponentFactory.createJTree();
  private DocumentTreeModel documentTreeModel = new DocumentTreeModel();
  private Document document = new Document();
  private ArrayList documentTreeSelectionListeners = new ArrayList();
  private Element e;

  public DocumentTree() {
    init();
  }

  public DocumentTree(Document document) {
    this.document = document;
    init();
  }

  private void init() {
    this.setLayout(new BorderLayout());
    this.add(ComponentFactory.createJScrollPane(tree), BorderLayout.CENTER);
    documentTreeModel = new DocumentTreeModel(this.document);
    tree.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    tree.setModel(documentTreeModel);
    tree.setEditable(true);
    //tree.setCellRenderer(new DocumentTreeCellRenderer());
    tree.setCellRenderer(new SmartElementRenderer());
    tree.getSelectionModel().addTreeSelectionListener(this);
  }

  public Document getDocument() {
    return document;
  }

  public void setDocument(Document document) {
    this.document = document;
    documentTreeModel.setDocument(document);
    tree.updateUI();
  }

  /**
   * valueChanged
   *
   * @param e TreeSelectionEvent
   */
  public void valueChanged(TreeSelectionEvent e) {
    ListIterator li = documentTreeSelectionListeners.listIterator();
    while (li.hasNext()) {
      ( (DocumentTreeSelectionListener) li.next()).valueChanged( (Element) e.
          getPath().getLastPathComponent());
    }
  }

  public void addDocumentTreeSelectionListener(DocumentTreeSelectionListener
                                               listener) {
    documentTreeSelectionListeners.add(listener);
  }

  public void removeDocumentTreeSelectionListener(DocumentTreeSelectionListener
                                                  listener) {
    documentTreeSelectionListeners.remove(listener);
  }

  public Element getSelectedElement(){
    if (tree.getSelectionPath() == null)
      return null;
    else
      return (Element) tree.getSelectionPath().getLastPathComponent();
  }

}
