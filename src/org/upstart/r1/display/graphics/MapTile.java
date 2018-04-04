package org.upstart.r1.display.graphics;

import org.upstart.r1.logic.Inventory;
import org.upstart.r1.objects.items.InventoryObject;

import java.io.IOException;

public class MapTile {
    public Sprite sprite;
    private Inventory inventory = new Inventory();

    private boolean isPassable = false;

    public MapTile(Sprite sprite, boolean isPassable) {
        this.sprite = sprite;
        this.isPassable = isPassable;
    }

    public MapTile(String path, boolean isPassable) throws IOException {
        this(SpriteManager.getSprite(path), isPassable);
    }

    public boolean isPassable() {
        return isPassable;
    }

    public boolean addObject(InventoryObject o) {
        return inventory.add(o);
    }

    public boolean remove(InventoryObject o) {
        return inventory.remove(o);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean hasContent() {
        return !inventory.isEmpty();
    }
}
