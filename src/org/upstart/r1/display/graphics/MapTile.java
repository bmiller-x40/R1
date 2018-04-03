package org.upstart.r1.display.graphics;

import org.upstart.r1.logic.Inventory;
import org.upstart.r1.objects.AbstractObject;

import java.io.IOException;
import java.util.Iterator;

public class MapTile {
    public Sprite sprite;
    private Inventory inventory = new Inventory();

    private boolean isPassable = false;

    public MapTile(Sprite sprite, boolean isPassable) {
        this.sprite = sprite;
        this.isPassable = isPassable;
    }

    public MapTile(String path, boolean isPassable) throws IOException {
        sprite = new Sprite(path);
        this.isPassable = isPassable;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public boolean addObject(AbstractObject o) {
        return inventory.add(o);
    }

    public void take(AbstractObject o) {
        inventory.remove(o);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean hasContent() {
        return !inventory.isEmpty();
    }
}
