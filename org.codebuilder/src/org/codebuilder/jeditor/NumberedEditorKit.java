package org.codebuilder.jeditor;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;
import java.awt.*;

public class NumberedEditorKit extends StyledEditorKit {
    public ViewFactory getViewFactory() {
      return new NumberedViewFactory();
    }
}
