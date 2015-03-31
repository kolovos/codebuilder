package org.codebuilder.ide.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.ide.ui.swing.*;
import org.codebuilder.ide.ui.swing.renderers.*;
import org.codebuilder.project.*;
import org.codebuilder.project.reader.*;
import org.codebuilder.project.writer.*;

public class ProjectPanel
    extends JPanel
    implements ProjectChangeListener {

  JTree tree = ComponentFactory.createJTree();
  JToolBar toolbar = new JToolBar();

  public ProjectPanel() {
    init();
  }

  public Document getSelectedDocument() {
    TreePath selectionPath = tree.getSelectionPath();
    if (selectionPath == null) {
      return null;
    }
    Object selected = selectionPath.getLastPathComponent();
    return (Document) selected;
  }

  public void init() {

    this.setLayout(new BorderLayout());

    // ---------------------
    // Initialize the toolbar
    // ---------------------
    toolbar.setRollover(true);
    //toolbar.setFloatable(false);
    toolbar.add(NewDocumentAction.getInstance());
    toolbar.add(AddDocumentAction.getInstance());
    toolbar.addSeparator();
    toolbar.add(NewDocumentGroupAction.getInstance());

    // ---------------------
    // Initialize the tree
    // ---------------------
    tree.setModel(new ProjectTreeModel());
    tree.getSelectionModel().addTreeSelectionListener(DocumentSelectionManager.getInstance());
    tree.addMouseListener(new ProjectTreeMouseListener(tree));
    tree.setCellRenderer(new DocumentTreeCellRenderer());

    // ---------------------
    // Add the components to
    // the panel
    // ---------------------

    this.add(toolbar, BorderLayout.NORTH);
    this.add(ComponentFactory.createJScrollPane(tree), BorderLayout.CENTER);

    // ---------------------
    // Listen for changes to
    // the project
    // ---------------------

    ProjectManager.getInstance().addProjectChangeListener(this);
  }

  /**
   * projectChanged
   *
   */
  public void projectChanged() {
    JComponentUtilities.updateUI(tree);
  }

  class ProjectTreeModel
      implements TreeModel {

    Project project;

    public ProjectTreeModel() {

    }

    /**
     * getRoot
     *
     * @return Object
     */
    public Object getRoot() {
      project = ProjectManager.getInstance().getCurrentProject();
      return project;
    }

    /**
     * getChildCount
     *
     * @param parent Object
     * @return int
     */
    public int getChildCount(Object parent) {
      if (parent instanceof DocumentGroup){
        return ((DocumentGroup) parent).getDocuments().size();
      }
      else {
        return 0;
      }
    }

    /**
     * isLeaf
     *
     * @param node Object
     * @return boolean
     */
    public boolean isLeaf(Object node) {
      if (node instanceof DocumentGroup) {
        if  (((DocumentGroup) node).getDocuments().size() == 0){
          return true;
        }
        else {
          return false;
        }
      }
      else {
        return true;
      }
    }

    /**
     * addTreeModelListener
     *
     * @param l TreeModelListener
     */
    public void addTreeModelListener(TreeModelListener l) {
    }

    /**
     * removeTreeModelListener
     *
     * @param l TreeModelListener
     */
    public void removeTreeModelListener(TreeModelListener l) {
    }

    /**
     * getChild
     *
     * @param parent Object
     * @param index int
     * @return Object
     */
    public Object getChild(Object parent, int index) {
      if (parent instanceof DocumentGroup) {
        return ((DocumentGroup) parent).getDocument(index);
      }
      else {
        return null;
      }

    }

    /**
     * getIndexOfChild
     *
     * @param parent Object
     * @param child Object
     * @return int
     */
    public int getIndexOfChild(Object parent, Object child) {
      return 0;
    }

    /**
     * valueForPathChanged
     *
     * @param path TreePath
     * @param newValue Object
     */
    public void valueForPathChanged(TreePath path, Object newValue) {
    }
  }

  public static void main(String[] args) throws ReadException, WriteException {

    JFrame frame = ComponentFactory.createJFrame("ProjectPanel test");
    frame.getContentPane().add(new ProjectPanel(), BorderLayout.CENTER);
    Project project = null;
    ProjectReader reader = new ProjectReader();
    project = reader.read("c:/test.txt");
    ProjectManager.getInstance().setCurrentProject(project);
    ProjectWriter writer = new ProjectWriter();
    writer.write("c:/test1.txt", project);
    frame.setVisible(true);
  }

}
