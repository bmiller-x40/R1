package org.upstart.r1.logic;

import org.upstart.r1.display.graphics.MapTile;
import org.upstart.r1.objects.AbstractObject;

public class Map {
    MapTile[][] mapTiles;
    public final int width;
    public final int height;

    public Map(MapTile[][] mapTiles) {
        this.mapTiles = mapTiles;
        this.width = mapTiles.length;
        this.height = mapTiles[0].length;
    }

    public Map(int width, int height) {
        mapTiles = new MapTile[width][height];
        this.width = width;
        this.height = height;
    }


    public MapTile getTile(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return mapTiles[x][y];
    }

    public void put(MapTile mapTile, int x, int y) {
        if(x < mapTiles.length && y < mapTiles[0].length) {
            mapTiles[x][y] = mapTile;
        }
    }

    public void put(int x, int y, MapTile mapTile) {
        put(mapTile, x, y);
    }

    public void putIfNull(int x, int y, MapTile mapTile) {
        if(mapTiles[x][y] == null) {
            put(x, y, mapTile);
        }
    }

    public void asciiDump() {
        System.out.print(' ');
        for(int x = 0; x< mapTiles.length; x++) {
            System.out.print(x % 10);
        }
        System.out.println();

        for(int y = 0; y< mapTiles[0].length; y++) {
            System.out.print(y % 10);
            for(int x = 0; x< mapTiles.length; x++) {
                MapTile mapTile = mapTiles[x][y];
                if(mapTile == null) {
                    System.out.print('.');
                } else if (mapTile.isPassable()) {
                    System.out.print(' ');
                } else {
                    System.out.print('#');
                }
            }
            System.out.println();
        }
    }

    public boolean isPassable(int x, int y) {
        return x < mapTiles.length && y < mapTiles[0].length && mapTiles[x][y] != null && mapTiles[x][y].isPassable();
    }

    public boolean placeObject(int x, int y, AbstractObject o) {
        MapTile tile = getTile(x, y);
        if(tile == null) {
            return false;
        }
        tile.addObject(o);
        return true;
    }
}
