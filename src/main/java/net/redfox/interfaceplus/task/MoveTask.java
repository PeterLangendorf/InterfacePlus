package net.redfox.interfaceplus.task;

import net.redfox.interfaceplus.math.Vector2;
import net.redfox.interfaceplus.math.Vector2Math;
import net.redfox.interfaceplus.object.util.Panel;

public class MoveTask implements Task {
  Panel panel;

  public MoveTask(Panel panel) {
    this.panel = panel;
  }
  @Override
  public void execute() {
    Vector2 newPos = Vector2Math.getIncrementsFromPoint(panel.getPosition(), new Vector2(200, 300), 3);
    panel.setPosition(panel.getPosition().getX()+newPos.getX(), panel.getPosition().getY()+newPos.getY());

    IO.println(Vector2Math.getIncrementsFromPoint(panel.getPosition(), new Vector2(200, 300), 3));
  }
}