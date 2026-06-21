package net.redfox.interfaceplus.object.util;

import java.awt.image.BufferedImage;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.RenderableImage;

@SuppressWarnings("unused")
public class Panel extends RenderableImage {
	private double leftFace;
	private double rightFace;
	private double topFace;
	private double bottomFace;

	protected Panel(BufferedImage displayImage, Vector2 position) {
		super(displayImage, position);
		resetHorizontalFaces();
		resetVerticalFaces();
	}

	private void resetHorizontalFaces() {
		leftFace = getX();
		rightFace = getX() + getSize().getWidth();
	}

	private void resetVerticalFaces() {
		topFace = getY();
		bottomFace = getY() + getSize().getHeight();
	}

	@Override
	public void setX(double x) {
		super.setX(x);
		resetHorizontalFaces();
	}

	@Override
	public void setY(double y) {
		super.setY(y);
		resetVerticalFaces();
	}

	@Override
	public void setPosition(double x, double y) {
		super.setPosition(x, y);
		resetHorizontalFaces();
		resetVerticalFaces();
	}

	public double getLeftFace() {
		return leftFace;
	}

	public double getRightFace() {
		return rightFace;
	}

	public double getTopFace() {
		return topFace;
	}

	public double getBottomFace() {
		return bottomFace;
	}

	@Override
	public String getName() {
		return "Panel";
	}

	@SuppressWarnings("unchecked")
	public static class Builder extends RenderableImage.Builder {
		public Builder() {
			super();
		}

		@Override
		public Panel.Builder position(Vector2 position) {
			super.setPosition(position);
			return this;
		}

		@Override
		public Panel.Builder displayImage(BufferedImage displayImage) {
			super.setDisplayImage(displayImage);
			return this;
		}

		@Override
		public Panel build() {
			return new Panel(displayImage, position);
		}
	}
}
