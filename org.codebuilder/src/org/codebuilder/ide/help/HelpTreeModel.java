package org.codebuilder.ide.help;

import javax.swing.tree.*;
import javax.swing.event.TreeModelListener;
import java.io.*;

public class HelpTreeModel implements TreeModel{

  private File root = new File("help");
  private HelpFileFilter helpFileFilter = new HelpFileFilter();

  public HelpTreeModel() {
  }

  /**
   * getRoot
   *
   * @return Object
   */
  public Object getRoot() {
    return root;
  }

  /**
   * getChildCount
   *
   * @param parent Object
   * @return int
   */
  public int getChildCount(Object parent) {
    File file = (File) parent;

    if (file.isDirectory())
      return file.listFiles(helpFileFilter).length;
    else
      return 0;
  }

  /**
   * isLeaf
   *
   * @param node Object
   * @return boolean
   */
  public boolean isLeaf(Object node) {
    return ((File) node).isFile();
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
    return ((File) parent).listFiles(helpFileFilter)[index];
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
  }

}
