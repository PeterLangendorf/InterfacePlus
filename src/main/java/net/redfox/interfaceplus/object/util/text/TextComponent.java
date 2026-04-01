package net.redfox.interfaceplus.object.util.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.RenderableObject;
import net.redfox.interfaceplus.object.Renderer;

@SuppressWarnings("unused")
public class TextComponent extends RenderableObject {
    private Font font;
    private Color color;
    private String contents;

    protected TextComponent(
            String contents,
            Vector2 position,
            Size2 size,
            Color color,
            String font,
            int fontSize,
            int style) {
        super(size, position);
        this.contents = contents;
        this.color = color;
        this.font = new Font(font, style, fontSize);
    }

    public void setContents(String text) {
        contents = text;
    }

    public void setFont(String name) {
        font = new Font(name, font.getStyle(), font.getSize());
    }

    public void setSize(int size) {
        font = new Font(font.getName(), font.getStyle(), size);
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public String getContents() {
        return contents;
    }

    @Override
    public String getName() {
        return "TextComponent";
    }

    @SuppressWarnings("unchecked")
    public static class Builder extends RenderableObject.Builder {
        private String font;
        private int style;
        private Color color;
        private String contents;
        private int fontSize;
        private Size2 size;

        public Builder(Renderer renderer) {
            super(renderer);
        }

        public TextComponent.Builder fontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public TextComponent.Builder size(Size2 size) {
            this.size = size;
            return this;
        }

        public TextComponent.Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public TextComponent.Builder color(Color color) {
            this.color = color;
            return this;
        }

        public TextComponent.Builder style(int style) {
            this.style = style;
            return this;
        }

        public TextComponent.Builder font(String font) {
            this.font = font;
            return this;
        }

        @Override
        public TextComponent.Builder position(Vector2 position) {
            super.setPosition(position);
            return this;
        }

        @Override
        public TextComponent build() {
            return build(new TextComponent(contents, position, size, color, font, fontSize, style));
        }
    }
	@Override
	public void update(WindowContext context) {
		Graphics2D g = context.getGraphics2D();
		g.setFont(font);
		g.setColor(color);
		g.drawString(contents, (int) (this.getX() + 0.5), (int) (this.getY() + 0.5));
	}
}
