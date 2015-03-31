package org.codebuilder.ide.ui;

import javax.swing.JMenu;
import org.codebuilder.ide.ui.widgets.*;
import org.codebuilder.ide.ui.actions.document.*;
import org.codebuilder.project.*;
import org.codebuilder.ide.ui.resources.*;
import org.codebuilder.ide.ui.editors.*;
import java.util.*;

/**
 * <p>Title: CodeBuilder</p>
 Framework</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Dimitrios Kolovos</p>
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */

public class ContextMenuFactory {
  public ContextMenuFactory() {

  }

  public static JMenu getMenuFor(Document document){

    JCustomizedMenu menu = null;

    menu = new JCustomizedMenu();

    if (!(document instanceof DocumentGroup)){
      JMenu open = new JCustomizedMenu();
      open.setText("Open with...");
      open.setIcon(IconFactory.Empty);
      open.setIcon(IconFactory.Edit);
      ListIterator li = EditorFactory.getInstance().getEditorsFor(document).listIterator();
      while (li.hasNext()){
        open.add(new EditDocumentAction(document, (IEditor) li.next()));
      }
      open.addSeparator();
      open.add(new DefaultSystemEditDocumentAction(document));
      open.add(new SelectSystemEditDocumentAction(document));
      menu.add(open);
      menu.addSeparator();
    }

    menu.add(NewDocumentAction.getInstance());
    menu.add(AddDocumentAction.getInstance());
    menu.addSeparator();
    menu.add(NewDocumentGroupAction.getInstance());
    if (!(document instanceof Project)){
      menu.addSeparator();
      menu.add(RemoveDocumentAction.getInstance());
    }

    return menu;
  }


}
