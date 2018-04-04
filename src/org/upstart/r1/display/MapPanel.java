package org.upstart.r1.display;

import org.upstart.r1.display.graphics.MapTile;
import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.Map;
import org.upstart.r1.objects.AbstractObject;
import org.upstart.r1.objects.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {
    BufferedImage paintedBuffer, workingBuffer;

    public MapPanel(int width, int height) {
        super();
        this.setBounds(0, 0, width, height);
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    }

    public void refresh() {
        GameState gameState = GameState.getInstance();
//        long drawTimeMillis = System.currentTimeMillis();
        Map map = gameState.getCurrentMap();
        Player p = gameState.getPlayer();

        int visibleTilesX = Math.min(this.getWidth() / 32, map.width);
        int visibleTilesY = Math.min(this.getHeight() / 32, map.height);

        int leftEdge = p.position.x - (visibleTilesX / 2);
        int topEdge = p.position.y - (visibleTilesY / 2);

        workingBuffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);

        drawMap(map, leftEdge, topEdge, leftEdge + visibleTilesX, topEdge + visibleTilesY);
        drawObjects(map, leftEdge, topEdge, leftEdge + visibleTilesX, topEdge + visibleTilesY);
        drawPlayer(gameState);

        paintedBuffer = workingBuffer;
        this.paintComponent(this.getGraphics());
//        drawTimeMillis = System.currentTimeMillis() - drawTimeMillis;
//        System.out.println("drawTimeMillis = " + drawTimeMillis);
    }


    private void drawMap(Map map, int mapStartX, int mapStartY, int mapEndX, int mapEndY) {
//        System.out.println(
//                String.format("drawing map from [%d,%d] to [%d,%d]", mapStartX, mapStartY, mapEndX, mapEndY)
//        );

        Graphics g = workingBuffer.getGraphics();

        int bufferEndX = Math.min(this.getWidth() / 32, map.width);
        int bufferEndY = Math.min(this.getHeight() / 32, map.height);

//        System.out.println(
//                String.format("drawing buffer from [0,0] to [%d,%d]", bufferEndX, bufferEndY)
//        );

        for(int x=0; x < bufferEndX; x++) {
            for(int y=0; y < bufferEndY; y++) {
                MapTile mapTile = map.getTile(mapStartX + x, mapStartY + y);
                if(mapTile != null) {
//                    System.out.println(
//                            String.format("draw %s at [%d, %d]", ((mapTile.isWall())? "wall":"floor"), x, y)
//                    );
                    g.drawImage(mapTile.sprite.img, x * 32, y * 32, null);
                }
            }
        }
    }

    private void drawObjects(Map map, int startX, int startY, int endX, int endY) {
        Graphics g = workingBuffer.getGraphics();
        for(int x = startX; x < endX; x++) {
            for(int y = startY; y < endY; y++) {
                MapTile tile = map.getTile(x, y);
                if(tile != null && tile.hasContent())
                    for (AbstractObject o : tile.getInventory()) {
//                        System.out.println(
//                                String.format("draw object at %d, %d", x, y)
//                        );
                        int ox = (x - startX) * 32;
                        int oy = (y - startY) * 32;
                        boolean rval = g.drawImage(o.sprite.img, ox, oy,  null);
                    }
            }
        }
    }

    private void drawPlayer(GameState gameState) {
        Player p = gameState.getPlayer();
//        System.out.println(
//                String.format("draw player at %d, %d", p.position.x, p.position.y)
//        );

        int screenWidthInTiles = (this.getWidth() / 32);
        int screenHeightInTiles = (this.getHeight() / 32);

        int centerX = (screenWidthInTiles / 2);
        int centerY = (screenHeightInTiles / 2);

        Graphics g = workingBuffer.getGraphics();
        g.drawImage(p.sprite.img, centerX * 32, centerY * 32, null);

    }

    @Override
    protected void paintComponent(Graphics g) {
//        long paintTime = System.currentTimeMillis();
        super.paintComponent(g);
        g.drawImage(paintedBuffer, 0, 0, null);
//        paintTime = System.currentTimeMillis() - paintTime;
//        System.out.println("painted in " + paintTime);
    }
}
