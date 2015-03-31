package org.codebuilder.ide.ui.dialogs.tools;

public class ConfigureDocumentsDialog
    extends FlatXMLConfigurationDialog{

  public ConfigureDocumentsDialog() {
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
    setTitle("Configure documents");
    setDescription("Configure documents that can be integrated in CodeBuilder projects");
    columnNames = new String[]{"class"};
    columnCaptions = new String[]{"Class"};
    configurationFile = "config/documents.xml";
    configurable = "document";
  }
}
