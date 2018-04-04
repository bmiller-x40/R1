package org.upstart.r1.logic.actions;

import org.upstart.r1.Application;
import org.upstart.r1.audio.SoundManager;
import org.upstart.r1.audio.SoundsEnum;
import org.upstart.r1.logic.*;

import java.awt.event.ActionEvent;

public class MoveAction extends GameAction {
    private Direction direction;

    public MoveAction(Direction direction) {
        this.direction = direction;
    }

    public void doAction() {
        GameState gameState = GameState.getInstance();
        Position position = gameState.getPlayer().position;
        if (CollisionDetector.isPassable(gameState, position, direction)) {
            position.x += direction.modX;
            position.y += direction.modY;
            Application.getApp().getWindow().refresh(gameState);
            SoundManager.getSoundManager().play(SoundsEnum.FOOTSTEPS);
        } else {
            SoundManager.getSoundManager().play(SoundsEnum.WALL_BUMP);
        }
    }
}
