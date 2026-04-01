package net.redfox.interfaceplus.gui.util;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		Point windowLocation = context.getLocation();
		mouseLocation.x -= windowLocation.x;
		mouseLocation.y -= windowLocation.y;
		return object.getX() <= mouseLocation.x && object.getX() + object.getSize().getWidth() >= mouseLocation.x
				&& object.getY() <= mouseLocation.y && object.getY() + object.getSize().getHeight() >= mouseLocation.y;
	}

	public static boolean isMouseDown() {
		return mouseDown;
	}
}
