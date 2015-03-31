package org.codebuilder.ide.feedback;

import javax.swing.ImageIcon;
import org.codebuilder.ide.ui.resources.IconFactory;

public final class FeedbackSeverity {

  private String value;
  private ImageIcon icon;

  // The enumeration members

  /**
   * Something succedded
   */
  public static FeedbackSeverity SUCCESS = new FeedbackSeverity(
      "Success", IconFactory.getIconFor("information"));

  /**
   * Just information
   */
  public static FeedbackSeverity INFORMATION = new FeedbackSeverity(
      "Information", IconFactory.getIconFor("information"));

  /**
   * Debug information
   */
  public static FeedbackSeverity DEBUG = new FeedbackSeverity(
      "Debug", IconFactory.getIconFor("debug"));

  /**
   * A situation that is not desirable but does not require the interaction of the user either
   */
  public static FeedbackSeverity WARNING = new FeedbackSeverity("Warning", IconFactory.getIconFor("warning"));

  /**
   * A situation that is errorneous
   */
  public static FeedbackSeverity ERROR = new FeedbackSeverity("Error", IconFactory.getIconFor("error"));

  /**
   * A situation that the system cannot recover from
   */
  public static FeedbackSeverity FATAL_ERROR = new FeedbackSeverity(
      "Fatal error", IconFactory.FatalError);

  /**
   * Private constructor to ensure that
   * instances can only be creaded from
   * within the context of the class
   * @param value String
   */
  private FeedbackSeverity(String value, ImageIcon icon) {
    this.value = value;
    this.icon = icon;
  }

  /**
   * Returns the internal value
   * of the member
   * @return String the interal value
   */
  public String toString() {
    return value;
  }
  public ImageIcon getIcon() {
    return icon;
  }
  public void setIcon(ImageIcon icon) {
    this.icon = icon;
  }
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }
}
