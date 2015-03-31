package org.codebuilder.ide.ui.actions.project;

import org.codebuilder.ide.ui.actions.*;
import javax.swing.*;
import org.codebuilder.ide.projectmanagement.*;

/**
 * <p>Title: CodeBuilder</p>
 *


 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: Dimitrios Kolovos</p>
 *
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */
public class ReOpenProjectAction extends IDEAbstractAction{
  public ReOpenProjectAction(String projectFileName) {
    super();
    this.putValue(AbstractAction.NAME, projectFileName);
    this.putValue(AbstractAction.SHORT_DESCRIPTION, "Re-open " + projectFileName);
  }

  public boolean perform(Object object) {
    //ProjectManager.getInstance();
    return true;
  }
}
