package net.redfox.interfaceplus.object;

import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;

@SuppressWarnings("unused")
public abstract class RenderableObject implements Renderable {
	private final Vector2 position;
	private final Size2 size;

	protected RenderableObject(Size2 size, Vector2 position) {
		this.position = position;
		this.size = size;
	}

	public void setX(double x) {
		this.position.setX(x);
	}

	public void setY(double y) {
		this.position.setY(y);
	}

	public void setPosition(double x, double y) {
		position.setX(x);
		position.setY(y);
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}

	public Vector2 getPosition() {
		return position;
	}

	public Size2 getSize() {
		return size;
	}

	public abstract void update(WindowContext context);

	public abstract String getName();

	@Override
	public void render(WindowContext context) {
		update(context);
	}

	@Override
	public String toString() {
		return getName() + this.hashCode();
	}

	protected abstract static class Builder {
		protected final Vector2 position;
		private final Renderer renderer;

		public Builder(Renderer renderer) {
			this.renderer = renderer;
			position = new Vector2(0, 0);
		}

		protected final void setPosition(Vector2 position) {
			this.position.setX(position.getX());
			this.position.setY(position.getY());
		}

		public <T extends Renderable> T build(T object) {
			renderer.register(object);
			return object;
		}

		public abstract <T extends Renderable> T build();

		public abstract <K extends RenderableObject.Builder> K position(Vector2 position);
	}
}
