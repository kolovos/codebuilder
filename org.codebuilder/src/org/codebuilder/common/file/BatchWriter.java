package org.codebuilder.common.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BatchWriter {
  public BatchWriter() {
  }

  public void writeAll(String content, String filename) throws IOException {
    File file = new File(filename);
    writeAll(content, file);
  }

  public void writeAll(String content, File file) throws IOException {
    FileWriter writer = new FileWriter(file);
    writer.write(content);
    writer.flush();
    writer.close();
  }

}
