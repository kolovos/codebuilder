package org.codebuilder.common.exception;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public interface ExceptionListener {

  public void exceptionOccured(String message, Exception ex);

}