package net.redfox.interfaceplus.gui;

import java.awt.*;
import javax.swing.*;
import net.redfox.interfaceplus.gui.display.Interface;
import net.redfox.interfaceplus.gui.util.KeyHandler;
import net.redfox.interfaceplus.gui.util.MouseHandler;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.Renderer;

public class DisplayPanel extends JPanel implements Runnable {
	public final Size2 size;
	private final int fps;

	private Thread thread;
	private final Interface panelInterface;
	private final WindowContext context;
	private final Renderer renderer;
	private Vector2 offset;

	public DisplayPanel(Interface i, int fps, Size2 size) {
		this.size = size;
		this.fps = fps;
		this.panelInterface = i;
		context = new WindowContext();
		this.setDoubleBuffered(true);
		this.addKeyListener(new KeyHandler(panelInterface::addTask));
		this.addMouseListener(new MouseHandler());
		this.setFocusable(true);
		this.renderer = new Renderer();
		this.offset = new Vector2(0, 0);
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setDrawOffset(Vector2 offset) {
		this.offset = offset;
	}

	public void startGameThread() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000.0 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;

		while (thread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;

			if (delta >= 1) {
				panelInterface.update();
				renderer.updateObjects(context);

				repaint();

				delta--;
			}
			if (timer >= 1000000000) {
				timer = 0;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		context.setG2(g2);
		context.setDimensions(size);
		context.setZoom(panelInterface.getZoom());
		context.setLocation(this.getLocationOnScreen());
		context.setDrawOffset(offset);
		if (context.isUsable())
			panelInterface.render(context);
		g2.dispose();
	}
}
