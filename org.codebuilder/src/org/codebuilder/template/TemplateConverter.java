package org.codebuilder.template;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.*;
import java.util.regex.Pattern;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.*;
import org.apache.velocity.exception.*;

/**
 * Manages the exess whitespace produced by Velocity.
 * Provides methods that parse a template file and create
 * a copy of it but with the whitespace handled
 * <p>Title: Velocity Filter</p>
 * <p>Description: Filter for whitespace handling in Velocity templates </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Generative Technologies</p>
 * @author Dimitrios Kolovos
 * @version 1.0
 */

public class TemplateConverter {

  public TemplateConverter() {
  }

  public String createCopy(String source) throws FileNotFoundException,
      FileNotFoundException, IOException {
    // ---------------------
    // Create a temp file
    // and copy the template there
    // ---------------------

    String tempfile = File.createTempFile("ecgftemplate","vm").getAbsolutePath();
    return createCopy(source, tempfile);
  }

  public String createCopy(String source, String target) throws
      FileNotFoundException, IOException {

    BufferedReader reader = null;
    BufferedWriter writer = null;
    try {
      reader = new BufferedReader(new FileReader(source));
      writer = new BufferedWriter(new FileWriter(target));

      // ---------------------
      // Open the source template file
      // and read each line
      // If the line starts with spaces followed by #
      // then trim the spaces and write it to
      // the copy file
      // ---------------------

      String currentLine;
      Pattern pattern = Pattern.compile("(^\\s*?#)");
      while ( (currentLine = reader.readLine()) != null) {
        String str = pattern.matcher(currentLine).replaceAll("#");
        writer.write(str + "\r\n");
      }
    }
    catch (FileNotFoundException ex) {
      throw ex;
    }
    catch (IOException ex) {
      throw ex;
    }
    finally {
      if (writer != null) {
        writer.flush();
        writer.close();
      }
      if (reader != null) {
        reader.close();
      }
    }

    return target;
  }

  public Template getTemplate(String name, VelocityEngine velocity) throws
      ParseErrorException, ResourceNotFoundException, Exception {
    Template template = null;
    try {
      template = velocity.getTemplate(name);
    }
    catch (ParseErrorException ex) {
      throw ex;
    }
    catch (ResourceNotFoundException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw ex;
    }

    if (template == null) {
      return template;
    }

    String copy = this.createCopy(name);
    template = velocity.getTemplate(copy);

    return template;
  }

  public Template getTemplate(String name) throws
      ParseErrorException, ResourceNotFoundException, Exception {
    Template template = null;
    try {
      template = Velocity.getTemplate(name);
    }
    catch (ParseErrorException ex) {
      throw ex;
    }
    catch (ResourceNotFoundException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw ex;
    }

    if (template == null) {
      return template;
    }

    String copy = this.createCopy(name);
    template = Velocity.getTemplate(copy);

    return template;

  }

}
