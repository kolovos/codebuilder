package org.codebuilder.ide.ui.editors.xmleditor.actions;

import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class AddElementAsChildAction extends XmlEditorAction{

  public AddElementAsChildAction(XmlEditor editor){
    super(editor);
    this.putValue(this.NAME, "Add as child");
    this.putValue(this.SHORT_DESCRIPTION, "Adds a new element as a child of the selected one");
    this.putValue(this.SMALL_ICON, IconFactory.getIconFor("AddElementAsChild"));
  }

  /**
   * perform
   *
   * @param o Object
   * @return boolean
   */
  public boolean perform(Object o) {
    Element e;

    if (o != null)
      e = (Element) o;
    else {
      e = new Element("NewElement");
    }

    Element p = editor.getDocumentTree().getSelectedElement();

    if (p == null) return false;

    p.addContent(e);
    editor.update();
    return false;
  }
}
