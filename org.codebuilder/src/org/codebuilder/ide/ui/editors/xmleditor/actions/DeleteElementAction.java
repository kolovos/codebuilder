package org.codebuilder.ide.ui.editors.xmleditor.actions;

import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class DeleteElementAction
    extends XmlEditorAction{

  public DeleteElementAction(XmlEditor editor){
    super(editor);
    this.putValue(this.NAME, "Delete element");
    this.putValue(this.SHORT_DESCRIPTION, "Deletes the selected element");
    this.putValue(this.SMALL_ICON, IconFactory.getIconFor("DeleteAttribute"));
  }

  /**
   * perform
   *
   * @param o Object
   * @return boolean
   */
  public boolean perform(Object o) {

    Element e = editor.getDocumentTree().getSelectedElement();

    if (e == null || e.getParent() == null || e.getParent() instanceof Document) return false;

    e.getParent().removeContent(e);
    editor.update();
    return false;
  }
}
