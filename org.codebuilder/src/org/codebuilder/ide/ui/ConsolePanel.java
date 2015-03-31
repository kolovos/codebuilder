package org.codebuilder.ide.ui;

import java.awt.*;
import javax.swing.*;
import org.codebuilder.ide.ui.actions.console.*;
import org.codebuilder.ide.feedback.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.swing.*;
import javax.swing.table.*;
import javax.swing.event.TableModelListener;
import org.codebuilder.ide.feedback.Feedback;
import org.codebuilder.ide.ui.swing.renderers.*;
import javax.swing.event.ListDataListener;

public class ConsolePanel
    extends JPanel
    implements FeedbackListener, ProjectBuildListener {


  private JList consoleList = new JList();
  private JToolBar toolbar = new JToolBar();
  private JScrollPane scrollPane = new JScrollPane();

  public ConsolePanel() {

    FeedbackManager.getInstance().addFeedbackListener(this);
    ProjectBuildManager.getInstance().addProjectBuildListener(this);


    // ---------------------
    // Add the buttons and the
    // looks to the toolbar
    // ---------------------

    toolbar.add(ClearConsoleAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(SaveConsoleAction.getInstance());
    toolbar.setRollover(true);
    toolbar.setOrientation(toolbar.VERTICAL);

    setLayout(new BorderLayout());
    add(toolbar, BorderLayout.WEST);

    // ---------------------
    // Add the console table
    // ---------------------
    consoleList.setModel(new ConsoleListModel());
    consoleList.setCellRenderer(new ConsoleCellRenderer());
    scrollPane = ComponentFactory.createJScrollPane(consoleList);
    add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * buildCanceled
   */
  public void buildCanceled() {
    FeedbackManager.getInstance().report("Build canceled", "The build process has been canceled by the user", FeedbackSeverity.INFORMATION);
  }

  /**
   * buildCompleted
   */
  public void buildCompleted() {
    FeedbackManager.getInstance().report("Build completed", "The build process has been succesfully completed", FeedbackSeverity.SUCCESS);
  }

  /**
   * buildFailed
   */
  public void buildFailed() {
    FeedbackManager.getInstance().report("Build failed", "Build has failed due to error(s)", FeedbackSeverity.ERROR);
  }

  /**
   * buildStarted
   */
  public void buildStarted() {
    FeedbackManager.getInstance().clearMessages();
    FeedbackManager.getInstance().report("Build started", "The build process has started", FeedbackSeverity.INFORMATION);
  }

  /**
   * progressUpdated
   */
  public void progressUpdated() {
  }

  /**
   * feedbackReceived
   *
   * @param feedback Feedback
   */
  public void feedbackReceived(Feedback feedback) {
    JComponentUtilities.updateUI(consoleList);
  }

  class ConsoleListModel implements ListModel{
    /**
     * getSize
     *
     * @return int
     */
    public int getSize() {
      return FeedbackManager.getInstance().getMessages().size();
    }

    /**
     * getElementAt
     *
     * @param index int
     * @return Object
     */
    public Object getElementAt(int index) {
      return FeedbackManager.getInstance().getMessages().get(index);
    }

    /**
     * addListDataListener
     *
     * @param l ListDataListener
     */
    public void addListDataListener(ListDataListener l) {
    }

    /**
     * removeListDataListener
     *
     * @param l ListDataListener
     */
    public void removeListDataListener(ListDataListener l) {
    }

  }
}
