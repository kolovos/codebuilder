package org.codebuilder.project;

public class DocumentValidationException
    extends Exception {

  protected int line; // the line in which the validation exception happened
  protected int column; // the column in which the validation exception happened
  protected String message; // the relative message

  public DocumentValidationException(int line, int column, String message,
                                     Exception original) {
    super(original);
    this.line = line;
    this.column = column;
    this.message = message;
  }

  /**
   * Sets the line in which the validation exception happened
   * @param line The new value
   */
  public void setLine(int line) {
    this.line = line;
  }

  /**
   * Returns the line in which the validation exception happened
   * @return int The value of line
   */
  public int getLine() {
    return this.line;
  }

  /**
   * Sets the column in which the validation exception happened
   * @param column The new value
   */
  public void setColumn(int column) {
    this.column = column;
  }

  /**
   * Returns the column in which the validation exception happened
   * @return int The value of column
   */
  public int getColumn() {
    return this.column;
  }

  /**
   * Sets the relative message
   * @param message The new value
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Returns the relative message
   * @return String The value of message
   */
  public String getMessage() {
    return this.message;
  }

}
