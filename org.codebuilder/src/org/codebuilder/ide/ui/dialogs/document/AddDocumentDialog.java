package org.codebuilder.ide.ui.dialogs.document;

import javax.swing.*;

import org.codebuilder.ide.projectmanagement.*;
import org.codebuilder.ide.ui.*;
import org.codebuilder.ide.ui.dialogs.*;
import org.codebuilder.project.*;
import java.io.IOException;
import org.codebuilder.common.file.BatchWriter;
import java.io.File;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import org.codebuilder.ide.ui.dialogs.AbstractDialog;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.border.*;
import org.codebuilder.ide.feedback.*;

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

public class AddDocumentDialog
    extends AbstractDialog{

  JLabel nameLabel = new JLabel("Name");
  JLabel pathLabel = new JLabel("Path");
  JLabel documentTypesLabel = new JLabel("Type");
  JTextField nameField = new JTextField("untitled");
  JFileField pathField = new JFileField(ProjectManager.getInstance().getCurrentProject().getBasePath(), JFileChooser.FILES_ONLY);
  JList documentTypes = new JList();

  public AddDocumentDialog() {
    super();
    this.setTitle("Add Document");
    this.setDescription(
        "Provide the details for the document to be added");

    //--------------------
    // Add the components in the contentPane
    //--------------------
    pathField.getTextField().setEditable(false);
    documentTypes.setModel(new DocumentTypesComboBoxModel());
    documentTypes.setCellRenderer(new DocumentTypesListCellRenderer());
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

    if (!(new File(pathField.getText()).exists())){
      showValidationError("File " + pathField.getText() + " does not exist");
      return;
    }

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


    // ---------------------
    // Now the document file
    // has been created as well.
    // Add the document to the project
    // ---------------------

    document.setName(nameField.getText());
    document.setPath(pathField.getText());

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
