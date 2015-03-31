package org.codebuilder.ide.ui.editors.xmleditor.actions;

import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class DeleteAttributeAction
    extends XmlEditorAction{

  public DeleteAttributeAction(XmlEditor editor){
    super(editor);
    this.putValue(this.NAME, "Delete attribute");
    this.putValue(this.SHORT_DESCRIPTION, "Deletes the selected attribute");
    this.putValue(this.SMALL_ICON, IconFactory.getIconFor("DeleteAttribute"));
  }

  /**
   * perform
   *
   * @param o Object
   * @return boolean
   */
  public boolean perform(Object o) {

    Attribute a = editor.getAttributeEditor().getSelectedAttribute();

    if (a == null) return false;

    a.getParent().removeAttribute(a);

    editor.update();
    return false;
  }
}
