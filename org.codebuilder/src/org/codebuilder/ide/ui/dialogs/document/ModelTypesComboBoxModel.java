package org.codebuilder.ide.ui.dialogs.document;

import javax.swing.ComboBoxModel;
import java.util.*;
import javax.swing.event.ListDataListener;
import org.codebuilder.project.DocumentFactory;

class DocumentTypesComboBoxModel implements ComboBoxModel{

  List newDocuments = DocumentFactory.getInstance().createDocuments();
  Object selectedItem;
  /**
   * getSelectedItem
   *
   * @return Object
   */
  public Object getSelectedItem() {
    return selectedItem;
  }

  /**
   * setSelectedItem
   *
   * @param anItem Object
   */
  public void setSelectedItem(Object anItem) {
    if (anItem != null)
    selectedItem = anItem;
  }

  /**
   * getSize
   *
   * @return int
   */
  public int getSize() {
    return newDocuments.size();
  }

  /**
   * getElementAt
   *
   * @param index int
   * @return Object
   */
  public Object getElementAt(int index) {
    return newDocuments.get(index);
  }

  /**
   * addListDataListener
   *
   * @param l ListDataListener
   */
  public void addListDataListener(ListDataListener l) {
  }

  /**
   * removeListDataListener
   *
   * @param l ListDataListener
   */
  public void removeListDataListener(ListDataListener l) {
  }

}
