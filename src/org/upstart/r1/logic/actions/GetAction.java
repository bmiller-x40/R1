package org.upstart.r1.logic.actions;

import org.upstart.r1.Application;
import org.upstart.r1.display.logging.GameLogger;
import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.Inventory;
import org.upstart.r1.objects.items.InventoryObject;
import org.upstart.r1.objects.player.Player;

public class GetAction extends GameAction {
    GameLogger messageLog = GameLogger.getLogger();

    @Override
    public void doAction() {
        GameState gameState = GameState.getInstance();
        Player p = gameState.getPlayer();

        Inventory tileInventory = gameState.getCurrentMap().getTileInventory(p.position);
        Inventory playerInventory = p.inventory;

        if(!tileInventory.isEmpty()) {
            InventoryObject object = tileInventory.remove(0);
            messageLog.log("You picked up " + object.getName() + ".");
            playerInventory.add(object);
            Application.getApp().getWindow().refresh();
        }
    }

}
