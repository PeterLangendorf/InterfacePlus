package net.redfox.interfaceplus.object;

import java.util.ArrayList;
import net.redfox.interfaceplus.gui.util.WindowContext;

public class Renderer {
	private final ArrayList<Renderable> objects;
	private final ArrayList<Renderable> renderableObjectQueue;

	public Renderer() {
		objects = new ArrayList<>();
		renderableObjectQueue = new ArrayList<>();
	}

	public void renderObjects(WindowContext context) {
		if (!renderableObjectQueue.isEmpty())
			updateRenderableObjectList();

		for (Renderable r : objects) {
			r.render(context);
		}
	}

	private void updateRenderableObjectList() {
		objects.addAll(renderableObjectQueue);
		renderableObjectQueue.clear();
	}

	public void register(Renderable object) {
		renderableObjectQueue.add(object);
	}
}
