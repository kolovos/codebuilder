package org.codebuilder.ide.ui;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.actions.*;
import org.codebuilder.ide.ui.dialogs.*;
import org.codebuilder.ide.ui.editors.objectbrowser.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.project.*;
import org.codebuilder.template.*;

public class IDE
    extends JFrame
    implements ProjectChangeListener {

  private static IDE instance = null;

  protected String title = "CodeBuilder Studio";
  protected ProjectPanel projectPanel = new ProjectPanel();
  protected GeneratedFilesPanel generatedFilesPanel = new GeneratedFilesPanel();
  protected EditorsPanel editorsPanel = new EditorsPanel();
  protected JLabel statusBar = new JLabel();
  protected LastRecentlyUsed lastRecentlyUsedProjects = new LastRecentlyUsed();
  protected ToolsPanel toolsPanel = new ToolsPanel();
  protected ConsolePanel consolePanel = new ConsolePanel();
  protected ObjectViewerPanel objectViewerPanel = new ObjectViewerPanel();

  JToolBar toolbar = new JToolBar();
  JToolBar pluginstoolbar = new JToolBar();

  private IDE() {
    super();
    init();
  }

  public static void reset() {
    instance = null;
  }

  public static IDE getInstance() {
    if (instance == null) {
      if (instance == null) {
        instance = new IDE();
      }
    }
    return instance;
  }

  public void init() {
    System.gc();
    TemplateEngine.reset();

    // ---------------------
    // Declare the bounds of the
    // IDE so that the LayoutManager
    // can put the splitters
    // in nice positions
    // ---------------------

    setTitle(title);
    setIconImage(IconFactory.CodeBuilder.getImage());
    Rectangle fullScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().
        getMaximumWindowBounds();
    Rectangle ideScreen = new Rectangle(100, 50, fullScreen.width - 200,
                                        fullScreen.height - 100);
    this.setBounds(fullScreen);

    // ---------------------
    // Register with the ProjectManager
    // to update IDE title
    // ---------------------
    ProjectManager.getInstance().addProjectChangeListener(this);

    // ---------------------
    // Arrange the panels of the
    // IDE, create the menus and
    // the toolbars
    // ---------------------

    //IDEDockableLayoutManager layoutManager = new IDEDockableLayoutManager();
    IDEInfonodeLayoutManager layoutManager = new IDEInfonodeLayoutManager();

    layoutManager.populateMenus(this);
    layoutManager.populateToolbars(this);
    layoutManager.arrangePanels(this);

    // ---------------------
    // Add the Idle Manager
    // ---------------------
    //IdleManager idleManager = new IdleManager();
    //this.addMouseListener(idleManager);
    //this.addKeyListener(idleManager);

    //--------------------
    // Load the last recently used
    // projects from file
    //--------------------

    lastRecentlyUsedProjects.load();
    ProjectManager.getInstance().notifyProjectChangeListeners();

    TimerTask dirtyProjectTask = new TimerTask() {

      public void run() {
        synchronized (this) {
          Project project = ProjectManager.getInstance().getCurrentProject();
          if (project == null) {
            setTitle(title);
          }
          else {
            String dirtyMark = "";
            if (project.isDirty()) dirtyMark = " * ";
            setTitle(title + " - " + project.getName() + dirtyMark); //+ " (" + project.getPath() + ")");
          }

        }
      }
    };

    java.util.Timer timer = new java.util.Timer();
    timer.scheduleAtFixedRate(dirtyProjectTask, 0, 1000);

    // ---------------------
    // Make the IDE visible
    // ---------------------
    setVisible(true);
  }

  public static void main(String args[]) {

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Throwable ex) {
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      }
      catch (Exception exception) {
        exception.printStackTrace();
      }
    }

    //SplashScreen ss = new SplashScreen();
    IDE.getInstance();
   // ss.close();

  }

  /**
   * Processes window events occurring on this window by dispatching them to any registered WindowListener objects.
   *
   * @param e the window event
  */
     protected void processWindowEvent(WindowEvent e) {
     if (e.getID() == WindowEvent.WINDOW_CLOSING) {
       ExitAction.getInstance().perform();
     }
     }

  public ConsolePanel getConsolePanel() {
    return this.consolePanel;
  }

  /**
   * Returns the panel in which the editors appear
   * @return EditorsPanel The value of editorsPanel
   */
  public EditorsPanel getEditorsPanel() {
    return this.editorsPanel;
  }

  /**
   * Returns the panel in which the contents of the project appear
   * @return ProjectPanel The value of projectPanel
   */
  public ProjectPanel getProjectPanel() {
    return this.projectPanel;
  }

  public ToolsPanel getToolsPanel() {
    return toolsPanel;
  }

  public ObjectViewerPanel getObjectViewerPanel() {
    return objectViewerPanel;
  }

  public JLabel getStatusBar() {
    return statusBar;
  }

  public LastRecentlyUsed getLastRecentlyUsedProjects() {
    return lastRecentlyUsedProjects;
  }

  /**
   * projectChanged
   */
  public void projectChanged() {

  }


  public GeneratedFilesPanel getGeneratedFilesPanel() {
    return generatedFilesPanel;
  }

}
