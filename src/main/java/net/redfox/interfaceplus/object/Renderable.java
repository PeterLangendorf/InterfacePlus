package net.redfox.interfaceplus.object;

import net.redfox.interfaceplus.gui.util.WindowContext;

public interface Renderable {
	void update(WindowContext context);
	void preRender(WindowContext context);
	void render(WindowContext context);
	void postRender(WindowContext context);
	void hide();
	void show();
	void toggleVisibility();
	boolean isHidden();
	void destroy();
}
