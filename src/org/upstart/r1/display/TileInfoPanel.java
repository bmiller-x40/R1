package org.upstart.r1.display;

import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.Inventory;
import org.upstart.r1.logic.Position;
import org.upstart.r1.objects.items.InventoryObject;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;

public class TileInfoPanel extends JTextPane {

    int width, height;

    public TileInfoPanel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(Integer.parseInt("ba9660", 16)));
    }

    public void refresh() {
        GameState gameState = GameState.getInstance();
        Position position = gameState.getPlayer().position;
        Inventory tileInventory = gameState.getCurrentMap().getTile(position).getInventory();

        DefaultStyledDocument document = null;
        try {
            document = new DefaultStyledDocument();
            document.insertString(0, "Items(" + tileInventory.size() + ")\n", null);

            if(!tileInventory.isEmpty()) {
                char ref = 'a';
                for(InventoryObject eachObject : tileInventory) {
                    String text = String.format("%s: %s\n", ref, eachObject.getName());
                    document.insertString(document.getEndPosition().getOffset() -1, text, null);
                    ref++;
                }
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        this.setStyledDocument(document);
        this.revalidate();
        this.repaint();
    }

}
