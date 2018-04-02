package org.upstart.r1.display.tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    public final BufferedImage img;

    private boolean isWall = false;
    private boolean isFloor = false;

    public Tile(String path) throws IOException {
        img =  ImageIO.read(new File(path));
    }

    public Tile(String path, boolean isWall, boolean isFloor) throws IOException {
        this(path);
        this.isWall = isWall;
        this.isFloor = isFloor;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isFloor() {
        return isFloor;
    }

    public void setFloor(boolean floor) {
        isFloor = floor;
    }
}
