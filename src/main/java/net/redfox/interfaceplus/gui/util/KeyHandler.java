package net.redfox.interfaceplus.gui.util;

import net.redfox.interfaceplus.task.Task;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class KeyHandler implements KeyListener {
	private static final Map<Integer, List<Task>> TYPE_EVENTS = new HashMap<>();
	private static final Map<Integer, List<Task>> PRESSED_EVENTS = new HashMap<>();
	private static final Map<Integer, List<Task>> RELEASED_EVENTS = new HashMap<>();

  private static final Set<Integer> PRESSED_KEYS = new HashSet<>();

  public KeyHandler(Consumer<Task> updateTask) {
    updateTask.accept(() -> {
      for (int i : PRESSED_KEYS) {
        if (PRESSED_EVENTS.containsKey(i)) {
          for (Task t : PRESSED_EVENTS.get(i)) {
            t.execute();
          }
        }
      }
    });
  }

  /**
   * Adds a task to run when the specified key is typed. This will fire once every time the user presses down on the key.
   * @param task The task to run.
   * @param keyCode The key that must be typed to run the task, as specified by the static keycodes in {@link KeyEvent}.
   */
	public static void addTypedKeyEvent(Task task, int keyCode) {
		updateList(TYPE_EVENTS, task, keyCode);
	}
  /**
   * Adds a task to run when the specified key is pressed. This will fire once per frame while the key is held down.
   * @param task The task to run.
   * @param keyCode The key that must be pressed to run the task, as specified by the static keycodes in {@link KeyEvent}.
   */
	public static void addPressedKeyEvent(Task task, int keyCode) {
		updateList(PRESSED_EVENTS, task, keyCode);
	}
  /**
   * Adds a task to run when the specified key is released. This will fire once when the key is released.
   * @param task The task to run.
   * @param keyCode The key that must be released to run the task, as specified by the static keycodes in {@link KeyEvent}.
   */
	public static void addReleasedKeyEvent(Task task, int keyCode) {
		updateList(RELEASED_EVENTS, task, keyCode);
	}

	private static void updateList(Map<Integer, List<Task>> map, Task task, int keyCode) {
		if (map.containsKey(keyCode)) {
			map.get(keyCode).add(task);
		} else {
			map.put(keyCode, new ArrayList<>(List.of(task)));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
    PRESSED_KEYS.add(e.getKeyCode());

		if (TYPE_EVENTS.containsKey(e.getKeyCode())) {
			for (Task task : TYPE_EVENTS.get(e.getKeyCode())) {
				task.execute();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
    PRESSED_KEYS.remove(e.getKeyCode());

		if (RELEASED_EVENTS.containsKey(e.getKeyCode())) {
			for (Task task : RELEASED_EVENTS.get(e.getKeyCode())) {
				task.execute();
			}
		}
	}
}
