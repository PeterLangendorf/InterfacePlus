package net.redfox.interfaceplus.gui.display;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import net.redfox.interfaceplus.gui.DisplayPanel;
import net.redfox.interfaceplus.gui.Screen;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.Renderer;
import net.redfox.interfaceplus.task.Task;

public class Interface {
	private final ArrayList<Task> taskList = new ArrayList<>();
	private final JFrame window;
	private final DisplayPanel panel;
	private double zoom = 1;

	protected Interface(String title, Size2 size, Color background, int FPS) {
		window = new JFrame();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new DisplayPanel(this, FPS, size);
		window.add(panel);
		window.setTitle(title);
		panel.setBackground(background);
		panel.setPreferredSize(size.toDimension());
		window.pack();
		window.setLocationRelativeTo(null);

		window.setVisible(true);

		panel.startGameThread();
	}

	public void update() {
		for (Task t : taskList) {
			t.execute();
		}
	}

	public void render(WindowContext context) {
		panel.getRenderer().renderObjects(context);
	}

	public void addTask(Task t) {
		taskList.add(t);
	}

	public Renderer getRenderer() {
		return panel.getRenderer();
	}

	public void switchToScreen(Screen screen) {
		getRenderer().switchToScreen(screen);
	}
	public double getZoom() {
		return zoom;
	}
	public void setZoom(double zoom) {
		this.zoom = zoom;
	}
	public void setDrawOffset(Vector2 offset) {
		this.panel.setDrawOffset(offset);
	}

	public static class Builder {
		private String title;
		private Size2 size;
		private Color background;
		private int FPS;

		public Builder() {
			title = "Untitled";
			size = Size2.kDefaultResolution;
			background = Color.WHITE;
			FPS = 60;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder color(Color background) {
			this.background = background;
			return this;
		}

		public Builder size(Size2 size) {
			this.size = size;
			return this;
		}

		public Builder FPS(int FPS) {
			this.FPS = FPS;
			return this;
		}

		public Interface build() {
			return new Interface(title, size, background, FPS);
		}
	}
}
