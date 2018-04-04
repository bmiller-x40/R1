package org.upstart.r1.logic;

import org.upstart.r1.display.graphics.Sprite;
import org.upstart.r1.display.graphics.SpriteManager;
import org.upstart.r1.logic.generation.ManualDungeonGenerator;
import org.upstart.r1.objects.items.InventoryObject;
import org.upstart.r1.objects.player.Player;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

public class GameState {
    private static GameState instance = null;
    private static ArrayList<PropertyChangeListener> listeners;

    Map currentMap;
    Player player;

    private GameState() {
        try {
            initPlayer();
            initMap();
            listeners = new ArrayList<>();
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

    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PropertyChangeListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {

    }

    private void initMap() throws IOException {
        Sprite floorSprite = SpriteManager.getSprite("images/dungeon/floor/crypt_10.png");
        Sprite wallSprite = SpriteManager.getSprite("images/dungeon/wall/catacombs_0.png");

        ManualDungeonGenerator dg = new ManualDungeonGenerator(50, 50, floorSprite, wallSprite);

        dg.makeRoom(5, 5, 5, 5);
        dg.makeRoom(25, 25, 7, 5);
        dg.makeRoom(45,45,5,5);

        dg.makeHall(5,5, 25, 25);
        dg.makeHall(25,25, 45, 45);

        currentMap = dg.getMap();

        InventoryObject box = new InventoryObject("images/item/misc/misc_box.png", "a box");
        currentMap.placeObject(26,26, box);
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
