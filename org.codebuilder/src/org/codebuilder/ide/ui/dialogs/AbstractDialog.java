package org.codebuilder.ide.ui.dialogs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.border.*;
import org.codebuilder.ide.ui.dialogs.util.*;
import org.codebuilder.ide.ui.*;
import java.awt.event.*;

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

public abstract class AbstractDialog
    extends JDialog {

  protected JPanel headerPane = new JPanel(new BorderLayout());
  protected JPanel contentPane = new JPanel(new BorderLayout());
  protected JPanel buttonsPane = new JPanel(new GridLayout());
  protected JLabel titleLabel = new JLabel();
  protected JLabel descriptionLabel = new JLabel();
  protected JButton okButton = new JButton(new OKAction());
  protected JButton cancelButton = new JButton(new CancelAction());
  protected JLabel iconLabel = new JLabel();
  protected AbstractDialog thiz = this;
  private boolean cancelled;

  private Border componentBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);

  public static Border TIGHT_BORDER = BorderFactory.createCompoundBorder(
      BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(3, 3, 3, 3));
  public static Border SPACIOUS_BORDER = BorderFactory.createCompoundBorder(
      BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(20, 20, 20, 20));

  public AbstractDialog() {
    init();
  }

  protected abstract void ok();

  public boolean isCancelled() {
    return cancelled;
  }

  protected void cancel() {
    this.cancelled = true;
    this.dispose();
  }

  public void setTitle(String title) {
    this.titleLabel.setText(title);
    super.setTitle(title);
  }

  public String getTitle() {
    return super.getTitle();
  }

  public String getDescription() {
    return this.descriptionLabel.getText();
  }

  public void setDescription(String description) {
    this.descriptionLabel.setText(description);
  }

  public void setIcon(Icon icon) {
    iconLabel.setIcon(icon);
  }

  public Icon getIcon() {
    return iconLabel.getIcon();
  }

  private void init() {

    this.getContentPane().setLayout(new BorderLayout());


    buttonsPane.add(okButton);
    buttonsPane.add(cancelButton);

    JPanel buttonsLeftPane = new JPanel(new BorderLayout());
    buttonsLeftPane.add(buttonsPane, BorderLayout.EAST);
    this.getContentPane().add(buttonsLeftPane, BorderLayout.SOUTH);

    //--------------------
    // Format headerPane
    //--------------------

    JPanel headerLeftPane = new JPanel(new BorderLayout());
    headerLeftPane.add(headerPane, BorderLayout.CENTER);
    headerLeftPane.add(iconLabel, BorderLayout.WEST);
    headerLeftPane.setBackground(Color.WHITE);
    headerPane.add(titleLabel, BorderLayout.CENTER);
    headerPane.add(descriptionLabel, BorderLayout.SOUTH);
    headerPane.setOpaque(false);
    //--------------------
    // Format titleLabel
    //--------------------

    titleLabel.setFont(titleLabel.getFont().deriveFont(1));
    titleLabel.setBorder(componentBorder);

    //--------------------
    // Format descriptionLabel
    //--------------------

    descriptionLabel.setBorder(componentBorder);

    //--------------------
    // Format iconLabel
    //--------------------

    iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    //--------------------
    // Format buttonsPane
    //--------------------
    okButton.setBorder(BorderFactory.createCompoundBorder(componentBorder,
        okButton.getBorder()));
    cancelButton.setBorder(BorderFactory.createCompoundBorder(componentBorder,
        cancelButton.getBorder()));

    //--------------------
    // Format contentPane
    //--------------------

    contentPane.setLayout(new GridLayout(12, 1));
    contentPane.setBorder(AbstractDialog.SPACIOUS_BORDER);

    //--------------------
    // Add the panels
    //--------------------

    this.getContentPane().add(headerLeftPane, BorderLayout.NORTH);
    this.getContentPane().add(contentPane, BorderLayout.CENTER);

  }

  public void showDialog() {
    showDialog(IDE.getInstance());
  }

  public void showDialog(Container parent){
    if (parent != null){
      DialogUtilities.positionInCenter(parent, this, 400, 500);
      this.setModal(true);
    }
    else{
      this.setBounds(200, 200, 500, 400);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    this.setResizable(false);
    this.setVisible(true);
  }

  protected void showValidationError(String message) {
    JOptionPane.showMessageDialog(this, message, "Error",
                                  JOptionPane.ERROR_MESSAGE);
  }

  protected JTextField createTextField(String contents) {
    JTextField textfield = new JTextField(contents);
    //textfield.setPreferredSize(new Dimension(200,100));
    //textfield.setMaximumSize(new Dimension(200,100));
    return textfield;
  }

  class OKAction
      extends AbstractAction {

    public OKAction() {
      this.putValue(AbstractAction.NAME, "OK");
    }

    public void actionPerformed(ActionEvent e) {
      ok();
    }

  }

  class CancelAction
      extends AbstractAction {

    public CancelAction() {
      this.putValue(AbstractAction.NAME, "Cancel");
    }

    public void actionPerformed(ActionEvent e) {
      cancel();
    }

  }
}
