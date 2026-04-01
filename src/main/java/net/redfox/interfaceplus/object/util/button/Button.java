package net.redfox.interfaceplus.object.util.button;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import net.redfox.interfaceplus.gui.util.MouseHandler;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.RenderableObject;
import net.redfox.interfaceplus.object.Renderer;
import net.redfox.interfaceplus.task.Task;

@SuppressWarnings("unused")
public class Button extends RenderableObject {
    private final ArrayList<Task> clickTaskList = new ArrayList<>();
    private final BufferedImage hoverImg, clickImg;
    private final BufferedImage defaultImg;
    private final boolean holdTrigger;

    private boolean buttonDown = false;

    protected Button(
            BufferedImage defaultImg,
            BufferedImage hoverImg,
            BufferedImage clickImg,
            Vector2 position,
            boolean holdTrigger) {
        super(new Size2(defaultImg.getWidth(), defaultImg.getHeight()), position);
        this.defaultImg = defaultImg;
        this.hoverImg = hoverImg;
        this.clickImg = clickImg;
        this.holdTrigger = holdTrigger;
    }

    protected Button(Size2 size, Vector2 position, boolean holdTrigger) {
        super(size, position);
        defaultImg = null;
        hoverImg = null;
        clickImg = null;
        this.holdTrigger = holdTrigger;
    }

    public void onClick(Task t) {
        clickTaskList.add(t);
    }

    private int getState(WindowContext context) {
        if (MouseHandler.overlaps(this, context)) {
            if (MouseHandler.isMouseDown()) {
                return 3;
            }
            return 2;
        }
        return 1;
    }


    @Override
    public String getName() {
        return "Button";
    }

    @SuppressWarnings("unchecked")
    public static class Builder extends RenderableObject.Builder {
        private BufferedImage hoverImg;
        private BufferedImage clickImg;
        private BufferedImage defaultImg;
        private boolean holdTrigger;

        public Builder(Renderer renderer) {
            super(renderer);
            hoverImg = null;
            clickImg = null;
            defaultImg = null;
            holdTrigger = false;
        }

        public Button.Builder hoverImage(BufferedImage hoverImg) {
            this.hoverImg = hoverImg;
            return this;
        }

        public Button.Builder clickImage(BufferedImage clickImg) {
            this.clickImg = clickImg;
            return this;
        }

        public Button.Builder defaultImage(BufferedImage defaultImg) {
            this.defaultImg = defaultImg;
            return this;
        }

        public Button.Builder holdTrigger(boolean holdTrigger) {
            this.holdTrigger = holdTrigger;
            return this;
        }

        @Override
        public Button.Builder position(Vector2 position) {
            super.setPosition(position);
            return this;
        }

        @Override
        public Button build() {
            return super.build(new Button(defaultImg, hoverImg, clickImg, position, holdTrigger));
        }
    }
	@Override
	public void update(WindowContext context) {
		switch (getState(context)) {
			case 1 -> BufferedImageManager.drawImage(context.getGraphics2D(), defaultImg, getPosition(), getSize());
			case 2 -> {
				BufferedImageManager.drawImage(context.getGraphics2D(), hoverImg, getPosition(), getSize());
				buttonDown = false;
			}
			case 3 -> {
				BufferedImageManager.drawImage(context.getGraphics2D(), clickImg, getPosition(), getSize());
				if (!buttonDown || holdTrigger) {
					buttonDown = true;
					for (Task t : clickTaskList) {
						t.execute();
					}
				}
			}
		}
	}
}
