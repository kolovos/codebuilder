package org.codebuilder.ide.ui.actions.document;

import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.IDE;

public abstract class AbstractDocumentAction extends IDEAbstractAction{
  public AbstractDocumentAction() {
    super();
  }

  protected IEditor getCurrentEditor(){
    IDE ide = IDE.getInstance();
    return ide.getEditorsPanel().getCurrentEditor();
  }

}
