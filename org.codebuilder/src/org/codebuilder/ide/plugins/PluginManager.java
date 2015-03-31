package org.codebuilder.ide.plugins;

import javax.swing.JMenu;
import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import java.io.IOException;
import org.jdom.JDOMException;
import java.util.ArrayList;
import java.util.ListIterator;
import org.jdom.Element;
import org.codebuilder.ide.feedback.FeedbackManager;
import javax.swing.JToolBar;
import org.codebuilder.ide.ui.*;

public class PluginManager {
  public PluginManager() {
  }

  public void populateUI(JMenu menu, JToolBar toolbar){

    ArrayList plugins = new ArrayList();
    try {
      plugins = parsePluginsDescriptor();
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
      return;
    }
    ListIterator li = plugins.listIterator();
    while (li.hasNext()){
      String clazz = (String) li.next();
      try {
        Plugin plugin = (Plugin) Class.forName(clazz).newInstance();
        PluginAction pluginAction = new PluginAction(plugin);
        if (menu != null)
          menu.add(pluginAction);
        if (toolbar != null)
          toolbar.add(pluginAction);
      }
      catch (Exception ex){
        FeedbackManager.getInstance().reportException(ex);
      }
    }
  }

  public ArrayList parsePluginsDescriptor() throws IOException, JDOMException {
      SAXBuilder builder = new SAXBuilder();
      ArrayList plugins = new ArrayList();
      Document doc = builder.build("config/plugins.xml");
      ListIterator li = doc.getRootElement().getChildren("plugin").listIterator();
      while (li.hasNext()){
        Element el = (Element) li.next();
        String plugin = el.getAttributeValue("class");
        if (plugin != null){
          plugins.add(plugin);
        }
      }
      return plugins;
  }
}
