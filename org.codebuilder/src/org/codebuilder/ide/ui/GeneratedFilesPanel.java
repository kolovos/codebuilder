package org.codebuilder.ide.ui;

import javax.swing.*;
import org.codebuilder.ide.ui.swing.renderers.*;
import java.awt.BorderLayout;
import org.codebuilder.project.*;
import javax.swing.tree.TreeModel;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;
import org.codebuilder.ide.ui.actions.generated.RefreshGeneratedFilesAction;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.swing.*;
import java.util.*;

public class GeneratedFilesPanel
    extends JPanel
    implements ProjectBuildListener, ProjectChangeListener {

  JTree tree = ComponentFactory.createJTree();
  JToolBar toolbar = new JToolBar();

  public GeneratedFilesPanel() {
    init();
  }

  public void init() {

    this.setLayout(new BorderLayout());

    // ---------------------
    // Initialize the tree
    // ---------------------
    tree.setModel(new GeneratedFilesTreeModel());
    tree.addMouseListener(new GeneratedFilesTreeMouseListener(tree));
    tree.setCellRenderer(new DocumentTreeCellRenderer());

    // ---------------------
    // Arrange the toolbar
    // ---------------------

    toolbar.add(RefreshGeneratedFilesAction.getInstance());
    toolbar.setRollover(true);
    //toolbar.setFloatable(false);

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
    ProjectBuildManager.getInstance().addProjectBuildListener(this);

  }

  public ArrayList getContainedTemplates(DocumentGroup documentGroup){
    ArrayList contained = new ArrayList();
    ListIterator li = documentGroup.getDocuments().listIterator();
    while (li.hasNext()){
      Object o = li.next();
      if (o instanceof Template || o instanceof DocumentGroup){
        contained.add(o);
      }
    }
    return contained;
  }

  public void projectChanged() {
    JComponentUtilities.updateUI(tree);
  }

  public void buildCanceled() {
    JComponentUtilities.updateUI(tree);
  }

  public void buildCompleted() {
    JComponentUtilities.updateUI(tree);
  }

  public void buildStarted() {
  }

  public void progressUpdated() {
  }

  public void buildFailed() {
  }

  class GeneratedFilesTreeModel
      implements TreeModel {

    public GeneratedFilesTreeModel() {
    }

    public Object getRoot() {
      Project project = ProjectManager.getInstance().getCurrentProject();
      return project;
    }

    public int getChildCount(Object parent) {

      if (parent instanceof DocumentGroup) {
        return getContainedTemplates((DocumentGroup) parent).size();
      }

      if (parent instanceof Template) {
        return ( (Template) parent).getGeneratedFiles().size();
      }

      return 0;
    }

    public boolean isLeaf(Object node) {
      if (node instanceof GeneratedFile) {
        return true;
      }

      if (node instanceof DocumentGroup) {
        return getContainedTemplates((DocumentGroup) node).size() == 0;
      }

      if (node instanceof Template) {
        Template template = (Template) node;
        if (template.getGeneratedFiles().size() == 0) {
          return true;
        }
      }

      return false;
    }

    public void addTreeModelListener(TreeModelListener l) {
    }

    public void removeTreeModelListener(TreeModelListener l) {
    }

    public Object getChild(Object parent, int index) {
      if (parent instanceof DocumentGroup) {
        return getContainedTemplates(( (DocumentGroup) parent)).get(
            index);
      }

      if (parent instanceof Template) {
        return ( (Template) parent).getGeneratedFiles().get(index);
      }

      return null;
    }

    public int getIndexOfChild(Object parent, Object child) {
      if (parent instanceof DocumentGroup) {
        return getContainedTemplates(( (DocumentGroup) parent)).lastIndexOf(child);
      }

      if (parent instanceof Template) {
        return ( (Template) parent).getGeneratedFiles().lastIndexOf(child);
      }

      return 0;
    }

    public void valueForPathChanged(TreePath path, Object newValue) {
    }
  }

}
