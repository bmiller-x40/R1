package org.upstart.r1.logic.generation;

import org.upstart.r1.display.tiles.Tile;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.upstart.r1.util.Random.*;
import static org.upstart.r1.util.Math.*;

public class DungeonGenerator {

    int mapWidth;
    int mapHeight;
    Tile floor;
    Tile wall;
    Tile[][] tileMap;

    public DungeonGenerator(int mapWidth, int mapHeight, Tile floor, Tile wall) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.floor = floor;
        this.wall = wall;
        tileMap = new Tile[mapWidth][mapHeight];

    }

    public Tile[][] fetch() {
        int prevX = 0;
        int prevY = 0;

        for(int nRoom = 0;nRoom < 7; nRoom++) {
            int rx = rand(2,mapWidth-4);
            int ry = rand(2,mapHeight-4);
            int rw = rand(1,5);
            int rh = rand(1,5);

            makeRoom(rx, ry, rw, rh);
            if(nRoom != 0) {
                makeHall(prevX, prevY, rx, ry);
            }
            prevX = rx;
            prevY = ry;

        }

        return tileMap;
    }

    public void makeRoom(int centerX, int centerY, int width, int height) {
        System.out.println(
                String.format("make %d x %d room centered on %d,%d", width, height, centerX, centerY)
        );

        int startX = max(0, centerX - roundDown(width, 2));
        int endX = min(centerX + roundUp(width, 2), mapWidth);
        int startY = max(0, centerY - roundDown(height, 2));
        int endY = min(centerY + roundUp(height, 2), mapHeight);

        System.out.println(
                String.format("box corners: [%d, %d], [%d, %d]", startX, startY, endX, endY)
        );

        for(int x=startX;x<endX;x++) {
            for(int y=startY;y<endY;y++) {
                if(x == startX || x == endX -1) {
                    tileMap[x][y] = wall;
                }
                else if (y==startY || y == endY -1) {
                    tileMap[x][y] = wall;
                }
                else {
                    tileMap[x][y] = floor;
                }
            }
        }
    }

    public void makeHall(int startX, int startY, int endX, int endY) {
        System.out.println(
                String.format("make hall from %d, %d to %d,%d", startX, startY, endY, endY)
        );

        int currentX = min(startX, endX), currentY = min(startY, endY);
        int maxX = max(startX, endX), maxY = max(startY, endY);

        int width = abs(startX - endX);
        int height = abs(startY- endY);

        if (width > height) {
            goRight(currentX, currentY, width);
            goDown(maxX, currentY, height);
        } else {
            goDown(currentX, currentY, height);
            goRight(currentX, maxY, width);
        }
    }

    private void goRight(int startX, int startY, int distance) {
        int i;
        for(i = 0; i<=distance; i++) {
            if(tileMap[startX + i][startY - 1] == null) {
                tileMap[startX + i][startY - 1] = wall;
            }
//            if(tileMap[startX + i][startY] == null) {
                tileMap[startX + i][startY] = floor;
//            }
            if(tileMap[startX + i][startY + 1] == null) {
                tileMap[startX + i][startY + 1] = wall;
            }
        }
        // cap it
        if(tileMap[startX + i][startY - 1] != floor) tileMap[startX + i][startY - 1] = wall;
        if(tileMap[startX + i][startY] != floor) tileMap[startX + i][startY] = wall;
        if(tileMap[startX + i][startY + 1] != floor) tileMap[startX + i][startY + 1] = wall;
    }

    private void goDown(int startX, int startY, int distance) {
        int i;
        for(i=0;i<=distance;i++) {
            if(tileMap[startX - 1][startY + i] == null) {
                tileMap[startX - 1][startY + i] = wall;
            }
//            if(tileMap[startX][startY + i] == null) {
                tileMap[startX][startY + i] = floor;
//            }
            if(tileMap[startX + 1][startY + i] == null) {
                tileMap[startX + 1][startY + i] = wall;
            }
        }
        // cap it
        if(tileMap[startX - 1][startY + i] != floor) tileMap[startX - 1][startY + i] = wall;
        if(tileMap[startX][startY + i] != floor) tileMap[startX][startY + i] = wall;
        if(tileMap[startX + 1][startY + i] != floor) tileMap[startX + 1][startY + i] = wall;
    }

}
