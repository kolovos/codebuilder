package org.codebuilder.ide.plugins;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import org.codebuilder.ide.ui.IDE;
import org.codebuilder.ide.projectmanagement.ProjectManager;
import org.codebuilder.ide.feedback.FeedbackManager;
import javax.swing.Action;
import org.codebuilder.ide.ui.resources.IconFactory;
import javax.swing.ImageIcon;
import javax.swing.*;

public class PluginAction extends AbstractAction{

  private Plugin plugin;

  public PluginAction(Plugin plugin) {
    this.plugin = plugin;
    putValue(Action.NAME, plugin.getName());
    putValue(Action.SHORT_DESCRIPTION, plugin.getShortDescription());
    putValue(Action.LONG_DESCRIPTION, plugin.getDescription());
    Icon icon = plugin.getIcon();
    if (icon == null) icon = IconFactory.Plugin;
    putValue(Action.SMALL_ICON, icon);
  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    plugin.start(IDE.getInstance(), ProjectManager.getInstance(), FeedbackManager.getInstance());
  }
}
