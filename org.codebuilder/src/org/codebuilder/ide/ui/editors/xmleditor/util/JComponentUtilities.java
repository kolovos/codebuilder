package org.codebuilder.ide.ui.editors.xmleditor.util;

import javax.swing.*;
import java.awt.*;

public class JComponentUtilities {
  public JComponentUtilities() {
  }

  public static void updateUI(JComponent component, boolean deep) {
    component.updateUI();
    if (!deep) {
      return;
    }

    if (component instanceof Container && ! (component instanceof JToolBar)) {
      Container container = (Container) component;
      int componentCount = container.getComponentCount();
      for (int i = 0; i < componentCount; i++) {
        if (container.getComponent(i) instanceof JComponent) {
          updateUI( (JComponent) container.getComponent(i), deep);
        }
      }
    }
  }

}
