package org.upstart.r1.logic;

import org.upstart.r1.display.graphics.MapTile;

public class CollisionDetector {

    public static boolean isPassable(GameState aGameState, Position aStartPosition, Direction aDirection) {
        Map theMap = aGameState.getCurrentMap();
        return theMap.isPassable(aStartPosition.x + aDirection.modX, aStartPosition.y + aDirection.modY);
    }

    private static MapTile getDestination(Map theMap, Position aStartPosition, Direction aDirection) {
        int tileX = aStartPosition.x + aDirection.modX;
        int tileY = aStartPosition.y + aDirection.modY;

        return theMap.getTile(tileX, tileY);
    }

}
