package net.redfox.interfaceplus.object;

import net.redfox.interfaceplus.gui.Screen;
import net.redfox.interfaceplus.gui.util.WindowContext;

public class Renderer {
	private Screen currentScreen;

	public void updateObjects(WindowContext context) {
		if (currentScreen == null)
			return;
		for (Renderable r : currentScreen.getRenderables()) {
			r.update(context);
		}
	}

	public void renderObjects(WindowContext context) {
		if (currentScreen == null)
			return;
		for (Renderable r : currentScreen.getRenderables()) {
			r.preRender(context);
		}
		for (Renderable r : currentScreen.getRenderables()) {
			r.render(context);
		}
		for (Renderable r : currentScreen.getRenderables()) {
			r.postRender(context);
		}
	}

	public void switchToScreen(Screen screen) {
		if (currentScreen != null)
			currentScreen.switchAwayFromScreen();
		currentScreen = screen;
		currentScreen.switchToScreen();
	}
}
