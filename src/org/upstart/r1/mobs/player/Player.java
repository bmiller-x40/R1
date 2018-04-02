package org.upstart.r1.mobs.player;

import org.upstart.r1.mobs.AbstractMob;

import java.io.IOException;

public class Player extends AbstractMob {
    public int facing;

    public Player(int xPos, int yPos, String imgPath) throws IOException {
        super(xPos, yPos, imgPath);
    }

    public int getFacing() {
        return facing;
    }

    public void setFacing(int facing) {
        this.facing = facing;
    }
}
