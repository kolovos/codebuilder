package org.codebuilder.ide.ui.editors;

import java.awt.*;
import javax.swing.*;
import org.codebuilder.ide.ui.editors.objectbrowser.*;
import org.codebuilder.ide.ui.swing.*;
import org.codebuilder.project.*;
import net.infonode.tabbedpanel.*;
import net.infonode.util.*;
import org.codebuilder.ide.feedback.*;

public class ModelEditor
    extends IEditor {

  ObjectBrowser objectBrowser = null;
  TextEditor textEditor = null;
  TabbedPanel tabs = ComponentFactory.createTabbedPanel(Direction.DOWN);

  public ModelEditor() {

  }

  /**
   * copy
   */
  public void copy() {
    textEditor.copy();
  }

  /**
   * cut
   */
  public void cut() {
    textEditor.cut();
  }

  /**
   * delete
   */
  public void delete() {
    textEditor.delete();
  }

  /**
   * find
   */
  public void find() {
    textEditor.find();
  }

  /**
   * getCaption
   *
   * @return String
   */
  public String getCaption() {
    return textEditor.getCaption();
  }

  /**
   * getIcon
   *
   * @return ImageIcon
   */
  public Icon getIcon() {
    return document.getIcon();
  }

  /**
   * goTo
   *
   * @param line int
   * @param column int
   */
  public void goTo(int line, int column) {
  }

  /**
   * isDirty
   *
   * @return boolean
   */
  public boolean isDirty() {
    return textEditor.isDirty();
  }

  /**
   * load
   *
   * @param filename String
   * @return boolean
   */
  public boolean load(Document document) {
    this.document = document;
    textEditor = new TextEditor();
    //textEditor.load(document);
    objectBrowser = new ObjectBrowser( (Model) document);
    tabs.addTab(ComponentFactory.createTab("Source", null, textEditor));
    tabs.addTab(ComponentFactory.createTab("Content", null, objectBrowser));
    this.setLayout(new BorderLayout());
    //this.add(tabs, BorderLayout.CENTER);
    this.add(objectBrowser, BorderLayout.CENTER);
    this.document = document;
    boolean retval = textEditor.load(document);
    objectBrowser.load(document);
    return retval;
  }

  /**
   * paste
   */
  public void paste() {
    textEditor.paste();
  }

  /**
   * redo
   */
  public void redo() {
    textEditor.redo();
  }

  /**
   * replace
   */
  public void replace() {
    textEditor.replace();
  }

  /**
   * save
   *
   * @return boolean
   */
  public boolean save() {
    //boolean result = textEditor.save();
    try{
      objectBrowser.load(document);
    }catch(Exception ex){
      FeedbackManager.getInstance().reportException(ex);
    }
    //return result;
    return true;
  }

  /**
   * selectAll
   */
  public void selectAll() {
    textEditor.selectAll();
  }

  /**
   * undo
   */
  public void undo() {
    textEditor.undo();
  }

  /**
   * accept
   *
   * @param document Document
   * @return boolean
   */
  public boolean accept(Document document) {
    return document instanceof Model;
  }

  /**
   * getName
   *
   * @return String
   */
  public String getName() {
    return "Model editor";
  }

}
