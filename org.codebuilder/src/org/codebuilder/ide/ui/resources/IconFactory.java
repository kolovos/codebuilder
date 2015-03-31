package org.codebuilder.ide.ui.resources;

import javax.swing.ImageIcon;
import org.codebuilder.ide.ui.widgets.*;
import javax.swing.*;
import java.io.File;

public final class IconFactory
    extends ImageIcon {

  /**
   * @todo Make the IconFactory load from .properties file
   */

  // The enumeration members
  public static IconFactory BuildProject = new IconFactory(
      "resources/images/BuildProject.gif");
  public static IconFactory ClosePanel = new IconFactory(
      "resources/images/ClosePanel.gif");
  public static IconFactory Clear = new IconFactory(
      "resources/images/Clear.gif");
  public static IconFactory Project = new IconFactory(
      "resources/images/Project.gif");
  public static IconFactory Template = new IconFactory(
      "resources/images/Template.gif");
  public static IconFactory TemplateGroup = new IconFactory(
      "resources/images/TemplateGroup.gif");
  public static IconFactory DocumentGroup = new IconFactory(
      "resources/images/DocumentGroup.gif");
  public static IconFactory Cut = new IconFactory(
      "resources/images/Cut.gif");
  public static IconFactory Copy = new IconFactory(
      "resources/images/Copy.gif");
  public static IconFactory Paste = new IconFactory(
      "resources/images/Paste.gif");
  public static IconFactory Delete = new IconFactory(
      "resources/images/Delete.gif");
  public static IconFactory Undo = new IconFactory(
      "resources/images/Undo.gif");
  public static IconFactory Redo = new IconFactory(
      "resources/images/Redo.gif");
  public static IconFactory SaveDocument = new IconFactory(
      "resources/images/SaveDocument.gif");
  public static IconFactory CloseDocument = new IconFactory(
      "resources/images/CloseDocument.gif");
  public static IconFactory EditDocument = new IconFactory(
      "resources/images/EditDocument.gif");
  public static IconFactory ValidateDocument = new IconFactory(
      "resources/images/ValidateDocument.gif");
  public static IconFactory ValidateOpenDocuments = new IconFactory(
      "resources/images/ValidateOpenDocuments.gif");
  public static IconFactory Logs = new IconFactory(
      "resources/images/Logs.gif");
  public static IconFactory NewTemplate = new IconFactory(
      "resources/images/NewTemplate.gif");
  public static IconFactory AddTemplate = new IconFactory(
      "resources/images/AddTemplate.gif");
  public static IconFactory RemoveTemplate = new IconFactory(
      "resources/images/RemoveTemplate.gif");
  public static IconFactory RenameTemplate = new IconFactory(
      "resources/images/RenameTemplate.gif");
  public static IconFactory ToggleStartupTemplate = new IconFactory(
      "resources/images/ToggleStartupTemplate.gif");
  public static IconFactory StartupTemplate = new IconFactory(
      "resources/images/StartupTemplate.gif");
  public static IconFactory CheckTemplateSyntax = new IconFactory(
      "resources/images/CheckTemplateSyntax.gif");
  public static IconFactory SaveAll = new IconFactory(
      "resources/images/SaveAll.gif");
  public static IconFactory Text = new IconFactory(
      "resources/images/Text.gif");
  public static IconFactory Model = new IconFactory(
      "resources/images/Model.gif");
  public static IconFactory Generated = new IconFactory(
      "resources/images/Generated.gif");
  public static IconFactory NewTemplateGroup = new IconFactory(
      "resources/images/NewTemplateGroup.gif");
  public static IconFactory NewProject = new IconFactory(
      "resources/images/NewProject.gif");
  public static IconFactory Debug = new IconFactory(
      "resources/images/Debug.gif");
  public static IconFactory Empty = new IconFactory(
      "resources/images/Empty.gif");
  public static IconFactory RemoveTemplateGroup = new IconFactory(
      "resources/images/RemoveTemplateGroup.gif");
  public static IconFactory NewModel = new IconFactory(
      "resources/images/NewModel.gif");
  public static IconFactory ModelGroup = new IconFactory(
      "resources/images/ModelGroup.gif");
  public static IconFactory NewModelGroup = new IconFactory(
      "resources/images/NewModelGroup.gif");
  public static IconFactory RemoveModelGroup = new IconFactory(
      "resources/images/RemoveModelGroup.gif");
  public static IconFactory AddModel = new IconFactory(
      "resources/images/AddModel.gif");
  public static IconFactory RemoveModel = new IconFactory(
      "resources/images/RemoveModel.gif");
  public static IconFactory ValidateTemplates = new IconFactory(
      "resources/images/ValidateTemplates.gif");
  public static IconFactory ValidateModels = new IconFactory(
      "resources/images/ValidateModels.gif");
  public static IconFactory Success = new IconFactory(
      "resources/images/Success.gif");
  public static IconFactory Information = new IconFactory(
      "resources/images/Information.gif");
  public static IconFactory Warning = new IconFactory(
      "resources/images/Warning.gif");
  public static IconFactory Error = new IconFactory(
      "resources/images/Error.gif");
  public static IconFactory FatalError = new IconFactory(
      "resources/images/FatalError.gif");
  public static IconFactory Refresh = new IconFactory(
      "resources/images/Refresh.gif");
  public static IconFactory Function = new IconFactory(
      "resources/images/Function.gif");
  public static IconFactory CodeBuilder = new IconFactory(
      "resources/images/CodeBuilder.gif");
  public static IconFactory CheckWellFormed = new IconFactory(
      "resources/images/CheckWellFormed.gif");
  public static IconFactory ProjectProperties = new IconFactory(
      "resources/images/ProjectProperties.gif");
  public static IconFactory AddProperty = new IconFactory(
      "resources/images/AddProperty.gif");
  public static IconFactory RemoveProperty = new IconFactory(
      "resources/images/RemoveProperty.gif");
  public static IconFactory Plugin = new IconFactory(
      "resources/images/Plugin.gif");
  public static IconFactory About = new IconFactory(
      "resources/images/CodeBuilder.gif");
  public static IconFactory CancelBuildProject = new IconFactory(
      "resources/images/CancelBuildProject.gif");
  public static IconFactory Todo = new IconFactory(
      "resources/images/Todo.gif");
  public static IconFactory OpenFolder = new IconFactory(
      "resources/images/OpenFolder.gif");
  public static IconFactory ClosedFolder = new IconFactory(
      "resources/images/ClosedFolder.gif");
  public static IconFactory HelpFile = new IconFactory(
      "resources/images/HelpFile.gif");
  public static IconFactory Bookmark = new IconFactory(
      "resources/images/Bookmark.gif");
  public static IconFactory Back = new IconFactory(
      "resources/images/Back.gif");
  public static IconFactory Forward = new IconFactory(
      "resources/images/Forward.gif");
  public static IconFactory Search = new IconFactory(
      "resources/images/Search.gif");
  public static IconFactory Help = new IconFactory(
      "resources/images/Help.gif");
  public static IconFactory XMLModel = new IconFactory(
      "resources/images/XMLModel.png");
  public static IconFactory UMLModel = new IconFactory(
      "resources/images/UMLModel.png");
  public static IconFactory W3CSchemaModel = new IconFactory(
      "resources/images/W3CSchema.png");
  public static IconFactory Tool = new IconFactory(
      "resources/images/Tool.gif");
  public static IconFactory Method = new IconFactory(
      "resources/images/Method.gif");
  public static IconFactory Clazz = new IconFactory(
      "resources/images/Class.gif");
  public static IconFactory PropertyEditor = new IconFactory(
      "resources/images/PropertyEditor.gif");
  public static IconFactory Editor = new IconFactory(
      "resources/images/Editor.gif");
  public static IconFactory Edit = new IconFactory(
      "resources/images/Edit.gif");
  public static IconFactory Introspection = new IconFactory(
      "resources/images/Introspection.gif");
  public static IconFactory TestClass = new IconFactory(
      "resources/images/TestClass.gif");
  public static IconFactory MoveUp = new IconFactory(
      "resources/images/MoveUp.gif");
  public static IconFactory MoveDown = new IconFactory(
      "resources/images/MoveDown.gif");
  public static IconFactory MOF = new IconFactory(
      "resources/images/MOF.png");
  //--------------------
  // Badge icons
  //--------------------

  private static IconFactory New = new IconFactory(
      "resources/images/New.gif");
  private static IconFactory AddExisting = new IconFactory(
      "resources/images/AddExisting.gif");
  private static IconFactory Remove = new IconFactory(
      "resources/images/Remove.gif");
  private static IconFactory Save = new IconFactory(
      "resources/images/Save.gif");
  private static IconFactory Open = new IconFactory(
      "resources/images/Open.gif");
  private static IconFactory Close = new IconFactory(
      "resources/images/Close.gif");

  private static IconFactory Rename = new IconFactory(
      "resources/images/Rename.gif");

  private static IconFactory Validate = new IconFactory(
      "resources/images/Validate.gif");

  private static IconFactory Properties = new IconFactory(
      "resources/images/Properties.gif");
  private static IconFactory Configure = new IconFactory(
      "resources/images/Configure.gif");

  private static IconFactory Run = new IconFactory(
      "resources/images/Run.gif");

  public static IconFactory Document = new IconFactory(
      "resources/images/Document.gif");

  /**
   * Private constructor to ensure that
   * instances can only be creaded from
   * within the context of the class
   * @param value String
   */
  private IconFactory(String filename) {
    super(filename);
  }

  private IconFactory(ImageIcon icon) {
    super(icon.getImage());
  }

  public Icon New() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.New,
                                        CompositeIcon.NORTH_EAST);
    return c;
  }

  public Icon AddExisting() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.AddExisting,
                                        CompositeIcon.NORTH_EAST);
    return c;
  }

  public Icon Remove() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Remove,
                                        CompositeIcon.NORTH_EAST);
    return c;
  }

  public Icon Save() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Save,
                                        CompositeIcon.SOUTH_EAST);
    return c;
  }

  public Icon Rename() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Rename,
                                        CompositeIcon.SOUTH_EAST);
    return c;
  }

  public Icon Open() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Open,
                                        CompositeIcon.SOUTH_EAST);
    return c;
  }

  public Icon Configure() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Configure,
                                        CompositeIcon.SOUTH_EAST);
    return c;
  }

  public Icon Close() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Close,
                                        CompositeIcon.NORTH_EAST);
    return c;
  }

  public Icon Validate() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Validate,
                                        CompositeIcon.SOUTH_EAST);
    return c;
  }

  public Icon Properties() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Properties,
                                        CompositeIcon.SOUTH_EAST);
    return c;
  }

  public Icon Run() {
    CompositeIcon c = new CompositeIcon(this, IconFactory.Run,
                                        CompositeIcon.SOUTH_EAST);
    return c;
  }

  public Icon as32x32() {
    return new ImageIcon(this.getImage().getScaledInstance(32, 32,
        this.getImage().SCALE_DEFAULT));
  }

  public static ImageIcon getIconFor(String name) {
    ImageIcon icon = new ImageIcon("resources/images/" + name + ".gif");
    if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
      icon = new ImageIcon("resources/images/" + name + ".png");
    }
    if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE &&
        name.endsWith("s")) {
      return IconFactory.getIconFor(name.substring(0, name.length() - 1));
    }
    if (icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
      icon = null;
    }
    return icon;
  }

  public static ImageIcon getIconFor(String name, String def){
    ImageIcon icon = getIconFor(name);
    if (icon == null || icon.getImageLoadStatus() != java.awt.MediaTracker.COMPLETE) {
      icon = getIconFor(def);
    }
    return icon;
  }

}
