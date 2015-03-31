package org.codebuilder.ide.ui;

import javax.swing.event.*;
import java.util.*;
import org.codebuilder.project.*;

public class DocumentSelectionManager implements TreeSelectionListener{

  private static DocumentSelectionManager instance = new DocumentSelectionManager();
  private ArrayList listeners = new ArrayList();

  private DocumentSelectionManager() {
  }

  public static DocumentSelectionManager getInstance(){
    return instance;
  }

  public void addDocumentSelectionListener(DocumentSelectionListener listener){
    listeners.add(listener);
  }

  public void removeDocumentSelectionListener(DocumentSelectionListener listener){
    listeners.remove(listener);
  }

  /**
   * valueChanged
   *
   * @param e TreeSelectionEvent
   */
  public void valueChanged(TreeSelectionEvent e) {
    Document newDocument = null;
    Document oldDocument = null;

    if (e.getNewLeadSelectionPath() != null) newDocument = (Document) e.getNewLeadSelectionPath().getLastPathComponent();
    if (e.getOldLeadSelectionPath() != null) oldDocument = (Document) e.getOldLeadSelectionPath().getLastPathComponent();

    ListIterator li = listeners.listIterator();
    while (li.hasNext()){
      DocumentSelectionListener listener = (DocumentSelectionListener) li.next();
      listener.documentSelected(newDocument, oldDocument);
    }
  }
}
