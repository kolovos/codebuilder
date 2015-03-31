package org.codebuilder.project;

import java.util.ArrayList;
import java.io.File;
import java.io.*;
import org.codebuilder.ide.feedback.FeedbackManager;
import org.apache.velocity.exception.ParseErrorException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ListIterator;
import org.codebuilder.ide.ui.resources.IconFactory;
import org.codebuilder.template.TemplateException;
import org.codebuilder.template.Context;
import javax.swing.*;
import net.infonode.properties.types.*;

public class Template
    extends Document
    implements Cloneable {

  private org.codebuilder.template.Template template;

  public Template() {
    template = new org.codebuilder.template.Template();
    this.properties.add(new BooleanProperty("overwrite", "true",
        "Set to false to ask before overwritting files"));
    this.properties.add(new BooleanProperty("preserve regions", "true",
        "Set to true to preserve hand written regions"));
    this.properties.add(new BooleanProperty("startup", "false",
        "Set to true to process the template when project is built"));
    this.properties.add(new StringProperty("target", "",
        "Where the output of the template will be stored"));
    this.properties.add(new StringProperty("language", "",
        "The language of the produced code (e.g. Java, VB.NET, HTML etc)"));
  }

  public void load() throws TemplateException {
    template.setPath(getPath());
    template.load();
  }

  public void setGlobalContext(Context context) {
    template.setGlobalContext(context);
  }

  public Context getGlobalContext() {
    return template.getGlobalContext();
  }

  public boolean isStartUp() {
    return this.properties.get("startup").getBooleanValue();
  }

  public void setStartUp(boolean startUp) {
    this.properties.get("startup").setValue("" + startUp);
  }

  public ArrayList getGeneratedFiles() {
    ListIterator li = template.getGeneratedFiles().listIterator();
    ArrayList files = new ArrayList();
    while (li.hasNext()) {
      files.add(new GeneratedFile( (String) li.next()));
    }
    return files;
  }

  public void reset() {
    template.reset();
  }

  public void validate() throws DocumentValidationException {

    try {
      template.checkSyntax();
    }
    catch (Exception ex) {
      if (ex instanceof ParseErrorException) {
        ParseErrorException pe = (ParseErrorException) ex;
        String message = pe.getMessage();

        Pattern pattern = Pattern.compile(
            "(.|\n|\r)*at line (\\d)+, column (\\d)+(.|\n|\r)*");
        Matcher matcher = pattern.matcher(message);

        matcher.find();
        int line = Integer.parseInt(matcher.group(2));
        int column = Integer.parseInt(matcher.group(3));

        throw new DocumentValidationException(line, column, message, pe);
      }
      else {
        FeedbackManager.getInstance().reportException(ex);
      }
    }
  }

  public Icon getIcon() {
    if (isStartUp()) {
      return IconFactory.Template.Run();
    }
    else {
      return IconFactory.Template;
    }
  }

  public void process() throws TemplateException {
    template.getContext().put("this", this);
    template.setOverwrite(properties.get("overwrite").getBooleanValue());
    template.setPreserveHandWrittenRegions(properties.get("preserve regions").
                                           getBooleanValue());

    String target = properties.get("target").getValue().toString();
    if (target.trim().length() == 0){
      try {
       target = File.createTempFile("Generated", "").getCanonicalPath();
      }
      catch (IOException ex) {
        /**
         * @todo Handle this exception
         */
        ex.printStackTrace();
      }
    }
    template.setTarget(target);

    TemplateProcessCoordinator coordinator = TemplateProcessCoordinator.
        getInstance();
    if (!coordinator.getCanceled()) {
      coordinator.processingTemplate(this);
      template.process();
    }

  }

  public void process(String target) throws TemplateException {
    properties.get("target").setValue(target);
    process();
  }

  /**
   * @deprecated
   * @throws CloneNotSupportedException
   * @return Object
   */
  public Object clone() throws CloneNotSupportedException {
    Template clone = (Template) super.clone();
    clone.template = (org.codebuilder.template.Template) clone.template.clone();
    return clone;
    //return super.clone(); // field-by-field copy
  }

  public Object newInstance(){
    Object newInstance = null;
    try {
      newInstance = clone();
    }
    catch (CloneNotSupportedException ex) {
      FeedbackManager.getInstance().reportException(ex);
    }
    return newInstance;
  }

  /**
   * getSuffix
   *
   * @return String
   */
  public String getSuffix() {
    return "cbf";
  }

  /**
   * getType
   *
   * @return String
   */
  public String getType() {
    return "Template";
  }

  public String getDescription() {
    return "Velocity Template Language (VTL) template";
  }

  public void setContext(Context context) {
    template.setContext(context);
  }

  public Context getContext() {
    return template.getContext();
  }

  /**
   * getShortDescription
   *
   * @return String
   */
  public String getShortDescription() {
    return "Template";
  }

  public String getLanguage(){
    return properties.get("language").getValue().toString();
  }

  public String getOutput(){
    return template.getOutput();
  }

}
