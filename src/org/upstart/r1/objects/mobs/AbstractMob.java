package org.upstart.r1.objects.mobs;

import org.upstart.r1.logic.Position;
import org.upstart.r1.objects.AbstractObject;

import java.io.IOException;

public class AbstractMob extends AbstractObject {
    public Position position;

    public AbstractMob(Position aPosition, String imgPath) throws IOException {
        super(imgPath);
        this.position = aPosition;
    }

    public AbstractMob(int xPos, int yPos, String imgPath) throws IOException {
        super(imgPath);
        this.position = new Position(xPos, yPos);
    }
}
