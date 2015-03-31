package org.codebuilder.ide.ui.actions.document;

import javax.swing.*;

import org.codebuilder.ide.*;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.editors.EditorFactory;
import org.codebuilder.project.*;
import org.codebuilder.ide.feedback.*;

public class EditDocumentAction
    extends AbstractDocumentAction {

  Document document = null;
  IEditor editor = null;

  /**
   * Initialize the action
   */
  public EditDocumentAction(Document document, IEditor editor) {
    super();
    this.document = document;
    this.editor = editor;
    init();
  }

  public EditDocumentAction(Document document){
    super();
    this.document = document;
    this.editor = EditorFactory.getInstance().getEditorFor(document);
    init();
  }

  private void init() {
    putValue(Action.NAME, editor.getName());
    putValue(Action.SHORT_DESCRIPTION,
             "Opens the current document for editing using " + editor.getName());
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

    // ---------------------
    // See if the object is already
    // being edited
    // ---------------------

    IDE ide = IDE.getInstance();
    IEditor existingEditor = ide.getEditorsPanel().getEditorFor(document);
    if (existingEditor != null) {
      ide.getEditorsPanel().activateEditor(existingEditor);
      return true;
    }

    //------------------
    // Since the object
    // is not null and it
    // is not beeing edited
    // ,see if an editor can
    // be created for it
    //------------------
    //editor = EditorFactory.getInstance().getEditorFor((Document)object);
    IEditor newEditor = null;

    try {
      newEditor = (IEditor) editor.getClass().newInstance();
      newEditor.load(document);
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
    }

    if (newEditor == null) {
      return false;
    }

    // ---------------------
    // Since we have the editor
    // add it to the editors panel
    // ---------------------

    ide.getEditorsPanel().addEditor(newEditor);
    ide.getEditorsPanel().activateEditor(newEditor);

    return true;
  }

}
