package org.upstart.r1.logic.generation;

import org.upstart.r1.display.tiles.Tile;
import org.upstart.r1.display.tiles.TilesEnum;
import org.upstart.r1.display.tiles.TileManager;
import org.upstart.r1.logic.Map;

public class DungeonGeneratorTest {
    public static void main(String[] args) {

        Tile DNG_FLOOR = TileManager.get(TilesEnum.DNG_FLOOR);
        Tile DNG_WALL = TileManager.get(TilesEnum.DNG_WALL);

        ManualDungeonGenerator dg = new ManualDungeonGenerator(30, 30, DNG_FLOOR, DNG_WALL);

        dg.makeRoom(5, 5, 5, 5);
        dg.makeRoom(25,25,5,5);
        dg.makeHall(5,5, 25, 25);

        Map map = new Map(dg.getTileArray());
        map.asciiDump();

    }
}
