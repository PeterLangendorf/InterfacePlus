package net.redfox.interfaceplus.gui.util;

import net.redfox.interfaceplus.task.Task;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyHandler implements KeyListener {
	private final Map<Integer, List<Task>> TYPE_EVENTS = new HashMap<>();
	private final Map<Integer, List<Task>> PRESSED_EVENTS = new HashMap<>();
	private final Map<Integer, List<Task>> RELEASED_EVENTS = new HashMap<>();

	public void addTypedKeyEvent(Task task, int keyCode) {
		updateList(TYPE_EVENTS, task, keyCode);
	}
	public void addPressedKeyEvent(Task task, int keyCode) {
		updateList(PRESSED_EVENTS, task, keyCode);
	}
	public void addReleasedKeyEvent(Task task, int keyCode) {
		updateList(RELEASED_EVENTS, task, keyCode);
	}

	private void updateList(Map<Integer, List<Task>> map, Task task, int keyCode) {
		if (map.containsKey(keyCode)) {
			map.get(keyCode).add(task);
		} else {
			map.put(keyCode, new ArrayList<>(List.of(task)));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (TYPE_EVENTS.containsKey(e.getKeyCode())) {
			for (Task task : TYPE_EVENTS.get(e.getKeyCode())) {
				task.execute();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (PRESSED_EVENTS.containsKey(e.getKeyCode())) {
			for (Task task : PRESSED_EVENTS.get(e.getKeyCode())) {
				task.execute();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (RELEASED_EVENTS.containsKey(e.getKeyCode())) {
			for (Task task : RELEASED_EVENTS.get(e.getKeyCode())) {
				task.execute();
			}
		}
	}
}
