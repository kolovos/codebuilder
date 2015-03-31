package org.codebuilder.ide.feedback;

public class Feedback {

  protected String message = ""; // a short message describing the feedback
  protected String details = ""; // a more detailed message
  protected FeedbackSeverity severity; // the severity of the feedback

  /**
   * Sets a short message describing the feedback
   * @param message The new value
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Returns a short message describing the feedback
   * @return String The value of message
   */
  public String getMessage() {
    return this.message;
  }

  /**
   * Sets a more detailed message
   * @param details The new value
   */
  public void setDetails(String details) {
    this.details = details;
  }

  /**
   * Returns a more detailed message
   * @return String The value of details
   */
  public String getDetails() {
    return this.details;
  }

  /**
   * Sets the severity of the feedback
   * @param severity The new value
   */
  public void setSeverity(FeedbackSeverity severity) {
    this.severity = severity;
  }

  /**
   * Returns the severity of the feedback
   * @return FeedbackSeverity The value of severity
   */
  public FeedbackSeverity getSeverity() {
    return this.severity;
  }

  /**
   * Returs the contents of the
   * instance in a string form
   * @return String
   */
  public String toString() {
    return message;
    /*
               return
                  "message : " + message + "\n" +
                  "details : " + details + "\n" +
                  "severity : " + severity + "\n" +
                  "";
     */
  }
}
