package org.codebuilder.ide.ui.editors.intellisense;

import javax.swing.JEditorPane;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import javax.swing.text.BadLocationException;
import javax.swing.BorderFactory;
import java.util.ListIterator;
import java.util.ArrayList;
import org.jdom.input.SAXBuilder;
import java.io.*;
import org.jdom.*;
import java.awt.Color;

public class VelocityIntellisenseOld
    implements KeyListener {

  private JEditorPane editor = null;
  private ShallowVelocityParser parser = new ShallowVelocityParser();
  private static ArrayList builtInVariables = null;
  private static ArrayList builtInDirectives = null;
  private IntellisenseMenu variablesMenu = new IntellisenseMenu();
  private IntellisenseMenu directivesMenu = new IntellisenseMenu();

  public static ArrayList getBuiltInDirectives() {
    if (builtInDirectives == null) {
      SAXBuilder builder = new SAXBuilder();
      try {
        builtInDirectives = new ArrayList();
        Document document = builder.build(
            "Resources/Intellisense/BuiltInDirectives.xml");
        ListIterator li = document.getRootElement().getChildren().listIterator();
        while (li.hasNext()) {
          Element el = (Element) li.next();
          builtInDirectives.add(new IntellisenseMenuItem(el.getAttributeValue(
              "name"), el.getAttributeValue("value"),
                               el.getAttributeValue("description"),
                                 Integer.parseInt(el.getAttributeValue(
              "moveback"))));
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
        return new ArrayList();
      }
    }
    return builtInDirectives;
  }

  public static ArrayList getBuiltInVariables() {
    if (builtInVariables == null) {
      SAXBuilder builder = new SAXBuilder();
      try {
        builtInVariables = new ArrayList();
        Document document = builder.build(
            "Resources/Intellisense/BuiltInVariables.xml");
        ListIterator li = document.getRootElement().getChildren().listIterator();
        while (li.hasNext()) {
          Element el = (Element) li.next();
          builtInVariables.add(new IntellisenseMenuItem(el.getAttributeValue(
              "name"), el.getAttributeValue("name"),
                               el.getAttributeValue("description"),
                                  0));
        }
      }
      catch (Exception ex) {
        ex.printStackTrace();
        return new ArrayList();
      }
    }
    return builtInVariables;
  }

  public VelocityIntellisenseOld(JEditorPane editor) {
    this.editor = editor;
    editor.addKeyListener(this);
  }

  /**
   * keyPressed
   *
   * @param e KeyEvent
   */
  public void keyPressed(KeyEvent e) {
  }

  /**
   * keyReleased
   *
   * @param e KeyEvent
   */
  public void keyReleased(KeyEvent e) {
  }

  /**
   * keyTyped
   *
   * @param e KeyEvent
   */
  public void keyTyped(KeyEvent e) {
    if (e.getKeyChar() == '#') {
      parser.parse(editor.getText());
      ListIterator li = getBuiltInDirectives().listIterator();
      directivesMenu = new IntellisenseMenu();

      // ---------------------
      // First load the built in directives
      // menu
      // ---------------------

      while (li.hasNext()) {
        IntellisenseMenuItem item = (IntellisenseMenuItem) li.next();
        item.setEditor(editor);
        directivesMenu.add(item);
      }

      // ---------------------
      // Show the menu
      // ---------------------

      directivesMenu.showMenu(editor);

    }
    if (e.getKeyChar() == '$') {
      parser.parse(editor.getText());
      ListIterator li = getBuiltInVariables().listIterator();
      variablesMenu = new IntellisenseMenu(true);

      // ---------------------
      // First load the built in variables
      // menu
      // ---------------------

      while (li.hasNext()) {
        IntellisenseMenuItem item = (IntellisenseMenuItem) li.next();
        item.setEditor(editor);
        variablesMenu.add(item);
      }

      // ---------------------
      // Then add the local variables
      // ---------------------

      li = parser.getVariables().listIterator();
      while (li.hasNext()){
        String variable = (String) li.next();
        IntellisenseMenuItem item = new IntellisenseMenuItem(variable,variable,"local variable",0);
        variablesMenu.add(item);
      }

      // ---------------------
      // Show the menu
      // ---------------------

      variablesMenu.showMenu(editor);


    }

  }

}
