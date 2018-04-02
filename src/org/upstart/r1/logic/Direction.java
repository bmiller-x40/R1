package org.upstart.r1.logic;

public enum Direction {

    NORTH(0,-1), NORTHEAST(1,-1), EAST(1,0), SOUTHEAST(1,1), SOUTH(0,1), SOUTHWEST(-1,1), WEST(-1,0), NORTHWEST(-1,-1), UP(0,0), DOWN(0,0);

    public final int modX, modY;

    Direction(int modX, int modY) {
        this.modX = modX;
        this.modY = modY;
    }
}
