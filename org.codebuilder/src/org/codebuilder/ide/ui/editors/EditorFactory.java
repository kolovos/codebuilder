package org.codebuilder.ide.ui.editors;

import org.codebuilder.project.*;
import org.codebuilder.ide.ui.editors.xmleditor.*;
import org.codebuilder.models.xml.*;
import java.util.*;
import org.jdom.input.*;
import java.io.*;
import org.jdom.*;
import org.codebuilder.ide.feedback.*;

/**
 * <p>Title: CodeBuilder</p>
 Framework</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Dimitrios Kolovos</p>
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */

public class EditorFactory {

  ArrayList editorTypes = new ArrayList();
  private static EditorFactory instance = new EditorFactory();

  public EditorFactory() {
  }

  public static EditorFactory getInstance(){
    return instance;
  }

  public IEditor getEditorFor(org.codebuilder.project.Document document){

    IEditor editor = null;
    try {
      editor = (IEditor) getEditorsFor(document).get(0);
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
      return null;
    }

    if (document instanceof GeneratedFile){
      editor = new TextEditor();
    }

    editor.load(document);
    return editor;

  }

  private ArrayList getEditorTypes(){
    if (editorTypes.size() == 0){
      SAXBuilder builder = new SAXBuilder();
      try {
        org.jdom.Document doc = builder.build("config/editors.xml");
        ListIterator li = doc.getRootElement().getChildren().listIterator();
        while (li.hasNext()){
          Element el = (Element) li.next();
          try{
          IEditor editor = (IEditor) Class.forName(el.getAttributeValue("class")).newInstance();
          editorTypes.add(editor);
          }
          catch (Exception ex){
            FeedbackManager.getInstance().reportException(ex);
          }
        }
      }
      catch (Exception ex) {
        FeedbackManager.getInstance().reportException(ex);
      }
    }
    return editorTypes;
  }

  public ArrayList getEditorsFor(org.codebuilder.project.Document document){
    ListIterator li = getEditorTypes().listIterator();
    ArrayList suitableEditors = new ArrayList();
    while (li.hasNext()){
      IEditor editor = (IEditor) li.next();
      if (editor.accept(document)){
        suitableEditors.add(editor);
      }
    }
    return suitableEditors;
  }


}
