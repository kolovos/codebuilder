package org.codebuilder.ide.ui.editors.xmleditor.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import org.codebuilder.ide.ui.editors.xmleditor.*;

public abstract class XmlEditorAction extends AbstractAction{

  protected XmlEditor editor = null;

  public XmlEditorAction(){

  }

  public XmlEditorAction(XmlEditor editor) {
    if (editor == null)
      throw new IllegalArgumentException("editor cannot be null");
    else
      this.editor = editor;
  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    perform(null);
  }

  public abstract boolean perform(Object o);

}
