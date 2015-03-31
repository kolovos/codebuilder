package org.codebuilder.template;

import java.io.IOException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import java.io.FileNotFoundException;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;

public class Workbench {
  public Workbench() {
  }

  public static void main(String[] args) throws IOException,
      ParseErrorException, ResourceNotFoundException, FileNotFoundException,
      IOException, Exception {

    //TemplateEngine.init();
    Template template = new Template();

    SAXBuilder builder = new SAXBuilder();
    Document doc = builder.build("C:/VTExample/demo.xml");

    //template.load("C:/VTExample/demo.vm");
    template.load("C:/VTExample/singleton.vm");
    Context context = new Context();
    context.put("template", template);
    context.put(doc.getRootElement().getName(),doc.getRootElement());

    template.setContext(context);

    template.setPreserveHandWrittenRegions(true);
    template.process("C:/VTExample/result.txt");
  }

}
