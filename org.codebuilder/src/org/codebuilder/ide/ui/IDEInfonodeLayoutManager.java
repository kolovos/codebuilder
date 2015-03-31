package org.codebuilder.ide.ui;

//import net.infonode.tabbedpanel.*;
//import net.infonode.gui.border.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.codebuilder.ide.plugins.*;
import org.codebuilder.ide.ui.actions.*;
import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.ide.ui.actions.edit.*;
import org.codebuilder.ide.ui.actions.help.*;
import org.codebuilder.ide.ui.actions.project.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.widgets.*;
import net.infonode.docking.*;
import net.infonode.docking.properties.*;
import net.infonode.docking.theme.*;
import net.infonode.docking.util.*;
import net.infonode.util.*;
import org.codebuilder.ide.ui.actions.tools.*;
import javax.swing.border.*;

public class IDEInfonodeLayoutManager {

  RootWindow rootWindow;
  ViewMap viewMap;
  RootWindowProperties rootWindowProperties = new RootWindowProperties();

  //JSplitPane messages2others;
  //JSplitPane properties2others;
  //JSplitPane project2editors;
  IDE ide;
  //JCheckBox jCheckBox1 = new JCheckBox();
  //JButton jButton1 = new JButton();

  public IDEInfonodeLayoutManager() {
    //try {
    //  jbInit();
    //}
    //catch (Exception e) {
    //  e.printStackTrace();
    //}

  }

  public void arrangePanels(IDE ide) {

    // ---------------------
    // Set the reference for
    // the IDE
    // ---------------------

    this.ide = ide;

    // ---------------------
    // Make the tooltips last for ever
    // ---------------------

    ToolTipManager tooltipManager = ToolTipManager.sharedInstance();
    tooltipManager.setDismissDelay(100000);
    /*
    MixedViewHandler handler = new MixedViewHandler(viewMap, new ViewSerializer() {
      public void writeView(View view, ObjectOutputStream out) throws
          IOException {
        //out.writeInt(((DynamicView) view).getId());
      }

      public View readView(ObjectInputStream in) throws IOException {
        return null;
      }
    });*/

    viewMap = new ViewMap();

    View projectView = new View("Project", IconFactory.Project,
                                  ide.getProjectPanel());
    View propertiesView = new View("Properties", IconFactory.PropertyEditor, new PropertiesEditor());
    View generatedView = new View("Generated", IconFactory.Generated,
                                  ide.getGeneratedFilesPanel());
    View consoleView = new View("Console", IconFactory.Logs,
                                ide.getConsolePanel());
    View editorsView = new View("Editors", IconFactory.About,
                                ide.getEditorsPanel());
    View toolsView = new View("Tools", IconFactory.About, ide.getToolsPanel());
    View objectViewerView = new View("Object Viewer", IconFactory.getIconFor("View"), ide.getObjectViewerPanel());
    View dynamicFeaturesView = new View("Features", IconFactory.Method, new JPanel());

    viewMap.addView(0, projectView);
    viewMap.addView(1, generatedView);
    viewMap.addView(2, consoleView);
    viewMap.addView(3, editorsView);
    viewMap.addView(4, toolsView);
    viewMap.addView(5, propertiesView);
    viewMap.addView(6, objectViewerView);

    //rootWindow.setWindow(new SplitWindow(templatesView,0.3f,new SplitWindow(false,0.7f,editorsView, consoleView),new SplitWindow(true,generatedView,toolsView)));

    //SplitWindow generated2tools = new SplitWindow(false, generatedView, toolsView);

    editorsView.getViewProperties().setAlwaysShowTitle(false);
    rootWindow = DockingUtil.createRootWindow(viewMap, true);
    
    rootWindow.setWindow(new SplitWindow(false, 0.7f,
                                         new SplitWindow(true, 0.2f,
        new SplitWindow(false,0.5f, projectView, propertiesView),
        //projectView,
        new SplitWindow(true, 0.75f, editorsView,
                        new SplitWindow(false, 0.5f, generatedView, new TabWindow(new DockingWindow[]{toolsView,objectViewerView})))),
                                         consoleView));
    toolsView.restoreFocus();

    rootWindow.getWindowBar(Direction.DOWN).setEnabled(true);
    rootWindow.getWindowBar(Direction.LEFT).setEnabled(true);
    rootWindow.getWindowBar(Direction.RIGHT).setEnabled(true);

   rootWindowProperties.addSuperObject( (new ShapedGradientDockingTheme()).
                                        getRootWindowProperties());


   rootWindowProperties.getDockingWindowProperties().setCloseEnabled(false);

   //rootWindowProperties.getTabWindowProperties().getCloseButtonProperties().setVisible(false);
   //rootWindowProperties.getTabWindowProperties().getMinimizeButtonProperties().setVisible(false);
   //rootWindowProperties.getTabWindowProperties().getMaximizeButtonProperties().setVisible(false);

    rootWindow.getRootWindowProperties().addSuperObject(rootWindowProperties);

    JPanel toolbars = new JPanel(new BorderLayout());
    ide.pluginstoolbar.setRollover(true);
    //ide.pluginstoolbar.setFloatable(false);
    ide.pluginstoolbar.add(new JLabel("Plug-ins:"));
    toolbars.add(ide.toolbar, BorderLayout.NORTH);
    ide.getContentPane().add(toolbars, BorderLayout.NORTH);

    // ---------------------
    // Arrange the positions of
    // the splitters
    // ---------------------


    //--------------------
    // Set the style of the status bar
    //--------------------

    JLabel statusBar = ide.getStatusBar();
    statusBar.setText("Ready...");
    statusBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.
        createLoweredBevelBorder(), BorderFactory.createEmptyBorder(2, 2, 2, 2)));

    /// ---------------------
    // Add a resize listener so that
    // the proportions can be sustained
    // ---------------------

    ide.addComponentListener(new IDEWindowListener());

    ide.getContentPane().add(rootWindow, BorderLayout.CENTER);
    //ide.getStatusBar().setBorder(new EtchedBorder());
    //ide.getContentPane().add(ide.getStatusBar(), BorderLayout.SOUTH);

  }

  public void populateMenus(IDE ide) {

    // ---------------------
    // Create the menus and the toolbars
    // ---------------------

    JMenuBar menubar = new JMenuBar();

    //menubar.setBorderPainted(true);
    //menubar.setBorder(BorderFactory.createCompoundBorder(menubar.getBorder(),
    //    BorderFactory.createEmptyBorder(2, 2, 2, 2)));

    JMenu filemenu = new JCustomizedMenu("File");
    filemenu.add(NewProjectAction.getInstance());
    filemenu.add(BrowseOpenProjectAction.getInstance());
    filemenu.add(SaveProjectAction.getInstance());
    filemenu.addSeparator();
    filemenu.add(SaveDocumentAction.getInstance());
    filemenu.add(SaveAllAction.getInstance());
    filemenu.addSeparator();
    // filemenu.add(CloseDocumentAction.getInstance());
    filemenu.add(CloseProjectAction.getInstance());
    filemenu.add(new LastRecentlyUsedProjectsMenu());
    filemenu.addSeparator();
    filemenu.add(ExitAction.getInstance());

    JMenu editmenu = new JCustomizedMenu("Edit");
    editmenu.add(UndoAction.getInstance());
    editmenu.add(RedoAction.getInstance());
    editmenu.addSeparator();
    editmenu.add(SelectAllAction.getInstance());
    editmenu.addSeparator();
    editmenu.add(CutAction.getInstance());
    editmenu.add(CopyAction.getInstance());
    editmenu.add(PasteAction.getInstance());
    editmenu.add(DeleteAction.getInstance());




    JMenu projectMenu = new JCustomizedMenu("Project");
    projectMenu.add(NewDocumentAction.getInstance());
    projectMenu.add(AddDocumentAction.getInstance());
    projectMenu.addSeparator();
    projectMenu.add(NewDocumentGroupAction.getInstance());
    projectMenu.addSeparator();
    projectMenu.add(RemoveDocumentAction.getInstance());

    JMenu buildMenu = new JCustomizedMenu("Build");
    buildMenu.add(BuildProjectAction.getInstance());
    buildMenu.add(CancelProjectBuildAction.getInstance());

    JMenu viewmenu = new JCustomizedMenu("View");

    JMenu toolsMenu = new JCustomizedMenu("Tools");
    toolsMenu.add(ConfigureToolsAction.getInstance());
    toolsMenu.add(ConfigurePluginsAction.getInstance());
    toolsMenu.addSeparator();
    toolsMenu.add(ConfigureDocumentsAction.getInstance());
    toolsMenu.add(ConfigureEditorsAction.getInstance());

    JMenu pluginsmenu = new JCustomizedMenu("Plug-ins");
    PluginManager pluginManager = new PluginManager();
    pluginManager.populateUI(pluginsmenu, ide.pluginstoolbar);
    pluginsmenu.addSeparator();
    pluginsmenu.add(ConfigurePluginsAction.getInstance());

    JMenu windowmenu = new JCustomizedMenu("Window");

    JMenu helpmenu = new JCustomizedMenu("Help");
    helpmenu.add(HelpAction.getInstance());
    helpmenu.add(AboutAction.getInstance());

    menubar.add(filemenu);
    menubar.add(editmenu);
    menubar.add(viewmenu);
    menubar.add(projectMenu);
    menubar.add(buildMenu);
    menubar.add(pluginsmenu);
    menubar.add(toolsMenu);
    menubar.add(windowmenu);
    menubar.add(helpmenu);

    ide.setJMenuBar(menubar);
    //menubar.setVisible(true);
    //filemenu.setVisible(true);
  }

  public void populateToolbars(IDE ide) {
    JToolBar toolbar = ide.toolbar;

    toolbar.setRollover(true);
    //toolbar.setFloatable(false);

    toolbar.add(NewProjectAction.getInstance());
    toolbar.add(BrowseOpenProjectAction.getInstance());
    toolbar.add(SaveProjectAction.getInstance());
    toolbar.add(CloseProjectAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(SaveDocumentAction.getInstance());
    toolbar.add(SaveAllAction.getInstance());
    // toolbar.add(CloseDocumentAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(UndoAction.getInstance());
    toolbar.add(RedoAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(CutAction.getInstance());
    toolbar.add(CopyAction.getInstance());
    toolbar.add(PasteAction.getInstance());
    toolbar.add(DeleteAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(BuildProjectAction.getInstance());
    toolbar.add(CancelProjectBuildAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(HelpAction.getInstance());
    //toolbar.add(DebugAction.getInstance());

  }

  class IDEWindowListener
      implements ComponentListener {
    /**
     * componentHidden
     *
     * @param e ComponentEvent
     */
    public void componentHidden(ComponentEvent e) {
    }

    /**
     * componentMoved
     *
     * @param e ComponentEvent
     */
    public void componentMoved(ComponentEvent e) {
    }

    /**
     * componentResized
     *
     * @param e ComponentEvent
     */
    public void componentResized(ComponentEvent e) {
      //project2editors.setDividerLocation(ide.projectPanel.getWidth());
      //messages2others.setDividerLocation(messages2others.getHeight() -
      //                                   ide.logsPanel.getHeight());
    }

    /**
     * componentShown
     *
     * @param e ComponentEvent
     */
    public void componentShown(ComponentEvent e) {
    }

  }

}
