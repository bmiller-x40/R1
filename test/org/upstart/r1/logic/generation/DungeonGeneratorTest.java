package org.upstart.r1.logic.generation;

import org.upstart.r1.display.graphics.Sprite;
import org.upstart.r1.display.graphics.SpriteManager;
import org.upstart.r1.logic.Map;

import java.io.IOException;

public class DungeonGeneratorTest {
    public static void main(String[] args) throws IOException {

        Sprite floorSprite = SpriteManager.getSprite("images/dungeon/wall/catacombs_0.png");
        Sprite wallSprite = SpriteManager.getSprite("images/dungeon/wall/catacombs_0.png");

        ManualDungeonGenerator dg = new ManualDungeonGenerator(30, 30, floorSprite, wallSprite);

        dg.makeRoom(5, 5, 5, 5);
        dg.makeRoom(25,25,5,5);
        dg.makeHall(5,5, 25, 25);

        Map map = dg.getMap();
        map.asciiDump();

    }
}
