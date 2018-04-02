package org.upstart.r1.logic;

import org.upstart.r1.display.tiles.Tile;
import org.upstart.r1.display.tiles.TilesEnum;
import org.upstart.r1.display.tiles.TileManager;
import org.upstart.r1.logic.generation.ManualDungeonGenerator;
import org.upstart.r1.mobs.player.Player;

import java.io.IOException;

public class GameState {
    private static GameState instance = null;

    Map currentMap;
    Player player;

    private GameState() {
        try {
            initPlayer();
            initMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameState getInstance() {
        if(instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    private void initMap() {
        Tile DNG_FLOOR = TileManager.get(TilesEnum.DNG_FLOOR);
        Tile DNG_WALL = TileManager.get(TilesEnum.DNG_WALL);

        ManualDungeonGenerator dg = new ManualDungeonGenerator(50, 50, DNG_FLOOR, DNG_WALL);

        dg.makeRoom(5, 5, 5, 5);
        dg.makeRoom(25, 25, 7, 5);
        dg.makeRoom(45,45,5,5);

        dg.makeHall(5,5, 25, 25);
        dg.makeHall(25,25, 45, 45);

        currentMap = new Map(dg.getTileArray());
    }


    private void initPlayer() throws IOException {
        player = new Player(25, 25, "images/player/base/human_male.png");
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public Player getPlayer() {
        return player;
    }
}
