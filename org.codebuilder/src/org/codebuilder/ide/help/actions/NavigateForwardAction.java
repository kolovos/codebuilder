package org.codebuilder.ide.help.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import org.codebuilder.ide.ui.resources.*;

public class NavigateForwardAction
    extends AbstractAction {

  public NavigateForwardAction() {
    this.putValue(this.NAME, "Forward");
    this.putValue(this.SHORT_DESCRIPTION, "Forward to the next help page");
    this.putValue(this.SMALL_ICON, IconFactory.Forward);

  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
  }
}
