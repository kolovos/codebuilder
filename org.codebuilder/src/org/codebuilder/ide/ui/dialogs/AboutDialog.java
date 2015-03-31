package org.codebuilder.ide.ui.dialogs;

import java.awt.*;
import javax.swing.*;
import org.codebuilder.common.file.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.swing.*;
import net.infonode.tabbedpanel.*;

public class AboutDialog
    extends AbstractDialog {

  TabbedPanel tabs = ComponentFactory.createTabbedPanel();

  public AboutDialog() {
    super();
    this.setTitle("About CodeBuilder");
    this.setDescription("Information about CodeBuilder");

    JTextPane about = ComponentFactory.createJTextPane();

    BatchReader reader = new BatchReader();
    about.setText(reader.readAllNoExceptions("about.txt"));
    about.setCaretPosition(0);
    about.setEditable(false);

    JTextPane todo =  ComponentFactory.createJTextPane();

    todo.setText(reader.readAllNoExceptions("todo.txt"));
    todo.setCaretPosition(0);
    todo.setEditable(false);

    tabs.addTab(ComponentFactory.createTab("About", IconFactory.About, new JScrollPane(about)));
    tabs.addTab(ComponentFactory.createTab("To do", IconFactory.Todo, new JScrollPane(todo)));

    contentPane.setLayout(new BorderLayout());
    contentPane.add(tabs, BorderLayout.CENTER);
    contentPane.setBorder(AbstractDialog.TIGHT_BORDER);

  }

  /**
   * ok
   */
  protected void ok() {
    cancel();
  }

}
