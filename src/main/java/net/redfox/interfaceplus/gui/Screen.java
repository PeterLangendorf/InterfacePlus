package net.redfox.interfaceplus.gui;

import net.redfox.interfaceplus.object.Renderable;

import java.util.ArrayList;
import java.util.List;

public class Screen {
	private final List<Renderable> renderables;

	private Screen(List<Renderable> renderables) {
		this.renderables = renderables;
	}

	public List<Renderable> getRenderables() {
		return renderables;
	}

	public void switchToScreen() {
		renderables.forEach(Renderable::show);
	}
	public void switchAwayFromScreen() {
		renderables.forEach(Renderable::hide);
	}
	public static Screen of(Renderable... renderables) {
		return new Screen(new ArrayList<>(List.of(renderables)));
	}
	public void addRenderableToScreen(Renderable renderable) {
		renderables.add(renderable);
	}
	public void removeRenderableFromScreen(Renderable renderable) {
		renderables.remove(renderable);
	}
}