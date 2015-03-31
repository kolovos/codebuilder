package org.codebuilder.ide.help.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import org.codebuilder.ide.ui.resources.*;

public class NavigateBackAction extends AbstractAction{

  public NavigateBackAction() {
    this.putValue(this.NAME, "Back");
    this.putValue(this.SHORT_DESCRIPTION, "Back to the previous help page");
    this.putValue(this.SMALL_ICON, IconFactory.Back);
  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
  }
}
