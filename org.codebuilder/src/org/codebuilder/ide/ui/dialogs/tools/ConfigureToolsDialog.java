package org.codebuilder.ide.ui.dialogs.tools;

public class ConfigureToolsDialog extends FlatXMLConfigurationDialog{
  public ConfigureToolsDialog() {
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
    setTitle("Configure tools");
    setDescription("Configure tools available under the $tools variable in templates");
    columnNames = new String[]{"name", "class"};
    columnCaptions = new String[]{"Name", "Class"};
    configurationFile = "config/tools.xml";
    configurable = "tool";
  }
}
