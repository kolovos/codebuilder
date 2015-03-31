package org.codebuilder.ide.feedback;

import java.util.ArrayList;
import java.util.ListIterator;

public class FeedbackManager {

  private ArrayList feedbackListeners = new ArrayList();
  private ArrayList messages = new ArrayList();

  private static FeedbackManager instance = null;

  private FeedbackManager() {

  }

  /**
   * Returns the one and only instance
   * of the singleton
   */
  public static FeedbackManager getInstance() {
    if (instance == null) {
      synchronized (FeedbackManager.class) {
        if (instance == null) {
          instance = new FeedbackManager();
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

  /**
   * Adds a new feedback listener
   * @param listener FeedbackListener
   */
  public void addFeedbackListener(FeedbackListener listener) {
    feedbackListeners.add(listener);
  }

  /**
   * Removes the supplied feedback listener
   * @param listener FeedbackListener
   */
  public void removeFeedbackListener(FeedbackListener listener) {
    feedbackListeners.remove(listener);
  }

  /**
   * Returns the messages that
   * are stored in the feedback
   * manager
   * @return ArrayList
   */
  public ArrayList getMessages() {
    return messages;
  }

  public void reportException(Exception ex) {
    report(ex.toString(), "", FeedbackSeverity.ERROR);
    ex.printStackTrace();
  }

  public void reportException(Throwable ex) {
    report(ex.toString(), "", FeedbackSeverity.ERROR);
    ex.printStackTrace();
  }

  public void report(String message, String details, FeedbackSeverity severity) {

    // ---------------------
    // Record the message
    // ---------------------

    Feedback feedback = new Feedback();
    feedback.setMessage(message);
    feedback.setSeverity(severity);
    feedback.setDetails(details);
    messages.add(feedback);

    // ---------------------
    // Notify the listeners
    // ---------------------

    notifyFeedbackListeners(feedback);

  }

  public void clearMessages(){
    messages.clear();
    notifyFeedbackListeners(null);
  }

  public void notifyFeedbackListeners(Feedback feedback){
    ListIterator li = feedbackListeners.listIterator();
    while (li.hasNext()){
      ((FeedbackListener) li.next()).feedbackReceived(feedback);
    }
  }
}
