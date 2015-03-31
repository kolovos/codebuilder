package org.codebuilder.ide.ui.actions.document;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.editors.EditorFactory;
import org.codebuilder.project.*;
import org.codebuilder.ide.feedback.*;
import java.io.*;

public class SelectSystemEditDocumentAction
    extends AbstractDocumentAction {

  Document document = null;

  public SelectSystemEditDocumentAction(Document document){
    super();
    this.document = document;
    init();
  }

  private void init() {
    putValue(Action.NAME, "Select System editor...");
    putValue(Action.SHORT_DESCRIPTION,
             "Opens the current document for editing using a selected system editor (windows-only)");
    putValue(Action.LONG_DESCRIPTION, "");
    putValue(Action.SMALL_ICON, IconFactory.Editor);
  }

  /**
   * Performs the action
   * @param Object - The object to perform the action on
   * @return boolean - If the action succeeded
   */
  public boolean perform(Object object) {
    if (document == null) {
      return false;
    }

    /**
     * @todo Add fix for linux
     */
    Runtime r = Runtime.getRuntime();
    try {
      //r.exec("cmd.exe /c start " + document.getPath());
      r.exec("RunDll32.exe Shell32.dll,OpenAs_RunDLL " + document.getPath());
    }
    catch (IOException ex) {
      ex.printStackTrace();
      return false;
    }

    return true;
  }

}
