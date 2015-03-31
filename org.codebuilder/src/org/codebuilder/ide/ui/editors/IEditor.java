package org.codebuilder.ide.ui.editors;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import org.codebuilder.project.Document;
import javax.swing.*;

public abstract class IEditor
    extends JPanel {


  protected Document document;

  public abstract boolean accept(Document document);

  public abstract String getName();

  public abstract void cut();

  public abstract void copy();

  public abstract void paste();

  public abstract void delete();

  public abstract void selectAll();

  public abstract void find();

  public abstract void replace();

  public abstract boolean save();

  public abstract boolean load(Document document);

  public abstract boolean isDirty();

  public abstract void undo();

  public abstract void redo();

  public Icon getIcon(){
    if (this.getDocument() != null){
      return this.getDocument().getIcon();
    }
    return null;
  };

  public abstract void goTo(int line, int column);

  public Document getDocument(){
    return document;
  }

  //public void setDocument(Document document){
  //  this.document = document;
  //}

  public abstract String getCaption();
}
