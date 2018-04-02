package org.upstart.r1.mobs;

import org.upstart.r1.display.tiles.Tile;
import org.upstart.r1.logic.Position;

import java.io.IOException;

public class AbstractMob {

    public Position position;

    public Tile tile;

    public AbstractMob(Position aPosition, String imgPath) throws IOException {
        this.position = aPosition;
        this.tile = new Tile(imgPath);
    }

    public AbstractMob(int xPos, int yPos, String imgPath) throws IOException {
        this(new Position(xPos, yPos), imgPath);
    }
}
