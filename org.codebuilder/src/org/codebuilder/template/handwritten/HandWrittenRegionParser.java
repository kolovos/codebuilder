package org.codebuilder.template.handwritten;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.TreeMap;
import org.codebuilder.common.file.BatchReader;
import java.util.Map;
import java.util.Iterator;
import java.io.File;

public class HandWrittenRegionParser {

  private String handWrittenRegionExp =
    "Hand written region start \\[(.*?)\\]" +
    "(.*?)" +
    "Hand written region end \\[\\1\\]";

  public HandWrittenRegionParser() {

  }

  public Map getRegions(File file) throws
      HandWrittenRegionException {
    BatchReader batchReader = new BatchReader();
    String content = null;
    try {
      content = batchReader.readAll(file);
      return getRegions(content);
    }
    catch (IOException ex) {
      return new TreeMap();
    }
  }

  public static void main(String[] args) throws FileNotFoundException,
      FileNotFoundException, IOException, HandWrittenRegionException {

    HandWrittenRegionParser parser = new HandWrittenRegionParser();

    String filename = "C:/Projects/MSc/Final/ECGF_Project_Package/sample.java";
    TreeMap regions = (TreeMap) parser.getRegions(filename);
    Iterator i = regions.values().iterator();
  }

  public Map getRegions(String contents) throws HandWrittenRegionException{

        Pattern pattern = Pattern.compile(handWrittenRegionExp, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(contents);
        TreeMap regions = new TreeMap();

        while (matcher.find()){
          HandWrittenRegion region = new HandWrittenRegion();
          region.setStartIndex(matcher.start());
          region.setEndIndex(matcher.end());
          region.setContent(matcher.group());
          region.setName(matcher.group(1));
          if (regions.containsKey(region.getName())){
            throw new HandWrittenRegionException(region.getName(), 2);
          }
          else{
            regions.put(region.getName(), region);
          }
        }

        return regions;
  }

}
