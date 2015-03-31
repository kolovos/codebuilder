package org.codebuilder.ide.help;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.event.HyperlinkEvent.*;
import org.codebuilder.ide.help.actions.*;
import org.codebuilder.ide.ui.dialogs.util.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.swing.*;
import net.infonode.tabbedpanel.*;
import javax.swing.text.html.*;

public class HelpViewer
    extends JFrame
    implements HyperlinkListener {

  private static HelpViewer instance;
  private JEditorPane browser = new JEditorPane();
  private HelpTree helpTree = new HelpTree();

  private HelpViewer() {
    init();
  }

  public static HelpViewer getInstance() {
    if (instance == null) {
      synchronized (HelpViewer.class) {
        if (instance == null) {
          instance = new HelpViewer();
        }
      }
    }
    return instance;
  }

  public void init() {

    this.setTitle("CodeBuilder help");
    this.setIconImage(IconFactory.Help.getImage());
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);
    toolbar.add(new NavigateBackAction());
    toolbar.add(new NavigateForwardAction());

    TabbedPanel tabbedPanel = ComponentFactory.createTabbedPanel();
    tabbedPanel.addTab(ComponentFactory.createTab("Contents",
        IconFactory.HelpFile, helpTree));
    tabbedPanel.addTab(ComponentFactory.createTab("Search",
        IconFactory.Search, createLabel()));
    tabbedPanel.addTab(ComponentFactory.createTab("Bookmarks",
        IconFactory.Bookmark, createLabel()));

    browser.setContentType("text/html");
    browser.setEditable(false);
    browser.addHyperlinkListener(this);
    JSplitPane splitter = new JSplitPane();
    splitter.setBorder(null);
    splitter.setLeftComponent(tabbedPanel);
    splitter.setRightComponent(ComponentFactory.createJScrollPane(browser));
    this.getContentPane().setLayout(new BorderLayout());
    this.getContentPane().add(splitter, BorderLayout.CENTER);
    this.getContentPane().add(toolbar, BorderLayout.NORTH);

  }

  public void navigate(String url) {
    try {
      browser.setPage(url);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void navigate(File f) {
    try {
      navigate(f.toURL().toString());
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void showDialog() {
    DialogUtilities.positionInCenterScreen(this, 600, 800);
    this.setVisible(true);
  }

  /**
   * hyperlinkUpdate
   *
   * @param e HyperlinkEvent
   */
  public void hyperlinkUpdate(HyperlinkEvent e) {
    if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
        JEditorPane pane = (JEditorPane) e.getSource();
        if (e instanceof HTMLFrameHyperlinkEvent) {
            HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
            HTMLDocument doc = (HTMLDocument)pane.getDocument();
            doc.processHTMLFrameHyperlinkEvent(evt);
        } else {
            try {
                navigate(e.getURL().toString());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

  }

  public JLabel createLabel(){
    JLabel label = new JLabel();
    label.setHorizontalAlignment(label.CENTER);
    label.setText("Not yet implemented");
    return label;
  }
}
