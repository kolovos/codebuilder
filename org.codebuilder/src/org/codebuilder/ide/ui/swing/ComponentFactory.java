package org.codebuilder.ide.ui.swing;

import javax.swing.*;
import org.codebuilder.ide.ui.widgets.*;
import net.infonode.tabbedpanel.*;
import net.infonode.util.*;
import net.infonode.tabbedpanel.theme.*;
import net.infonode.tabbedpanel.titledtab.*;
import net.infonode.gui.componentpainter.*;
import net.infonode.gui.colorprovider.*;
import java.awt.*;
import javax.swing.tree.*;

public class ComponentFactory {

  private static ComponentPainter backgroundPainter = new
      SolidColorComponentPainter(
      new FixedColorProvider(new Color(128, 128, 255)));

  public ComponentFactory() {
  }

  public static JTextPane createJTextPane() {
    JTextPane textPane = new JTextPane();
    //textPane.setBorder(new EtchedLineBorder(true,true,true,true));
    return textPane;
  }

  public static JScrollPane createJScrollPane(JComponent c) {
    JScrollPane scroll = new JScrollPane(c);
    return scroll;
  }

  public static JTree createJTree() {
    JTree tree = new JTree();
    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    //JTree tree = new JTree();
    return tree;
  }

  public static TabbedPanel createTabbedPanel() {
    return createTabbedPanel(null);
  }

  public static TabbedPanel createTabbedPanel(Direction direction) {
    TabbedPanel tabs = new TabbedPanel();
    tabs.getProperties().addSuperObject(new ShapedGradientTheme().
                                        getTabbedPanelProperties());
    tabs.setBorder(null);
    if (direction != null) {
      tabs.getProperties().setTabAreaOrientation(direction);
    }
    TabbedPanelProperties tabbedPanelHoverProperties = new
        TabbedPanelProperties();
    tabbedPanelHoverProperties.getContentPanelProperties().
        getShapedPanelProperties().setComponentPainter(
        backgroundPainter);
    //tabs.getProperties().addSuperObject(tabbedPanelHoverProperties);

    return tabs;
  }

  public static TitledTab createTab(String name, Icon icon,
                                    JComponent component) {
    TitledTab tab = new TitledTab(name, icon, component, null);
    tab.getProperties().addSuperObject(new ShapedGradientTheme().
                                       getTitledTabProperties());
    TitledTabProperties titledTabHoverProperties = new TitledTabProperties();
    titledTabHoverProperties.getHighlightedProperties().
        getShapedPanelProperties().setComponentPainter(
        backgroundPainter);
    //tab.getProperties().addSuperObject(titledTabHoverProperties);
    return tab;
  }

  public static JFrame createJFrame(String title){
    JFrame frame = new JFrame();
    frame.setTitle(title);
    frame.getContentPane().setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(100,100,500,500);
    return frame;
  }
}
