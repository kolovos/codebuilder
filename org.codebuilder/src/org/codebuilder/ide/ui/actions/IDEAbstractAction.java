package org.codebuilder.ide.ui.actions;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import org.codebuilder.ide.ui.IdleListener;
import org.codebuilder.ide.ui.IdleManager;

public abstract class IDEAbstractAction extends AbstractAction implements IdleListener{

  public IDEAbstractAction(){
    IdleManager.getInstance().addIdleListener(this);
  }

  /**
   * actionPerformed
   *
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    perform();
  }

  public abstract boolean perform(Object object);

  public boolean perform(){
    return perform(null);
  }

  /**
   * idleUserDetected
   */
  public void idleUserDetected() {
    this.setEnabled(this.canHappen());
  }

  public boolean canHappen(){
    return true;
  }
}
