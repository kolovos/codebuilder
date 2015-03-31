package org.codebuilder.ide.ui;

import java.awt.*;
import javax.swing.*;
import org.codebuilder.ide.ui.actions.console.*;
import org.codebuilder.ide.feedback.*;
import org.codebuilder.ide.projectmanagement.*;
import javax.swing.text.*;
import org.codebuilder.ide.ui.swing.*;

public class ConsolePanelOld
    extends JPanel
    implements FeedbackListener, ProjectBuildListener {

  private JTextPane messagesText = new JTextPane();
  private JToolBar toolbar = new JToolBar();
  private JScrollPane scrollPane = new JScrollPane();

  public ConsolePanelOld() {

    FeedbackManager.getInstance().addFeedbackListener(this);
    ProjectBuildManager.getInstance().addProjectBuildListener(this);


    // ---------------------
    // Add the buttons and the
    // looks to the toolbar
    // ---------------------

    toolbar.add(ClearConsoleAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(SaveConsoleAction.getInstance());
    //toolbar.setFloatable(false);
    toolbar.setRollover(true);
    toolbar.setOrientation(toolbar.VERTICAL);

    setLayout(new BorderLayout());
    add(toolbar, BorderLayout.WEST);


    // ---------------------
    // Stylize the messagesText
    // ---------------------
    messagesText.setFont(new java.awt.Font("Courier New", 0, 12));
    messagesText.setEditable(false);

    // ---------------------
    // Add the messagesText
    // ---------------------
    scrollPane = ComponentFactory.createJScrollPane(messagesText);
    add(scrollPane, BorderLayout.CENTER);
  }

  public void print(String message) {

    try {
      messagesText.getDocument().insertString(messagesText.getDocument().
                                              getLength(), message, null);
      messagesText.setCaretPosition(messagesText.getDocument().getLength());
    }
    catch (BadLocationException ex) {
      ex.printStackTrace();
    }
    //messagesText.setText(messagesText.getText() + message);
    //messagesText.setCaretPosition(messagesText.getDocument().getLength());
    /*
    try {
      ProjectBuildManager.getInstance().sleep(500);
    }
    catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    */
  }

  public void println(String message) {
    print(message + "\n");
  }

  public void print(Object o) {
    if (o == null) {
      return;
    }
    print(o.toString());
  }

  public void println(Object o) {
    if (o == null) {
      return;
    }
    println(o.toString());
  }

  public void clear() {
    messagesText.setText("");
  }

  /**
   * buildCanceled
   */
  public void buildCanceled() {
    this.printSystemMessage("Build canceled");
  }

  /**
   * buildCompleted
   */
  public void buildCompleted() {
    this.printSystemMessage("Build completed");
  }

  /**
   * buildStarted
   */
  public void buildStarted() {
    this.clear();
    this.printSystemMessage("Build started");
  }

  private void printSystemMessage(Object obj){
    Style systemMessage = messagesText.getStyledDocument().addStyle("systemMessage", messagesText.getStyle("normal"));
    StyleConstants.setForeground(systemMessage, Color.GRAY);

    try {
      messagesText.getDocument().insertString(messagesText.getDocument().
                                              getLength(), obj.toString() + "\n", systemMessage);
    }
    catch (BadLocationException ex) {
      ex.printStackTrace();
    }

  }

  /**
   * progressUpdated
   */
  public void progressUpdated() {
    this.println(ProjectBuildManager.getInstance().getProgress().get(
        ProjectBuildManager.
        getInstance().getProgress().size() - 1).toString());
  }

  /**
   * feedbackReceived
   *
   * @param feedback Feedback
   */
  public void feedbackReceived(Feedback feedback) {
    if (feedback.getSeverity() != FeedbackSeverity.INFORMATION)
      this.print(feedback.getSeverity().getValue() + " : ");
    this.print(feedback.getMessage());
    this.println(feedback.getDetails());
  }

  /**
   * buildFailed
   */
  public void buildFailed() {
    this.println("Build failed");
  }

  public void error(Object obj){
    Style error = messagesText.getStyledDocument().addStyle("error", messagesText.getStyle("normal"));
    StyleConstants.setForeground(error, Color.RED);

    try {
      messagesText.getDocument().insertString(messagesText.getDocument().
                                              getLength(), obj.toString() + "\n", messagesText.getStyle("error"));
    }
    catch (BadLocationException ex) {
      ex.printStackTrace();
    }
  }
}
