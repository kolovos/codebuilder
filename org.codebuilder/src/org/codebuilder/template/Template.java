package org.codebuilder.template;

import java.io.*;
import java.util.*;

import org.codebuilder.common.file.*;
import org.codebuilder.template.handwritten.*;
import org.codebuilder.ide.feedback.FeedbackManager;

public class Template implements Cloneable{

  protected String path;
  private org.apache.velocity.Template template;
  protected String target;
  protected Context context = new Context();
  protected Context globalContext = new Context();
  protected boolean preserveHandWrittenRegions;
  private boolean overwrite;
  protected ArrayList generatedFiles = new ArrayList();
  protected String output = "";

  public void setPath(String path) {
    this.path = path;
  }

  public String getPath() {
    return this.path;
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getTarget() {
    return target;
  }

  public void load(String path) throws Exception {
    setPath(path);
    load();
  }

  public List getGeneratedFiles(){
    return generatedFiles;
  }

  public void process(String target) throws TemplateException {
    setTarget(target);
    process();
  }

  public void process() throws TemplateException {

    //context.put("template", this);

    // ---------------------
    // Process the template
    // and write the output
    // into a string
    // ---------------------

    StringWriter writer = new StringWriter();
    try {
      template.merge(context, writer);
      writer.flush();
      writer.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new TemplateException(this, TemplateExceptionKind.WRITE_EXCEPTION,
                                  ex);
    }

    // ---------------------
    // Check if the target file exists.
    // If it does not exist or
    // if overwrite is set to true
    // write the new content
    // ---------------------

    String merged = "";

    File targetFile = new File(this.getTarget());
    if (overwrite || !targetFile.exists()) {
      // ---------------------
      // Use the HandWrittenRegionPreserver
      // to preserve hand written regions
      // ---------------------
      if (preserveHandWrittenRegions && targetFile.exists()) {
        HandWrittenRegionPreserver preserver = new HandWrittenRegionPreserver();
        try {
          merged = preserver.merge(writer.toString(), this.getTarget());
        }
        catch (HandWrittenRegionException ex) {
          throw new TemplateException(this,
                                      TemplateExceptionKind.DUBLICATE_HAND_WRITTEN_REGION_EXCEPTION,
                                      ex);
        }
      }
      else {
        merged = writer.toString();
      }
    }

    // ---------------------
    // Try to write the merged contents
    // into the output file. If it fails
    // throw a template exception
    // ---------------------

    BatchWriter batch = new BatchWriter();
    try {
      output = merged;
      batch.writeAll(merged, this.getTarget());
      generatedFiles.add(this.getTarget());
    }
    catch (IOException ex) {
      throw new TemplateException(this, TemplateExceptionKind.WRITE_EXCEPTION,
                                  ex);
    }
  }

  public void load() throws TemplateException {
    TemplateConverter converter = new TemplateConverter();
    try {
      String copyPath = converter.createCopy(path);
      TemplateEngine.getInstance().getTemplate(path);

      // ---------------------
      // In case the original template has
      // loaded and parsed correctly, there
      // are good chances that the copy will
      // do the same
      // ---------------------

      template = TemplateEngine.getInstance().getTemplate(copyPath);

    }
    catch (Exception ex) {
      ex.printStackTrace();
      throw new TemplateException(this, TemplateExceptionKind.READ_EXCEPTION,
                                  ex);
    }
  }

  public void checkSyntax() throws Exception {
    TemplateEngine.getInstance().getTemplate(path);
  }

  public boolean getPreserveHandWrittenRegions() {
    return preserveHandWrittenRegions;
  }

  public void setPreserveHandWrittenRegions(boolean preserveHandWrittenRegions) {
    this.preserveHandWrittenRegions = preserveHandWrittenRegions;
  }

  public boolean getOverwrite() {
    return overwrite;
  }

  public void setOverwrite(boolean overwrite) {
    this.overwrite = overwrite;
  }

  public Template() {
    preserveHandWrittenRegions = true;
    overwrite = true;
    template = new org.apache.velocity.Template();
  }

  public void reset() {
    this.generatedFiles = new ArrayList();
  }
  public Context getGlobalContext() {
    return globalContext;
  }
  public void setGlobalContext(Context globalContext) {
    this.globalContext = globalContext;
    context = new Context(globalContext);
  }

  /**
   * @deprecated
   * @throws CloneNotSupportedException
   * @return Object
   */
  public Object clone () throws CloneNotSupportedException {
    Template clone = (Template) super.clone();  // field-by-field copy
    clone.setContext((Context) this.getContext().clone());
    return clone;
  }
  public String getOutput() {
    return output;
  }

}
