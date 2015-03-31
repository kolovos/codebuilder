package org.codebuilder.ide.ui;

import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.util.ArrayList;

/**
 * <p>Title: CodeBuilder</p>
 Framework</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Dimitrios Kolovos</p>
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */

public class IdleManager
    implements MouseListener, KeyListener {

  private static IdleManager instance = null;
  private ArrayList idleListeners = new ArrayList();

  private IdleManager() {

  }

  public static IdleManager getInstance() {
    if (instance == null) {
      synchronized (IdleManager.class) {
        if (instance == null) {
          instance = new IdleManager();
        }
      }
    }
    return instance;
  }

  public void addComponent(Component c){

  }

  public void removeComponent(Component c){

  }

  public void notifyIdleListeners(){

  }

  public void addIdleListener(IdleListener listener){
    this.idleListeners.add(listener);
  }

  public void removeIdleListener(IdleListener listener){
    this.idleListeners.remove(listener);
  }

  /**
   * keyPressed
   *
   * @param e KeyEvent
   */
  public void keyPressed(KeyEvent e) {
  }

  /**
   * keyReleased
   *
   * @param e KeyEvent
   */
  public void keyReleased(KeyEvent e) {
  }

  /**
   * keyTyped
   *
   * @param e KeyEvent
   */
  public void keyTyped(KeyEvent e) {
  }

  /**
   * mouseClicked
   *
   * @param e MouseEvent
   */
  public void mouseClicked(MouseEvent e) {
  }

  /**
   * mouseEntered
   *
   * @param e MouseEvent
   */
  public void mouseEntered(MouseEvent e) {
  }

  /**
   * mouseExited
   *
   * @param e MouseEvent
   */
  public void mouseExited(MouseEvent e) {
  }

  /**
   * mousePressed
   *
   * @param e MouseEvent
   */
  public void mousePressed(MouseEvent e) {
  }

  /**
   * mouseReleased
   *
   * @param e MouseEvent
   */
  public void mouseReleased(MouseEvent e) {
  }

}
