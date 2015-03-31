package org.codebuilder.ide.ui.editors.xmleditor.actions;

import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class MoveAttributeUpAction
    extends XmlEditorAction{

  public MoveAttributeUpAction(XmlEditor editor){
    super(editor);
    this.putValue(this.NAME, "Move up");
    this.putValue(this.SHORT_DESCRIPTION, "Moves the selected attribute up");
    this.putValue(this.SMALL_ICON, IconFactory.getIconFor("MoveUp"));
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
