package org.upstart.r1.logic;

import org.upstart.r1.display.tiles.Tile;

public class CollisionDetector {

    public static boolean isPassable(GameState aGameState, Position aStartPosition, Direction aDirection) {
        Map theMap = aGameState.getCurrentMap();
        Tile destination = getDestination(theMap, aStartPosition, aDirection);
        return destination != null && destination.isFloor();
    }

    private static Tile getDestination(Map theMap, Position aStartPosition, Direction aDirection) {
        int tileX = aStartPosition.x + aDirection.modX;
        int tileY = aStartPosition.y + aDirection.modY;

        return theMap.getTile(tileX, tileY);
    }

}
