package org.upstart.r1.objects.player;

import org.upstart.r1.logic.Inventory;
import org.upstart.r1.objects.mobs.AbstractMob;

import java.io.IOException;

public class Player extends AbstractMob {
    public int facing;
    public Inventory inventory = new Inventory();

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
