package org.codebuilder.models.xml;

import org.jdom.*;
import java.util.*;

public class IdrefsAttribute
    extends Attribute{

  private List target = null;
  private boolean targetSet = false;

  public IdrefsAttribute(String string1, String string2){
    super(string1, string2);
  }

  public IdrefsAttribute(String string1, String string2, Namespace ns){
    super(string1,string2,ns);
  }

  public IdrefsAttribute(String string1, String string2, int int1){
    super(string1, string2, int1);
  }

  public IdrefsAttribute(String string1, String string2, int int1, Namespace ns){
    super(string1, string2, int1, ns);
  }

  public IdrefsAttribute() {
    super();
  }

  public List getTarget() {
    return target;
  }

  public void setTarget(List target) {
    this.target = target;
    targetSet = true;
  }

  public boolean isTargetSet(){
    return targetSet;
  }

  public String toString(){
  return "[IDREFSAttribute " + this.getName() + " : " + this.getValue() + " ]";
  }

}
