package net.redfox.interfaceplus.gui.util;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.object.RenderableObject;

public class MouseHandler implements MouseListener {
	private static boolean mouseDown = false;

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static boolean overlaps(RenderableObject object, WindowContext context) {
		Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
		Vector2 windowLocation = context.getLocation();
		mouseLocation.x -= (int) windowLocation.getX();
		mouseLocation.y -= (int) windowLocation.getY();
		return object.getX() * context.getZoom() <= mouseLocation.x
				&& object.getX() * context.getZoom()
						+ object.getSize().getWidth() * context.getZoom() >= mouseLocation.x
				&& object.getY() * context.getZoom() <= mouseLocation.y && object.getY() * context.getZoom()
						+ object.getSize().getHeight() * context.getZoom() >= mouseLocation.y;
	}

	public static boolean isMouseDown() {
		return mouseDown;
	}
}
