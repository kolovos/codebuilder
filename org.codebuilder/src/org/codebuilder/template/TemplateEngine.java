package org.codebuilder.template;

import org.apache.velocity.app.VelocityEngine;
import java.util.Properties;
import org.apache.velocity.runtime.RuntimeConstants;

public class TemplateEngine extends VelocityEngine{

  private static TemplateEngine instance = null;

  private TemplateEngine(){
    try {
      init();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void init() throws Exception {
    Properties properties = new Properties();
    addCustomProperties(properties);
    super.init(properties);
  }

  private void addCustomProperties(Properties properties){
    properties.setProperty(RuntimeConstants.UBERSPECT_CLASSNAME, "org.codebuilder.template.introspection.DynamicIntrospection");
    properties.setProperty("resource.loader","file");
    properties.setProperty("file.resource.loader.class", "org.codebuilder.template.TemplateReader");
    properties.setProperty("velocimacro.permissions.allowInline","true");
    properties.setProperty("file.resource.loader.cache","false");
    properties.setProperty("velocimacro.library.autoreload","true");
  }

  public void init(Properties properties) throws Exception {
    addCustomProperties(properties);
    super.init(properties);
  }

  public static TemplateEngine getInstance(){
    if (instance == null){
      synchronized(TemplateEngine.class){
        if (instance == null){
          instance = new TemplateEngine();
        }
      }
    }
    return instance;
  }

  public static void reset(){
    instance = null;
  }



}
