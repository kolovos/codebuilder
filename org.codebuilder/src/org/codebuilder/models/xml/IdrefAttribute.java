package org.codebuilder.models.xml;

import org.jdom.*;

public class IdrefAttribute extends Attribute{

  private Element target = null;
  private boolean targetSet = false;

  public IdrefAttribute(String string1, String string2){
    super(string1, string2);
  }

  public IdrefAttribute(String string1, String string2, Namespace ns){
    super(string1,string2,ns);
  }

  public IdrefAttribute(String string1, String string2, int int1){
    super(string1, string2, int1);
  }

  public IdrefAttribute(String string1, String string2, int int1, Namespace ns){
    super(string1, string2, int1, ns);
  }

  public IdrefAttribute() {
    super();
  }

  public Element getTarget() {
    return target;
  }

  public void setTarget(Element target) {
    this.target = target;
    targetSet = true;
  }

  public boolean isTargetSet(){
    return targetSet;
  }

  public String toString(){
    return "[IDREFAttribute " + this.getName() + " : " + this.getValue() + " ]";
  }
}
