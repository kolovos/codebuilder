package org.codebuilder.ide.ui.editors.xmleditor.actions;

import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class AddAttributeAction
    extends XmlEditorAction{

  public AddAttributeAction(XmlEditor editor){
    super(editor);
    this.putValue(this.NAME, "Add an attribute");
    this.putValue(this.SHORT_DESCRIPTION, "Adds a new attribute to the selected element");
    this.putValue(this.SMALL_ICON, IconFactory.getIconFor("AddAttribute"));
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
