package org.upstart.r1.logic.actions;

import org.upstart.r1.Application;
import org.upstart.r1.display.logging.GameLogger;
import org.upstart.r1.display.ui.InventoryPopupMenu;
import org.upstart.r1.display.ui.SelectionHandler;
import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.Inventory;
import org.upstart.r1.objects.items.InventoryObject;
import org.upstart.r1.objects.player.Player;

public class DropAction extends GameAction implements SelectionHandler {
    GameLogger messageLog = GameLogger.getLogger();

    @Override
    public void doAction() {
        GameState gameState = GameState.getInstance();
        Player p = gameState.getPlayer();
        Inventory playerInventory = p.inventory;

        if(playerInventory == null || playerInventory.isEmpty()) {
            messageLog.log("You have nothing to drop.");
        }
        else if(playerInventory.size() == 1) {
            dropItem(0);
        } else {
            InventoryPopupMenu ipm = new InventoryPopupMenu(playerInventory, this);
        }
    }

    private void dropItem(int index) {
        GameState gameState = GameState.getInstance();
        Player p = gameState.getPlayer();
        Inventory playerInventory = p.inventory;
        Inventory tileInventory = gameState.getCurrentMap().getTileInventory(p.position);

        InventoryObject object = playerInventory.remove(index);
        messageLog.log("You dropped " + object.getName() + ".");
        tileInventory.add(object);
    }

    @Override
    public void itemSelected(int index) {
        dropItem(index);
    }
}
