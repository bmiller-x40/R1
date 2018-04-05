package org.upstart.r1.logic.actions;

import org.upstart.r1.display.logging.GameLogger;
import org.upstart.r1.display.ui.InventoryPopupMenu;
import org.upstart.r1.display.ui.SelectionHandler;
import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.Inventory;
import org.upstart.r1.objects.items.InventoryObject;
import org.upstart.r1.objects.player.Player;

public class GetAction extends GameAction implements SelectionHandler {
    GameLogger messageLog = GameLogger.getLogger();

    @Override
    public void doAction() {
        GameState gameState = GameState.getInstance();
        Player p = gameState.getPlayer();

        Inventory tileInventory = gameState.getCurrentMap().getTileInventory(p.position);

        if(tileInventory == null || tileInventory.isEmpty()) {
            messageLog.log("There is nothing here to get.");
        }
        else if(tileInventory.size() == 1) {
            getItem(0);
        } else {
            InventoryPopupMenu ipm = new InventoryPopupMenu(tileInventory, this);
        }
    }

    private void getItem(int index) {
        GameState gameState = GameState.getInstance();
        Player p = gameState.getPlayer();
        Inventory playerInventory = p.inventory;
        Inventory tileInventory = gameState.getCurrentMap().getTileInventory(p.position);

        InventoryObject object = tileInventory.remove(index);
        messageLog.log("You picked up " + object.getName() + ".");
        playerInventory.add(object);
//        Application.getApp().getWindow().refresh();
    }

    @Override
    public void itemSelected(int index) {
        getItem(index);
    }
}
