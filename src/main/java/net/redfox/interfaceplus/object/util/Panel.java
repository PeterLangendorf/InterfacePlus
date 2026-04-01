package net.redfox.interfaceplus.object.util;

import java.awt.image.BufferedImage;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.RenderableImage;
import net.redfox.interfaceplus.object.Renderer;

@SuppressWarnings("unused")
public class Panel extends RenderableImage {
	protected Panel(BufferedImage displayImage, Vector2 position) {
		super(displayImage, position);
	}

	@Override
	public String getName() {
		return "Panel";
	}

	@SuppressWarnings("unchecked")
	public static class Builder extends RenderableImage.Builder {
		public Builder(Renderer renderer) {
			super(renderer);
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
			return super.build(new Panel(displayImage, position));
		}
	}
}
