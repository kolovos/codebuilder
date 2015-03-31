package org.codebuilder.ide.ui.editors.xmleditor.actions;

import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.jdom.*;
import org.codebuilder.ide.ui.resources.IconFactory;

public class AddElementAsNextAction
    extends XmlEditorAction{

  public AddElementAsNextAction(XmlEditor editor){
    super(editor);
    this.putValue(this.NAME, "Add next");
    this.putValue(this.SHORT_DESCRIPTION, "Adds a new element next the selected one");
    this.putValue(this.SMALL_ICON, IconFactory.getIconFor("AddElementAsNext"));
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

    if (p == null || p.getParent() == null) return false;

    p.getParent().getContent().add(p.getParent().indexOf(p) + 1, e);
    editor.update();
    return false;
  }
}
