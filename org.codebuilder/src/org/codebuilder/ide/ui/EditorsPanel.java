package org.codebuilder.ide.ui;
/*
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.util.ListIterator;
import java.util.TimerTask;
import java.util.Timer;
import org.codebuilder.ide.ui.widgets.*;
import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.ide.ui.editors.*;

public class EditorsPanel
    extends IDEPanel {

  private ArrayList editors = new ArrayList();
  private JTabbedPaneWithCloseIcons tabs = new JTabbedPaneWithCloseIcons();

  public EditorsPanel() {
    super();

    // ---------------------
    // Add the tabbed pane
    // ---------------------

    tabs.setTabPlacement(JTabbedPane.TOP);
    tabs.setTabCloseAction(CloseDocumentAction.getInstance());
    this.getContentPane().add(tabs, BorderLayout.CENTER);

    // ---------------------
    // Add a timer to check for the
    // dirty editors
    // ---------------------

    TimerTask dirtyEditorsTask = new TimerTask() {

      public void run() {
        synchronized (editors) {
          boolean update = false;
          for (int i = 0; i < editors.size(); i++) {
            IEditor editor = (IEditor) editors.get(i);
            if (tabs.getTitleAt(i).compareTo(getEditorTitle(editor)) != 0) {
              tabs.setTitleAt(i, getEditorTitle(editor));
              update = true;
            }
            if (update) {
              tabs.updateUI();
            }
          }
        }
      }
    };

    Timer timer = new Timer();
    timer.scheduleAtFixedRate(dirtyEditorsTask, 0, 1000);
  }

  public String getEditorTitle(IEditor editor) {
    String title = editor.getCaption();
    if (editor.isDirty()) {
      title += " *";
    }
    return title;
  }

  public IEditor getCurrentEditor() {
    return (IEditor) tabs.getSelectedComponent();
  }

  public void addEditor(IEditor editor) {
    editors.add(editor);
    tabs.addTab(editor.getCaption(), editor, editor.getIcon());
  }

  public void removeEditor(IEditor editor) {
    editors.remove(editor);
    tabs.remove(editor);
  }

  public IEditor getEditor(Object edited) {
    ListIterator li = editors.listIterator();
    while (li.hasNext()) {
      IEditor editor = (IEditor) li.next();
      if (editor.getDocument() == edited) {
        return editor;
      }
    }
    return null;
  }

  public void activateEditor(IEditor editor) {
    for (int i = 0, n = editors.size(); i < n; i++) {
      if (editors.get(i).equals(editor)) {
        tabs.setSelectedComponent(editor);
        editor.setVisible(true);
        break;
      }
      else {
        editor.setVisible(false);
      }
    }
    //tabs.repaint();
  }

  public IEditor getEditorFor(Object object) {
    ListIterator li = editors.listIterator();
    while (li.hasNext()) {
      IEditor editor = (IEditor) li.next();
      if (editor.getDocument().equals(object)) {
        return editor;
      }
    }
    return null;
  }

  public void refreshEditorFor(Object object) {
    IEditor editor = getEditorFor(object);
    if (editor == null) {
      return;
    }
    for (int i = 0, n = editors.size(); i < n; i++) {
      if (editors.get(i) == editor) {
        tabs.setTitleAt(i, editor.getCaption());
        tabs.updateUI();
        return;
      }
    }
  }

  public ArrayList getEditors() {
    return editors;
  }

}

*/

import java.util.*;
import java.util.Timer;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.ide.ui.editors.*;
import org.codebuilder.ide.ui.swing.*;
import net.infonode.tabbedpanel.*;
import net.infonode.tabbedpanel.theme.*;
import net.infonode.tabbedpanel.titledtab.*;
import javax.swing.plaf.metal.*;
import net.infonode.gui.*;
import org.codebuilder.ide.projectmanagement.*;

public class EditorsPanel
    extends JPanel implements ProjectChangeListener{

  private ArrayList editors = new ArrayList();
  //private JTabbedPaneWithCloseIcons tabs = new JTabbedPaneWithCloseIcons();
  TabbedPanel tabs = ComponentFactory.createTabbedPanel();

  public EditorsPanel() {
    //super();
    ProjectManager.getInstance().addProjectChangeListener(this);

    // ---------------------
    // Add the tabbed pane
    // ---------------------

    //tabs.setOpaque(false);

    //tabs.setTabPlacement(JTabbedPane.TOP);
    //tabs.setTabCloseAction(CloseDocumentAction.getInstance());
    this.setLayout(new BorderLayout());
    this.add(tabs, BorderLayout.CENTER);

    // ---------------------
    // Add a timer to check for the
    // dirty editors
    // ---------------------


    TimerTask dirtyEditorsTask = new TimerTask() {

      public void run() {
        synchronized (editors) {
          boolean update = false;
          for (int i = 0; i < editors.size(); i++) {
            IEditor editor = (IEditor) editors.get(i);
            if (((TitledTab)tabs.getTabAt(i)).getText().compareTo(getEditorTitle(editor)) != 0) {
              //tabs.setTitleAt(i, getEditorTitle(editor));
              ((TitledTab)tabs.getTabAt(i)).setText(getEditorTitle(editor));
              update = true;
            }
            if (update) {
              tabs.updateUI();
            }
          }
        }
      }
    };

    Timer timer = new Timer();
    timer.scheduleAtFixedRate(dirtyEditorsTask, 0, 1000);

  }


  private JButton createXButton() {
    JButton closeButton = new JButton(CloseDocumentAction.getInstance());
    //closeButton.setUI(new FlatIconButtonUI());
    closeButton.setText("X");
    closeButton.setIcon(null);
    closeButton.setOpaque(false);
    closeButton.setMargin(null);
    closeButton.setFont(closeButton.getFont().deriveFont(Font.BOLD).deriveFont((float) 10));
    closeButton.setBorder(new EmptyBorder(0, 0, 0, 0));
    return closeButton;
  }


  private JButton createCloseTabButton(final TitledTab tab) {
    JButton closeButton = createXButton();

    return closeButton;
  }

  public String getEditorTitle(IEditor editor) {
    String title = editor.getCaption();
    if (editor.isDirty()) {
      title += " *";
    }
    return title;
  }

  public IEditor getCurrentEditor() {
    if (tabs.getSelectedTab() == null) return null;
    return (IEditor) tabs.getSelectedTab().getContentComponent();
  }

  public void addEditor(IEditor editor) {
    editors.add(editor);
    tabs.addTab(createTab(editor.getCaption(), editor.getIcon(), editor));
  }

  public TitledTab createTab(String name, Icon icon, JComponent component){
    TitledTab tab =  ComponentFactory.createTab(name, icon, component); //new TitledTab(name,icon,component,null);
    //tab.getProperties().addSuperObject(new ShapedGradientTheme().getTitledTabProperties());
    tab.setHighlightedStateTitleComponent(createCloseTabButton(tab));
    return tab;
  }

  public void removeEditor(IEditor editor) {
    //tabs.remove(editor);
    for (int i = 0, n = editors.size(); i < n; i++) {
      if (editors.get(i).equals(editor)) {
        //tabs.setSelectedComponent(editor);
        editors.remove(editor);
        tabs.removeTab(tabs.getTabAt(i));
        break;
      }
    }
  }

  public IEditor getEditor(Object edited) {
    ListIterator li = editors.listIterator();
    while (li.hasNext()) {
      IEditor editor = (IEditor) li.next();
      if (editor.getDocument() == edited) {
        return editor;
      }
    }
    return null;
  }

  public void activateEditor(IEditor editor) {
    for (int i = 0, n = editors.size(); i < n; i++) {
      if (editors.get(i).equals(editor)) {
        //tabs.setSelectedComponent(editor);
        tabs.setSelectedTab(tabs.getTabAt(i));
        editor.setVisible(true);
        break;
      }
      else {
        editor.setVisible(false);
      }
    }
    //tabs.repaint();
  }

  public IEditor getEditorFor(Object object) {
    ListIterator li = editors.listIterator();
    while (li.hasNext()) {
      IEditor editor = (IEditor) li.next();
      if (editor.getDocument().equals(object)) {
        return editor;
      }
    }
    return null;
  }

  public void refreshEditorFor(Object object) {
    IEditor editor = getEditorFor(object);
    if (editor == null) {
      return;
    }
    for (int i = 0, n = editors.size(); i < n; i++) {
      if (editors.get(i) == editor) {
        //tabs.setTitleAt(i, editor.getCaption());
        ((TitledTab)tabs.getTabAt(i)).setText(editor.getCaption());
        ((TitledTab)tabs.getTabAt(i)).setIcon(editor.getIcon());
        tabs.updateUI();
        return;
      }
    }
  }

  public void refreshEditors() {
    for (int i = 0, n = editors.size(); i < n; i++) {
        ((TitledTab)tabs.getTabAt(i)).setText(((IEditor)editors.get(i)).getCaption());
        ((TitledTab)tabs.getTabAt(i)).setIcon(((IEditor)editors.get(i)).getIcon());
        tabs.updateUI();
    }
  }

  public ArrayList getEditors() {
    return editors;
  }

  /**
   * projectChanged
   */
  public void projectChanged() {
    refreshEditors();
  }

}

