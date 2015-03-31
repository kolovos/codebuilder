package org.codebuilder.ide.ui.editors.xmleditor;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import org.codebuilder.ide.ui.editors.*;
import org.codebuilder.ide.ui.editors.xmleditor.actions.*;
import org.codebuilder.ide.ui.editors.xmleditor.util.*;
import org.codebuilder.ide.ui.editors.xmleditor.xml.*;
import org.codebuilder.models.xml.*;
import org.jdom.*;
import org.jdom.input.*;
import org.codebuilder.ide.feedback.FeedbackManager;

public class XmlEditor
    extends IEditor
    implements DocumentTreeSelectionListener{

  protected DocumentTree documentTree;
  protected AttributeEditor attributeEditor;
  protected JToolBar elementToolbar = new JToolBar();
  protected JToolBar attributeToolbar = new JToolBar();
  protected DocumentTreeModel treeModel;
  protected org.jdom.Document xmlDocument;

  public XmlEditor() {
    init();
  }

  //public XmlEditor(XMLModel model) {
  //  init();
  //  this.document = (org.codebuilder.project.Document) model;
  //  this.load(model);
  //}

  public void init() {
    documentTree = new DocumentTree();
    attributeEditor = new AttributeEditor();
    documentTree.addDocumentTreeSelectionListener(this);
    JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                         documentTree, attributeEditor);
    this.setLayout(new BorderLayout());
    splitter.setBorder(new EmptyBorder(2,2,2,2));
    splitter.setDividerLocation(200);
    this.setBorder(null);
    this.add(splitter, BorderLayout.CENTER);
    //this.add(elementToolbar, BorderLayout.NORTH);
    //this.add(attributeToolbar, BorderLayout.EAST);

    elementToolbar.setRollover(true);
    elementToolbar.setFloatable(false);

    attributeToolbar.setOrientation(JToolBar.VERTICAL);
    attributeToolbar.setRollover(true);
    attributeToolbar.setFloatable(false);

    // ---------------------
    // Populate the elementToolbar
    // ---------------------
    elementToolbar.add(new AddElementAsChildAction(this));
    elementToolbar.add(new AddElementAsNextAction(this));
    elementToolbar.add(new DeleteElementAction(this));

    // ---------------------
    // Populate the atteibuteToolbar
    // ---------------------
    attributeToolbar.add(new AddAttributeAction(this));
    attributeToolbar.add(new DeleteAttributeAction(this));
    attributeToolbar.add(new MoveAttributeUpAction(this));
    attributeToolbar.add(new MoveAttributeDownAction(this));
  }

  public DocumentTree getDocumentTree() {
    return documentTree;
  }

  public AttributeEditor getAttributeEditor() {
    return attributeEditor;
  }

  public boolean load(org.codebuilder.project.Document document){
    try{
      this.document = document;
      SAXBuilder builder = new SAXBuilder();
      builder.setFactory(new PrettyFactory());
      xmlDocument = builder.build(document.getPath());
      documentTree.setDocument(xmlDocument);
      this.update();
    }
    catch(Exception ex){
      FeedbackManager.getInstance().reportException(ex);
    }
    return true;
  }

  /**
   * valueChanged
   *
   * @param e Element
   */
  public void valueChanged(Element e) {
    attributeEditor.setAttributes(e.getAttributes());
  }

  public void update(){
     JComponentUtilities.updateUI(this, true);
  }

  /**
   * copy
   */
  public void copy() {
  }

  /**
   * cut
   */
  public void cut() {
  }

  /**
   * delete
   */
  public void delete() {
  }

  /**
   * find
   */
  public void find() {
  }

  /**
   * getCaption
   *
   * @return String
   */
  public String getCaption() {
    return this.getDocument().getName();
  }

  /**
   * getIcon
   *
   * @return ImageIcon
   */
  public Icon getIcon() {
    return this.getDocument().getIcon();
  }

  /**
   * goTo
   *
   * @param int0 int
   * @param int1 int
   */
  public void goTo(int int0, int int1) {
  }

  /**
   * isDirty
   *
   * @return boolean
   */
  public boolean isDirty() {
    return false;
  }

  /**
   * paste
   */
  public void paste() {
  }

  /**
   * redo
   */
  public void redo() {
  }

  /**
   * replace
   */
  public void replace() {
  }

  /**
   * save
   *
   * @return boolean
   */
  public boolean save() {
    return false;
  }

  /**
   * selectAll
   */
  public void selectAll() {
  }

  /**
   * undo
   */
  public void undo() {
  }

  /**
   * accept
   *
   * @param document Document
   * @return boolean
   */
  public boolean accept(org.codebuilder.project.Document document) {
    //String path = document.getPath();
    //return document instanceof XMLModel || path.endsWith(".xml") || path.endsWith(".xsd");
    return true;
  }

  /**
   * getName
   *
   * @return String
   */
  public String getName() {
    return "Visual XML viewer";
  }

}
