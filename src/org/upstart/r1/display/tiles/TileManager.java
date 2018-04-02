package org.upstart.r1.display.tiles;

import java.io.IOException;
import java.util.HashMap;

public class TileManager {
    private static HashMap<TilesEnum, Tile> tiles = new HashMap<>();

    static {
        System.out.println("initializing tiles");
        for (TilesEnum c : TilesEnum.values()) {
            try {
                Tile tile = new Tile(c.getPath(), c.isWall(), c.isFloor());
                tiles.put(c, tile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private TileManager() {
    }

    public static Tile get(TilesEnum name) {
        return tiles.get(name);
    }

}
