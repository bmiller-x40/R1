package org.upstart.r1.display.tiles;

public enum TilesEnum {

    DNG_FLOOR(1, "images/dungeon/floor/crypt_10.png", true, false),
    DNG_WALL(2, "images/dungeon/wall/catacombs_0.png", false, true);

    private final int key;
    private final String path;
    private final boolean isFloor;
    private final boolean isWall;

    TilesEnum(int aKey, String aPath, boolean isFloor, boolean isWall) {
        this.key = aKey;
        this.path = aPath;
        this.isFloor = isFloor;
        this.isWall = isWall;
    }

    public int getKey() {
        return key;
    }

    public String getPath() {
        return path;
    }

    public boolean isFloor() {
        return isFloor;
    }

    public boolean isWall() {
        return isWall;
    }
}
