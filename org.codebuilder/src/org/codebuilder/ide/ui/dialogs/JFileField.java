package org.codebuilder.ide.ui.dialogs;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Title: CodeBuilder</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: Dimitrios Kolovos</p>
 *
 * @author Dimitrios S. Kolovos
 * @version 1.0
 */

public class JFileField
    extends JPanel
    implements ActionListener {

  private JTextField textField = new JTextField();
  private JButton browseButton = new JButton("...");
  protected int fileSelectionMode;
  protected List changeListeners = new ArrayList();

  public JFileField() {
    init();
  }

  public JFileField(String textField, int fileSelectionMode) {
    this.textField.setText(textField);
    setFileSelectionMode(fileSelectionMode);
    init();
  }

  public JFileField(String textField, String browseButton,
                    int fileSelectionMode) {
    this.textField.setText(textField);
    this.browseButton.setText(browseButton);
    setFileSelectionMode(fileSelectionMode);
    init();
  }

  public JButton getBrowseButton(){
    return browseButton;
  }

  public JTextField getTextField(){
    return textField;
  }

  public int getFileSelectionMode() {
    return fileSelectionMode;
  }

  public void setFileSelectionMode(int fileSelectionMode) {
    if (fileSelectionMode != JFileChooser.FILES_AND_DIRECTORIES &&
        fileSelectionMode != JFileChooser.FILES_ONLY &&
        fileSelectionMode != JFileChooser.DIRECTORIES_ONLY
        ) {
      throw new IllegalArgumentException("fileSelectionMode should be one of the allowed values in the JFileChooser component");
    }
    else {
      this.fileSelectionMode = fileSelectionMode;
    }
  }

  private void init() {
    this.setLayout(new BorderLayout());
    this.add(textField, BorderLayout.CENTER);
    this.add(browseButton, BorderLayout.EAST);
    this.browseButton.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createEmptyBorder(0, 5, 0, 0), browseButton.getBorder()));
    browseButton.addActionListener(this);
    textField.addActionListener(this);
  }

  public String getText() {
    return textField.getText();
  }

  public void actionPerformed(ActionEvent e) {
    JFileChooser filechooser = new JFileChooser(textField.getText());
    filechooser.setFileSelectionMode(this.fileSelectionMode);
    int response = filechooser.showOpenDialog(null);

    if (response == JFileChooser.APPROVE_OPTION) {
      textField.setText(filechooser.getSelectedFile().getAbsolutePath());
    }
  }

  /*
     public void addChangeListener(ChangeListener listener){
    changeListeners.add(listener);
     }

     public void removeChangeListener(ChangeListener listener){
    changeListeners.remove(listener);
     }
   */
  /**
   * stateChanged
   *
   * @param e ChangeEvent
   */
  /*
     public void stateChanged(ChangeEvent e) {
    ListIterator li = changeListeners.listIterator();
    while (li.hasNext()){
      ChangeListener listener = (ChangeListener) li.next();
      listener.stateChanged(e);
    }
     }
   */
  /**
   * propertyChange
   *
   * @param evt PropertyChangeEvent
   */
  /*
     public void propertyChange(PropertyChangeEvent evt) {
     }
   */

}
