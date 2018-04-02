package org.upstart.r1.logic.generation;

import org.upstart.r1.display.tiles.Tile;

public class ManualDungeonGenerator extends DungeonGenerator {
    public ManualDungeonGenerator(int mapWidth, int mapHeight, Tile floor, Tile wall) {
        super(mapWidth, mapHeight, floor, wall);
    }

    public Tile[][] getTileArray() {
        return tileMap;
    }
}
