package org.upstart.r1.logic.generation;

import org.upstart.r1.display.graphics.Sprite;
import org.upstart.r1.logic.Map;

public class ManualDungeonGenerator extends DungeonGenerator {
    public ManualDungeonGenerator(int mapWidth, int mapHeight, Sprite floorSprite, Sprite wallSprite) {
        super(mapWidth, mapHeight, floorSprite, wallSprite);
    }

    public Map getMap() {
        return map;
    }
}
