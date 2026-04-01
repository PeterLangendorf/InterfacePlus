package net.redfox.interfaceplus.math;

import java.awt.Dimension;

public class Size2 {
	private int width;
	private int height;

	public Size2() {
		this(0, 0);
	}

	public Size2(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Dimension toDimension() {
		return new Dimension(width, height);
	}
}
