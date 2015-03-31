package org.codebuilder.ide.ui.editors.xmleditor.xml;

import org.jdom.*;

public class PrettyElement extends Element{

  public PrettyElement(String p0){
    super(p0);
  }

  public PrettyElement(String p0, String p1){
    super(p0, p1);
  }

  public PrettyElement(String p0, String p1, String p2){
      super(p0,p1,p2);
  }

  public PrettyElement(String p0, Namespace ns){
    super(p0, ns);
  }

  public String toString(){
    return this.getName();
  }

}
