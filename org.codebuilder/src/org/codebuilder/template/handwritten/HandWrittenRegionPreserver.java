package org.codebuilder.template.handwritten;

import java.io.File;
import java.util.TreeMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.codebuilder.common.file.BatchWriter;
import org.codebuilder.common.text.Escaper;
import org.codebuilder.template.Template;

public class HandWrittenRegionPreserver {


  public HandWrittenRegionPreserver() {

  }

  public String merge(String generated, String filename) throws
      HandWrittenRegionException{
    File file = new File(filename);
    return merge(generated, file);
  }

  public String merge(String generated, File existing) throws
      HandWrittenRegionException {
    HandWrittenRegionParser parser = new HandWrittenRegionParser();

    // ---------------------
    // If the target file exists
    // preserve the hand written regions
    // else just flush the generated
    // code into it
    // ---------------------

    if (existing.exists()) {
      TreeMap generatedRegions = (TreeMap) parser.getRegions(generated);
      TreeMap existingRegions = (TreeMap) parser.getRegions(existing);

      Iterator i = generatedRegions.values().iterator();

      while (i.hasNext()) {
        HandWrittenRegion generatedRegion = (HandWrittenRegion) i.next();
        if (existingRegions.containsKey(generatedRegion.getName())) {
          HandWrittenRegion existingRegion = (HandWrittenRegion)
              existingRegions.get(generatedRegion.getName());

          Escaper escaper = new Escaper();
          String escapedGeneratedRegionContent = escaper.
              escapeRegularExpression(generatedRegion.getContent());
          String escapedExistingRegionContent = escaper.escapeRegularExpression(
              existingRegion.getContent());

          generated = generated.replaceAll(escapedGeneratedRegionContent,
                                           escapedExistingRegionContent);
        }
      }
    }

    return generated;
  }

}
