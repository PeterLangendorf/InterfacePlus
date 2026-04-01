package net.redfox.interfaceplus.object.util.animated;

import java.awt.image.BufferedImage;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.RenderableObject;
import net.redfox.interfaceplus.object.Renderer;
import net.redfox.interfaceplus.util.BufferedImageManager;

@SuppressWarnings("unused")
public class AnimatedPanel extends RenderableObject {
	private final BufferedImage[] displayImages;
	private final int maxFrame;
	private final int frameRate;
	private int currentFrame;
	private int frameDelay;

	protected AnimatedPanel(BufferedImage[] displayImages, Vector2 position, int frameRate) {
		super(new Size2(displayImages[0].getWidth(), displayImages[0].getHeight()), position);
		this.displayImages = displayImages;
		maxFrame = this.displayImages.length;
		this.frameRate = frameRate;
	}

	@Override
	public void update(WindowContext context) {
		if (frameDelay == frameRate) {
			frameDelay = 0;
			if (currentFrame == maxFrame - 1) {
				currentFrame = 0;
			} else {
				currentFrame++;
			}
		} else {
			frameDelay++;
		}

		BufferedImageManager.drawImage(context.getGraphics2D(), displayImages[currentFrame], getPosition(), getSize());
	}

	@Override
	public String getName() {
		return "";
	}

	@SuppressWarnings("unchecked")
	public static class Builder extends RenderableObject.Builder {
		private BufferedImage[] displayImages;
		private int frameRate;

		public Builder(Renderer renderer) {
			super(renderer);
		}

		public AnimatedPanel.Builder frameRate(int frameRate) {
			this.frameRate = frameRate;
			return this;
		}

		public AnimatedPanel.Builder displayImages(BufferedImage[] displayImages) {
			this.displayImages = displayImages;
			return this;
		}

		@Override
		public AnimatedPanel.Builder position(Vector2 position) {
			super.setPosition(position);
			return this;
		}

		@Override
		public AnimatedPanel build() {
			return super.build(new AnimatedPanel(displayImages, position, frameRate));
		}
	}
}
