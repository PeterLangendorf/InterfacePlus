package net.redfox.interfaceplus.object;

import java.awt.image.BufferedImage;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;

@SuppressWarnings("unused")
public abstract class RenderableImage extends RenderableObject {
    protected BufferedImage displayImage;

    protected RenderableImage(BufferedImage displayImage, Vector2 position) {
        super(new Size2(displayImage.getWidth(), displayImage.getHeight()), position);
        this.displayImage = displayImage;
    }

	@Override
	public void update(WindowContext context) {
		BufferedImageManager.drawImage(context.getGraphics2D(), displayImage, getPosition(), getSize());
	}

    public abstract static class Builder extends RenderableObject.Builder {
        protected BufferedImage displayImage;

        public Builder(Renderer renderer) {
            super(renderer);
            displayImage = null;
        }

        protected final void setDisplayImage(BufferedImage displayImage) {
            this.displayImage = displayImage;
        }

        public abstract <K extends RenderableImage.Builder> K displayImage(
                BufferedImage displayImage);
    }
}
