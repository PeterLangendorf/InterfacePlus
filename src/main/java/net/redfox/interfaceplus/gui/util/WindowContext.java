package net.redfox.interfaceplus.gui.util;

import java.awt.*;

public class WindowContext {
    private Graphics2D g2;
    private Dimension dimensions;
    private final Point location;

	public WindowContext() {
		this(0, 0);
	}

    public WindowContext(Graphics2D g2, Point location, Dimension dimensions) {
        this.g2 = g2;
        this.location = location;
        this.dimensions = dimensions;
    }

    public WindowContext(Graphics2D g2, int x, int y, Dimension dimensions) {
        this(g2, new Point(x, y), dimensions);
    }

    public WindowContext(Point location) {
        this.g2 = null;
        this.location = location;
        this.dimensions = null;
    }

    public WindowContext(int x, int y) {
        this(new Point(x, y));
    }

    public boolean isUsable() {
        return g2 != null && dimensions != null;
    }

    public Graphics2D getGraphics2D() {
        return g2;
    }

    public Point getLocation() {
        return location;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }

    public void setLocation(Point location) {
        this.location.setLocation(location.x, location.y);
    }

    public void setLocation(int x, int y) {
        this.location.setLocation(x, y);
    }
}
