package org.codebuilder.ide.ui.editors.xmleditor;

import javax.swing.tree.TreeModel;
import org.jdom.*;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

public class DocumentTreeModel implements TreeModel{

  private Document document;

  public DocumentTreeModel(){

  }

  public DocumentTreeModel(Document document){
    this.document = document;
  }

  public void setDocument(Document document){
    this.document = document;
  }

  /**
   * getRoot
   * @todo Extend the Element object to change the toString to give only the name
   * @return Object
   */
  public Object getRoot() {
    if (!document.hasRootElement())
      return null;
    else
      return document.getRootElement();
  }

  /**
   * getChildCount
   *
   * @param parent Object
   * @return int
   */
  public int getChildCount(Object parent) {
    return ((Element) parent).getChildren().size();
  }

  /**
   * isLeaf
   *
   * @param node Object
   * @return boolean
   */
  public boolean isLeaf(Object node) {
    return ((Element) node).getChildren().size() == 0;
  }

  /**
   * addTreeModelListener
   *
   * @param l TreeModelListener
   */
  public void addTreeModelListener(TreeModelListener l) {
  }

  /**
   * removeTreeModelListener
   *
   * @param l TreeModelListener
   */
  public void removeTreeModelListener(TreeModelListener l) {
  }

  /**
   * getChild
   *
   * @param parent Object
   * @param index int
   * @return Object
   */
  public Object getChild(Object parent, int index) {
    return ((Element) parent).getChildren().get(index);
  }

  /**
   * getIndexOfChild
   *
   * @param parent Object
   * @param child Object
   * @return int
   */
  public int getIndexOfChild(Object parent, Object child) {
    return 0;
  }

  /**
   * valueForPathChanged
   *
   * @param path TreePath
   * @param newValue Object
   */
  public void valueForPathChanged(TreePath path, Object newValue) {
    ((Element) path.getLastPathComponent()).setName(newValue.toString());
  }

}
