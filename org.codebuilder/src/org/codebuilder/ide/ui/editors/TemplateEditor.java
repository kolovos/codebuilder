package org.codebuilder.ide.ui.editors;

import org.codebuilder.project.Template;
import org.codebuilder.ide.ui.actions.document.ValidateDocumentAction;
import org.codebuilder.project.*;

public class TemplateEditor
    extends TextEditor{

  public TemplateEditor() {
    toolbar.add(ValidateDocumentAction.getInstance());
  }

  public boolean accept(Document document) {
    return document instanceof Template;
  }

  public String getName() {
    return "Template editor";
  }
}
