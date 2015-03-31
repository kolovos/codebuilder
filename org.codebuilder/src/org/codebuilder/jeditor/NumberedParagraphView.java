package org.codebuilder.jeditor;

import java.awt.*;
import javax.swing.text.*;

public class NumberedParagraphView
    extends ParagraphView {
  public static short NUMBERS_WIDTH = 25;

  public NumberedParagraphView(Element e) {
    super(e);
    short top = 0;
    short left = 0;
    short bottom = 0;
    short right = 0;
    this.setInsets(top, left, bottom, right);
  }

  protected void setInsets(short top, short left, short bottom, short right) {
    super.setInsets(top, (short) (left + NUMBERS_WIDTH), bottom, right);
  }

  public void paintChild(Graphics g, Rectangle r, int n) {
    super.paintChild(g, r, n);
    int previousLineCount = getPreviousLineCount();
    int numberX = r.x - getLeftInset();
    int numberY = r.y + r.height - 5;
    g.setColor(new Color(153, 154, 204));
    g.setFont(new Font("Courier New", 0, 12));
    g.drawString(Integer.toString(previousLineCount + n + 1),
                 numberX, numberY);
  }

  public int getPreviousLineCount() {
    int lineCount = 0;
    View parent = this.getParent();
    int count = parent.getViewCount();
    for (int i = 0; i < count; i++) {
      if (parent.getView(i) == this) {
        break;
      }
      else {
        lineCount += parent.getView(i).getViewCount();
      }
    }
    return lineCount;
  }
}
