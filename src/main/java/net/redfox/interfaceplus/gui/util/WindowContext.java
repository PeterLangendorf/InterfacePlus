package net.redfox.interfaceplus.gui.util;

import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;

import java.awt.*;

public class WindowContext {
	private Graphics2D g2;
	private Size2 dimensions;
	private double zoom;
	private final Vector2 location;
	private Vector2 drawOffset;

	public WindowContext() {
		this(0, 0);
	}

	public WindowContext(Graphics2D g2, Vector2 location, Size2 dimensions, double zoom) {
		this.g2 = g2;
		this.location = location;
		this.dimensions = dimensions;
		this.zoom = zoom;
	}

	public WindowContext(Graphics2D g2, int x, int y, Size2 dimensions) {
		this(g2, new Vector2(x, y), dimensions, 1);
	}

	public WindowContext(Vector2 location) {
		this(null, location, null, 1);
	}

	public WindowContext(int x, int y) {
		this(new Vector2(x, y));
	}

	public boolean isUsable() {
		return g2 != null && dimensions != null;
	}

	public Graphics2D getGraphics2D() {
		return g2;
	}

	public Vector2 getLocation() {
		return location;
	}

	public Size2 getDimensions() {
		return dimensions;
	}

	public void setDimensions(Size2 dimensions) {
		this.dimensions = dimensions;
	}

	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}

	public void setLocation(Vector2 newLocation) {
		setLocation(newLocation.getX(), newLocation.getY());
	}
	public void setLocation(Point newLocation) {
		setLocation(newLocation.getX(), newLocation.getY());
	}
	public void setLocation(double x, double y) {
		location.setX(x);
		location.setY(y);
	}
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	public double getZoom() {
		return zoom;
	}
	public void setDrawOffset(Vector2 drawOffset) {
		this.drawOffset = drawOffset;
	}
	public Vector2 getDrawOffset() {
		return drawOffset;
	}
}
