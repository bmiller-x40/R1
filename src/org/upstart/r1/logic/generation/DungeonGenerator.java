package org.upstart.r1.logic.generation;

import org.upstart.r1.display.graphics.MapTile;
import org.upstart.r1.display.graphics.Sprite;
import org.upstart.r1.logic.Map;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.upstart.r1.util.Math.*;
import static org.upstart.r1.util.Random.rand;

public class DungeonGenerator {

    int mapWidth;
    int mapHeight;
    private Sprite floorSprite;
    private Sprite wallSprite;
    Map map;

    public DungeonGenerator(int mapWidth, int mapHeight, Sprite floorSprite, Sprite wallSprite) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.floorSprite = floorSprite;
        this.wallSprite = wallSprite;
        map = new Map(mapWidth, mapHeight);

    }

    public Map getMap() {
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

        return map;
    }

    public void makeRoom(int centerX, int centerY, int width, int height) {
//        System.out.println(
//                String.format("make %d x %d room centered on %d,%d", width, height, centerX, centerY)
//        );

        int startX = max(0, centerX - roundDown(width, 2));
        int endX = min(centerX + roundUp(width, 2), mapWidth);
        int startY = max(0, centerY - roundDown(height, 2));
        int endY = min(centerY + roundUp(height, 2), mapHeight);

//        System.out.println(
//                String.format("box corners: [%d, %d], [%d, %d]", startX, startY, endX, endY)
//        );

        for(int x=startX;x<endX;x++) {
            for(int y=startY;y<endY;y++) {
                if(x == startX || x == endX -1) {
                    map.put(x, y, newWall());
                }
                else if (y==startY || y == endY -1) {
                    map.put(x, y, newWall());
                }
                else {
                    map.put(x, y, newFloor());
                }
            }
        }
    }

    private MapTile newWall() {
        return new MapTile(wallSprite, false);
    }

    private MapTile newFloor() {
        return new MapTile(floorSprite, true);
    }

    public void makeHall(int startX, int startY, int endX, int endY) {
//        System.out.println(
//                String.format("make hall from %d, %d to %d,%d", startX, startY, endY, endY)
//        );

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
            map.putIfNull(startX + i, startY - 1, newWall());
            map.put(startX + i, startY, newFloor());
            map.putIfNull(startX + i, startY + 1, newWall());
        }
        // cap it
        int x = startX  + i, y = startY - 1;
        for(int n=0; n<3; n++) {
            map.putIfNull(x, y + n, newWall());
        }
    }

    private void goDown(int startX, int startY, int distance) {
        int i;
        for(i=0;i<=distance;i++) {
            map.putIfNull(startX - 1,startY + i, newWall());
            map.put(startX, startY + i, newFloor());
            map.putIfNull(startX + 1, startY + i, newWall());
        }
        // cap it
        int x = startX - 1, y = startY + i;
        for(int n=0; n<3; n++) {
            map.putIfNull(x + n, y, newWall());
        }
    }
}
