package org.codebuilder.ide.ui.actions.edit;

import org.codebuilder.ide.ui.actions.IDEAbstractAction;
import org.codebuilder.ide.ui.editors.IEditor;
import org.codebuilder.ide.ui.IDE;

public abstract class AbstractEditAction extends IDEAbstractAction{
  public AbstractEditAction() {
    super();
  }

  protected IEditor getCurrentEditor(){
    IDE ide = IDE.getInstance();
    return ide.getEditorsPanel().getCurrentEditor();
  }

}
