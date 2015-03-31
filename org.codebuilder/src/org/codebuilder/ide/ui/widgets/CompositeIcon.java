package org.codebuilder.ide.ui.widgets;

import javax.swing.Icon;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.SwingConstants;
import javax.swing.*;

/**
 * Creates a composite icon by combing two icons, the base and the overlay.  The base
 * must be bigger than the overlay or the overlay will be rejected!  The base icon
 * must be set first.  The position of the overlay is determined by the
 * SwingConstants N, NE, NW, etc.
 *
 * @author Eric Schultz
 * @version CompositeIcon 1.00 Aug 15, 2001
 */

public class CompositeIcon extends ImageIcon implements Icon, SwingConstants{
    /**
     * The version of the class
     */
    public static final String version = "CompositeIcon 1.00 Aug 15, 2001";
    /**
     * The icon that serves as the base image
     */
    protected Icon base;
    /**
     * The icon that is drawn over the base
     */
    protected Icon overlay;
    /**
     * The position the user requests the overlay be put in
     */
    protected int position;
    /**
     * The overlay's X position relative to the base
     */
    protected int ox;
    /**
     * The overley's Y position relative to the base
     */
    protected int oy;

    /**
     * Creates a new composite icon from two existing icons and places the overlay in the
     * specified position
     * @param base the base icon
     * @param overlay the icon to draw on top of the base
     * @param position the location of the overlay relative to the base
     */
    public CompositeIcon(Icon base, Icon overlay, int position) {
        setBaseIcon(base);
        setOverlayIcon(overlay, position);
    }

    /**
     * Sets the base icon
     * @param base base icon, does nothing if this parameter is null
     */
    public void setBaseIcon(Icon base) {
        if (base != null)
            this.base = base;
    }

    /**
     * Returns the base icon
     * @returns the base icon
     */
    public Icon getBaseIcon() {
        return this.base;
    }

    /**
     * Sets the overlay icon and calculates the new position
     * @param overlay the icon to overlay, does nothing if the base and overlay are null
     */
    public void setOverlayIcon(Icon overlay) {
        if (base != null && overlay != null) {
            if (overlay.getIconHeight() <= base.getIconHeight() && overlay.getIconWidth() <= base.getIconWidth()) {
                this.overlay = overlay;
                calcPosition();
            }
        }
    }

    /**
     * Sets the new overlay icon and the new position
     * @param overlay the new icon to overlay
     * @param position the new position for the overlay
     */
    public void setOverlayIcon(Icon overlay, int position) {
        setOverlayIcon(overlay);
        setPosition(position);
    }

    /**
     * Returns the overlay icon
     * @returns the overlay icon
     */
    public Icon getOverlayIcon() {
        return this.overlay;
    }

    /**
     * Sets the new position.  Base on the SwingConstants cardinal constants (NORTH, etc.)
     * @param the position of the overlay (1 of 9).
     */
    public void setPosition(int position) {
        this.position = position;
        calcPosition();
    }

    /**
     * Returns the position of the overlay
     * @returns the position of the overlay
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Calculates the position of the overlay relative to the base icon
     */
    protected void calcPosition() {
        if (base != null && overlay != null) {
            switch (position) {
                case NORTH_EAST:
                    ox = base.getIconWidth() - overlay.getIconWidth();
                    oy = 0;
                    break;
                case NORTH:
                    ox =  (base.getIconWidth() - overlay.getIconWidth()) / 2;
                    oy = 0;
                    break;
                case NORTH_WEST:
                    ox = 0;
                    oy = 0;
                    break;
                case WEST:
                    ox = 0;
                    oy = (base.getIconHeight() - overlay.getIconHeight()) / 2;
                    break;
                case CENTER:
                    ox =  (base.getIconWidth() - overlay.getIconWidth()) / 2;
                    oy = (base.getIconHeight() - overlay.getIconHeight()) / 2;
                    break;
                case EAST:
                    ox = base.getIconWidth() - overlay.getIconWidth();
                    oy = (base.getIconHeight() - overlay.getIconHeight()) / 2;
                    break;
                case SOUTH_EAST:
                    ox = base.getIconWidth() - overlay.getIconWidth();
                    oy = base.getIconHeight() - overlay.getIconHeight();
                    break;
                case SOUTH:
                    ox =  (base.getIconWidth() - overlay.getIconWidth()) / 2;
                    oy = base.getIconHeight() - overlay.getIconHeight();
                    break;
                case SOUTH_WEST:
                    ox = 0;
                    oy = base.getIconHeight() - overlay.getIconHeight();
                    break;
                default :
                    ox =  (base.getIconWidth() - overlay.getIconWidth()) / 2;
                    oy = (base.getIconHeight() - overlay.getIconHeight()) / 2;
                    break;
            }
        }
    }

    /**
     * Returns the height of the base icon
     * @returns the height of the icon
     */
    public int getIconHeight() {
        return base.getIconHeight();
    }

    /**
     * Returns the width of the icon
     * @returns the width of the base icon
     */
    public int getIconWidth() {
        return base.getIconWidth();
    }

    /**
     * Paints the two icons, calling each of their paintIcon methods
     * @param c the GUI component inw which to paint this icon
     * @param g the graphics object which contains the icon
     * @param x the x location of the base icon
     * @param y the y location of the base icon
     */
    public void paintIcon(Component c, Graphics g, int x, int y) {
        base.paintIcon(c, g, x, y);
        overlay.paintIcon(c, g, x+ox, y+oy);
    }
}
