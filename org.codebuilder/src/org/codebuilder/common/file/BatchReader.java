package org.codebuilder.common.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

public class BatchReader {

  public BatchReader() {
  }

  /**
   * Reads a file into a string
   * @param file File
   * @throws FileNotFoundException
   * @throws IOException
   * @return String
   */
  public String readAll(File file) throws
      FileNotFoundException, IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    StringBuffer buffer = new StringBuffer();
    String str;
    while ((str = reader.readLine()) != null){
      buffer.append(str + "\r\n");
    }
    return buffer.toString();
  }

  /**
   * Reads a file into a string
   * given its filename
   * @param filename String
   * @throws FileNotFoundException
   * @throws IOException
   * @return String
   */
  public String readAll(String filename) throws FileNotFoundException,
      IOException {
        File file = new File(filename);
        return readAll(file);
  }

  public String readAllNoExceptions(String filename){
    String contents;
    try {
      contents = readAll(filename);
    }
    catch (FileNotFoundException ex) {
      contents = ex.getMessage();
    }
    catch (IOException ex) {
      contents = ex.getMessage();
    }
    return contents;
  }

}
