package org.upstart.r1.logic;

import org.upstart.r1.display.tiles.Tile;

public class Map {
    Tile[][] tiles;
    public final int width;
    public final int height;

    public Map(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
    }

    public Map(int width, int height) {
        tiles = new Tile[width][height];
        this.width = width;
        this.height = height;
    }


    public Tile getTile(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return tiles[x][y];
    }

    public void put(Tile tile, int x, int y) {
        tiles[x][y] = tile;
    }

    public void asciiDump() {
        System.out.print(' ');
        for(int x=0;x<tiles.length;x++) {
            System.out.print(x % 10);
        }
        System.out.println();

        for(int y=0;y<tiles[0].length;y++) {
            System.out.print(y % 10);
            for(int x=0;x<tiles.length;x++) {
                Tile tile = tiles[x][y];
                if(tile == null) {
                    System.out.print('.');
                } else if (tile.isFloor()) {
                    System.out.print(' ');
                } else {
                    System.out.print('#');
                }
            }
            System.out.println();
        }
    }
}
