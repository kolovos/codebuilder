package org.codebuilder.project;

import java.util.ArrayList;
import java.util.ListIterator;

public class TemplateProcessCoordinator {

  private static TemplateProcessCoordinator instance = null;

  protected boolean canceled; // if processing is cancelled

  protected ArrayList templateProcessListeners = new ArrayList();

  private TemplateProcessCoordinator() {

  }

  /**
   * Returns the one and only instance
   * of the singleton
   */
  public static TemplateProcessCoordinator getInstance() {
    if (instance == null) {
      synchronized (TemplateProcessCoordinator.class) {
        if (instance == null) {
          instance = new TemplateProcessCoordinator();
        }
      }
    }
    return instance;
  }

  /**
   * Sets if processing is cancelled
   * @param canceled The new value
   */
  public void setCanceled(boolean canceled) {
    this.canceled = canceled;
  }

  /**
   * Returns if processing is cancelled
   * @return boolean The value of canceled
   */
  public boolean getCanceled() {
    return this.canceled;
  }

  //[Element: <listener/>]

  /**
   * Adds a new TemplateProcessListener listener to the
   * templateProcessListeners
   */
  public void addtemplateProcessListener(TemplateProcessListener
                                         templateProcessListener) {
    templateProcessListeners.add(templateProcessListener);
  }

  /**
   * Removes the specified TemplateProcessListener listener from the
   * templateProcessListeners
   */
  public void removetemplateProcessListener(TemplateProcessListener
                                            templateProcessListener) {
    templateProcessListeners.remove(templateProcessListener);
  }

  /**
   * Notifies all the
   * templateProcessListeners
   */
  public void notifyAllTemplateProcessListeners(Template template) {
    ListIterator li = templateProcessListeners.listIterator();
    while (li.hasNext()) {
      TemplateProcessListener templateProcessListener = (
          TemplateProcessListener) li.next();
      templateProcessListener.processingTemplate(template);
    }
  }

  /**
   * Resets the singleton
   */
  public static void reset() {
    instance = null;
  }

  public void processingTemplate(Template template){
    notifyAllTemplateProcessListeners(template);
  }

}
