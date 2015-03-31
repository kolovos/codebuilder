package org.codebuilder.ide.ui.dialogs.document;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import org.codebuilder.common.file.*;
import org.codebuilder.ide.feedback.*;
import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.*;
import org.codebuilder.ide.ui.dialogs.*;
import org.codebuilder.project.*;

/**
 * <p>Title: CodeBuilder</p>
 *
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: Dimitrios Kolovos</p>
 *
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */

public class NewDocumentDialog
    extends AbstractDialog{

  JLabel nameLabel = new JLabel("Name");
  JLabel pathLabel = new JLabel("Store in directory");
  JLabel documentTypesLabel = new JLabel("Type");
  JTextField nameField = new JTextField("untitled");
  JFileField pathField = new JFileField(ProjectManager.getInstance().getCurrentProject().getBasePath(), JFileChooser.DIRECTORIES_ONLY);
  JList documentTypes = new JList();
  //JLabel descriptionLabel = new JLabel("Description: ");

  public NewDocumentDialog() {
    super();
    this.setTitle("New document");
    this.setDescription(
        "Provide the details for the new document");

    //--------------------
    // Add the components in the contentPane
    //--------------------

    documentTypes.setModel(new DocumentTypesComboBoxModel());
    documentTypes.setCellRenderer(new DocumentTypesListCellRenderer());
    //documentTypes.getSelectionModel().addListSelectionListener(new DocumentTypesListSelectionListener());
    documentTypes.setSelectedIndex(0);

    contentPane.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel(new BorderLayout());
    documentTypesLabel.setBorder(new EmptyBorder(0,0,4,0));
    //descriptionLabel.setBorder(BorderFactory.createCompoundBorder(new JList().getBorder(), new EmptyBorder(4,4,4,0)));
    //descriptionLabel.setOpaque(true);
    //descriptionLabel.setBackground(SystemColor.info);

    topPanel.add(documentTypesLabel, BorderLayout.NORTH);
    topPanel.add(new JScrollPane(documentTypes), BorderLayout.CENTER);
    //topPanel.add(descriptionLabel, BorderLayout.SOUTH);
    contentPane.add(topPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new GridLayout(4,1));
    bottomPanel.add(nameLabel);
    bottomPanel.add(nameField);
    bottomPanel.add(pathLabel);
    bottomPanel.add(pathField);

    contentPane.add(bottomPanel, BorderLayout.SOUTH);
  }

  protected void ok() {
    //------------------
    // Since the project
    // is not null, proceed
    // with the action
    //------------------

    Document document = null;
    try {
      document = (Document) documentTypes.getSelectedValue().getClass().
          newInstance();
    }
    catch (Exception ex) {
      FeedbackManager.getInstance().reportException(ex);
    }

    //if (!Template.isValidName(nameField.getText())) {
    //  showValidationError(nameField.getText() + " is not a valid model name");
    //  return;
    //}

    String filename = pathField.getText() + "\\" + nameField.getText() + "." + document.getSuffix();

    // ---------------------
    // The user has chosen a valid
    // name and filename.
    // Therefore add a template to the
    // project
    // ---------------------

    if (!(new File(filename).exists())){
    BatchWriter writer = new BatchWriter();
    try {
      writer.writeAll("", new File(filename));
    }
    catch (IOException ex) {
      showValidationError("The model could not be created.\n Check that the directory exists and is writeable");
      return;
    }
    }

    // ---------------------
    // Now the document file
    // has been created as well.
    // Add the document to the project
    // ---------------------

    document.setName(nameField.getText());
    document.setPath(filename);

    // ---------------------
    // Add the new document
    // to the right document group
    // ---------------------

    IDE ide = IDE.getInstance();
    Project project = ProjectManager.getInstance().getCurrentProject();

    ProjectPanel projectPanel = ide.getProjectPanel();
    Document parent = projectPanel.getSelectedDocument();
    if (parent == null) parent = project;

    if (!(parent instanceof DocumentGroup)){
      parent = parent.getParent();
    }

    ((DocumentGroup) parent).addDocument(document);

    ProjectManager.getInstance().notifyProjectChangeListeners();

    // ---------------------
    // Finally open the template
    // for editing
    // ---------------------

    //EditDocumentAction.getInstance().perform(template);

    this.dispose();
  }

  class DocumentTypesComboBoxModel implements ListModel{

    java.util.List newDocuments = DocumentFactory.getInstance().createDocuments();

    /**
     * getSize
     *
     * @return int
     */
    public int getSize() {
      return newDocuments.size();
    }

    /**
     * getElementAt
     *
     * @param index int
     * @return Object
     */
    public Object getElementAt(int index) {
      return newDocuments.get(index);
    }

    /**
     * addListDataListener
     *
     * @param l ListDataListener
     */
    public void addListDataListener(ListDataListener l) {
    }

    /**
     * removeListDataListener
     *
     * @param l ListDataListener
     */
    public void removeListDataListener(ListDataListener l) {
    }

  }

  class DocumentTypesListSelectionListener implements ListSelectionListener{
    /**
     * valueChanged
     *
     * @param e ListSelectionEvent
     */
    public void valueChanged(ListSelectionEvent e) {
      //pathField.setVisible(!(documentTypes.getSelectedValue() instanceof DocumentGroup));
      //pathLabel.setVisible(!(documentTypes.getSelectedValue() instanceof DocumentGroup));
      //descriptionLabel.setText(((Document) documentTypes.getSelectedValue()).getDescription());
    }

  }
}
