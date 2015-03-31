package org.codebuilder.ide.ui.editors.xmleditor.actions;

import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class MoveAttributeDownAction
    extends XmlEditorAction{

  public MoveAttributeDownAction(XmlEditor editor){
    super(editor);
    this.putValue(this.NAME, "Move down");
    this.putValue(this.SHORT_DESCRIPTION, "Moves the selected attribute down");
    this.putValue(this.SMALL_ICON, IconFactory.getIconFor("MoveDown"));
  }

  /**
   * perform
   *
   * @param o Object
   * @return boolean
   */
  public boolean perform(Object o) {
    Attribute a;

    if (o != null)
      a = (Attribute) o;
    else {
      a = new Attribute("NewAttribute", "");
    }

    Element e = editor.getDocumentTree().getSelectedElement();

    if (e == null) return false;

    e.getAttributes().add(a);
    editor.update();
    return false;
  }
}
