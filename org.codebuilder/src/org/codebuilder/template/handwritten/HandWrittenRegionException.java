package org.codebuilder.template.handwritten;

import org.codebuilder.template.TemplateException;

public class HandWrittenRegionException
    extends Exception {

  protected String region; // the name of the region that appears more than once
  protected int multiplicity; // how many times the region exists

  public HandWrittenRegionException(String region, int multiplicity) {
    this.region = region;
    this.multiplicity = multiplicity;
  }

  /**
   * Sets the name of the region that appears more than once
   * @param region The new value
   */
  public void setRegion(String region) {
    this.region = region;
  }

  /**
   * Returns the name of the region that appears more than once
   * @return String The value of region
   */
  public String getRegion() {
    return this.region;
  }

  /**
   * Sets how many times the region exists
   * @param multiplicity The new value
   */
  public void setMultiplicity(int multiplicity) {
    this.multiplicity = multiplicity;
  }

  /**
   * Returns how many times the region exists
   * @return int The value of multiplicity
   */
  public int getMultiplicity() {
    return this.multiplicity;
  }

}
