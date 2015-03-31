package org.codebuilder.ide.ui.dialogs.tools;

public class ConfigurePluginsDialog
    extends FlatXMLConfigurationDialog{

  public ConfigurePluginsDialog() {
  }

  /**
 * To implement this method one must set:
 * title - The title of the dialog
 * description - The description of the dialog
 * columnNames - A collection of column names
 * columnCaptions - A collection of column captions
 * configurationFile - The path to the flat configuration file
 * configurable - A title for the object under configuration
 */
public void setConfiguration() {
    setTitle("Configure plugins");
    setDescription("Configure plugins that are used by CodeBuilder");
    columnNames = new String[]{"class"};
    columnCaptions = new String[]{"Class"};
    configurationFile = "config/plugins.xml";
    configurable = "plugin";
  }
}
