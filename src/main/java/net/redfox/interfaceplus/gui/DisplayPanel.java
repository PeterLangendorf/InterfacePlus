package net.redfox.interfaceplus.gui;

import java.awt.*;
import javax.swing.*;
import net.redfox.interfaceplus.gui.display.Interface;
import net.redfox.interfaceplus.gui.util.KeyHandler;
import net.redfox.interfaceplus.gui.util.MouseHandler;
import net.redfox.interfaceplus.gui.util.WindowContext;
import net.redfox.interfaceplus.math.Size2;
import net.redfox.interfaceplus.object.Renderer;

public class DisplayPanel extends JPanel implements Runnable {
	public final Size2 size;
	private final int fps;

	private Thread thread;
	private final Interface panelInterface;
	private final WindowContext context;
	public final Renderer renderer;

	public DisplayPanel(Interface i, int fps, Size2 size) {
		this.size = size;
		this.fps = fps;
		context = new WindowContext();
		this.setDoubleBuffered(true);
		this.addKeyListener(new KeyHandler());
		this.addMouseListener(new MouseHandler());
		this.setFocusable(true);
		this.panelInterface = i;
		this.renderer = new Renderer();
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
		context.setLocation(this.getLocationOnScreen());
		if (context.isUsable()) panelInterface.updateImages(context);
		g2.dispose();
	}
}
