package org.codebuilder.common.exception;

import java.util.ArrayList;
import java.util.ListIterator;

public class ExceptionReporter {

  private static ExceptionReporter instance = null;
  private ArrayList exceptionListeners = new ArrayList();

  private ExceptionReporter() {

  }

  public void reportException(Exception ex){
    reportException(ex.toString(), ex);
  }

  public void reportException(String message, Exception ex){
    ListIterator li = exceptionListeners.listIterator();
    while (li.hasNext()){
      ((ExceptionListener) li.next()).exceptionOccured(message, ex);
      /**
       * @todo add logging code here
       */
      }
  }

  public void addExceptionListener(ExceptionListener listener){
    this.exceptionListeners.add(listener);
  }

  public void removeExceptionListener(ExceptionListener listener){
    this.exceptionListeners.remove(listener);
  }

  /**
   * Returns the one and only instance of the singleton
   *
   * @return ExceptionReporter
   */
  public static ExceptionReporter getInstance() {
    if (instance == null) {
      synchronized (ExceptionReporter.class) {
        if (instance == null) {
          instance = new ExceptionReporter();
        }
      }
    }
    return instance;
  }

  /**
   * Resets the singleton
   */
  public static void reset() {
    instance = null;
  }

}
