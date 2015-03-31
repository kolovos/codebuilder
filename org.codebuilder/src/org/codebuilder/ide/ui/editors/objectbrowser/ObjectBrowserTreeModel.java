package org.codebuilder.ide.ui.editors.objectbrowser;

import javax.swing.tree.*;
import javax.swing.event.TreeModelListener;
import java.lang.reflect.*;
import org.codebuilder.template.introspection.DynamicMethod;
/**
 * $Uml.hasStereotype($class,"table")
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class ObjectBrowserTreeModel
    implements TreeModel {

  private Object object = null;

  public ObjectBrowserTreeModel(Object object) {
    this.object = object;
  }

  /**
   * getRoot
   *
   * @return Object
   */
  public Object getRoot() {
    return new BrowsableObject(object);
  }

  /**
   * getChildCount
   *
   * @param parent Object
   * @return int
   */
  public int getChildCount(Object parent) {
    if (parent instanceof ObjectFeature) {
      return 1;
    }
    else if (parent instanceof DynamicMethod){
      return 0;
    }
    else if (parent instanceof BrowsableObject) {
      if ( ( (BrowsableObject) parent).isCollection()) {
        return ( (BrowsableObject) parent).getCollectionItems().size();
      }
      else {
        return ( (BrowsableObject) parent).getFieldsAndMethods().size();
      }
    }
    else {
      return 0;
    }
  }

  /**
   * isLeaf
   *
   * @param node Object
   * @return boolean
   */
  public boolean isLeaf(Object node) {
    return (node instanceof BrowsableObject &&
        ( (BrowsableObject) node).isPrimitive()) || node instanceof DynamicMethod;
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
    if (parent instanceof ObjectFeature) {
      return ( (ObjectFeature) parent).invoke();
    }
    else {
      if ( ( (BrowsableObject) parent).isCollection()) {
        return ( (BrowsableObject) parent).getCollectionItems().get(index);
      }
      else {
        return ( (BrowsableObject) parent).getFieldsAndMethods().get(index);
      }
    }
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
