package org.codebuilder.ide.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import org.codebuilder.ide.feedback.*;

public class SplashScreen extends JDialog implements FeedbackListener{
  BorderLayout borderLayout1 = new BorderLayout();
  private ImageIcon splashImage = null;
  private JLabel statusBar = new JLabel("Initializing CodeBuilder...");
  private boolean undecorated;
  private int width;
  private int height;

  public SplashScreen() {
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    finally{
    }
  }

  void jbInit() throws Exception {
    this.getContentPane().setLayout(borderLayout1);
    this.setUndecorated(true);
    splashImage = new ImageIcon("resources/images/Logo.png");
    this.getContentPane().add(new JLabel(splashImage), BorderLayout.CENTER);
    //this.getContentPane().add(statusBar, BorderLayout.SOUTH);
    this.setResizable(false);
    this.setTitle("About CodeBuilder...");
    this.width = splashImage.getIconWidth();
    this.height = splashImage.getIconHeight();// + statusBar.getHeight();
    this.setUndecorated(true);
    position();
    this.setVisible(true);
  }

  public static void main(String[] args){
    SplashScreen ss = new SplashScreen();
  }

  private void position(){
    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Rectangle bounds = env.getMaximumWindowBounds();
    int x = (int) (bounds.getWidth() - this.width) / 2;
    int y = (int) (bounds.getHeight() - this.height) / 2;
    this.setBounds(x,y,this.width,this.height);
  }

  public void close(){
    this.setVisible(false);
    this.dispose();
  }

  /**
   * feedbackReceived
   *
   * @param feedback Feedback
   */
  public void feedbackReceived(Feedback feedback) {
    statusBar.setText(feedback.getMessage());
  }

}
