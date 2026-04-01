package net.redfox.interfaceplus.object.util.animated;

import java.awt.image.BufferedImage;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.RenderableObject;
import net.redfox.interfaceplus.object.Renderer;
import net.redfox.interfaceplus.util.BufferedImageManager;

@SuppressWarnings("unused")
public class NodePanel extends RenderableObject {
	private final BufferedImage[] displayImages;
	private int currentFrame = 0;
	private final boolean repeat;

	protected NodePanel(BufferedImage[] displayImages, Vector2 position, boolean repeat) {
		super(new Size2(displayImages[0].getWidth(), displayImages[0].getHeight()), position);
		this.displayImages = displayImages;
		this.repeat = repeat;
	}

	public void next() {
		if (currentFrame == displayImages.length - 1) {
			if (repeat) {
				currentFrame = 0;
			}
		} else {
			currentFrame++;
		}
	}

	@Override
	public void update(WindowContext context) {
		BufferedImageManager.drawImage(context.getGraphics2D(), displayImages[currentFrame], getPosition(), getSize());
	}

	@Override
	public String getName() {
		return "";
	}

	@SuppressWarnings("unchecked")
	public static class Builder extends RenderableObject.Builder {
		private BufferedImage[] displayImages;
		private boolean repeat;

		public Builder(Renderer renderer) {
			super(renderer);
			repeat = true;
			displayImages = new BufferedImage[]{};
		}

		public NodePanel.Builder repeat(boolean repeat) {
			this.repeat = repeat;
			return this;
		}

		@Override
		public NodePanel.Builder position(Vector2 position) {
			super.setPosition(position);
			return this;
		}

		public NodePanel.Builder displayImages(BufferedImage[] displayImages) {
			this.displayImages = displayImages;
			return this;
		}

		@Override
		public NodePanel build() {
			return super.build(new NodePanel(displayImages, position, repeat));
		}
	}
}
