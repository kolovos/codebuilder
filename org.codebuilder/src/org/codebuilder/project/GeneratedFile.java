package org.codebuilder.project;

import java.io.File;
import javax.swing.ImageIcon;
import org.codebuilder.ide.ui.resources.IconFactory;
import javax.swing.*;

public class GeneratedFile extends Document{

  public GeneratedFile(String path) {
    setPath(path);
    setName((new File(path)).getName());
  }

  /**
   * validate
   */
  public void validate() {
  }

  public Icon getIcon(){
    return IconFactory.Generated;
  }

  public boolean equals(Object obj){
    if (!(obj instanceof GeneratedFile)) return false;
    return ((GeneratedFile) obj).getPath().compareTo(this.getPath()) == 0;
  }

  /**
   * reset
   */
  public void reset() {
  }

  /**
   * getSuffix
   *
   * @return String
   */
  public String getSuffix() {
    return "";
  }

  /**
   * getType
   *
   * @return String
   */
  public String getType() {
    return "GeneratedFile";
  }

  /**
   * getDescription
   *
   * @return String
   */
  public String getDescription() {
    return "A generated file";
  }

  /**
   * getShortDescription
   *
   * @return String
   */
  public String getShortDescription() {
    return "Generated file";
  }

}
