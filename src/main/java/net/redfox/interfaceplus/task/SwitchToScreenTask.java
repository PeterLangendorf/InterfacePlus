package net.redfox.interfaceplus.task;

import net.redfox.interfaceplus.gui.Screen;

import java.util.function.Consumer;

public class SwitchToScreenTask implements Task {
	private Screen screen;
	private Consumer<Screen> screenConsumer;

	public SwitchToScreenTask(Consumer<Screen> switchToScreen, Screen screen) {
		this.screen = screen;
		this.screenConsumer = switchToScreen;
	}

	@Override
	public void execute() {
		screenConsumer.accept(screen);
	}
}