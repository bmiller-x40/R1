package org.upstart.r1.display;

import org.upstart.r1.display.tiles.Tile;
import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.Map;
import org.upstart.r1.mobs.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MapPanel extends JPanel {
    BufferedImage paintedBuffer, workingBuffer;

    public MapPanel(int offset_x, int offset_y, int width, int height) {
        super(true);
        this.setBounds(offset_x, offset_y, width, height);
        this.setBackground(Color.BLACK);

        this.setBorder(BorderFactory.createLineBorder(Color.CYAN));

    }

    public void refresh(GameState gameState) {
//        long drawTimeMillis = System.currentTimeMillis();
        Map map = gameState.getCurrentMap();
        Player p = gameState.getPlayer();

        int visibleTilesX = Math.min(this.getWidth() / 32, map.width);
        int visibleTilesY = Math.min(this.getHeight() / 32, map.height);

        int leftEdge = p.position.x - (visibleTilesX / 2);
        int topEdge = p.position.y - (visibleTilesY / 2);

        workingBuffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);

        drawMap(map, leftEdge, topEdge, leftEdge + visibleTilesX, topEdge + visibleTilesY);
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
                Tile tile = map.getTile(mapStartX + x, mapStartY + y);
                if(tile != null) {
//                    System.out.println(
//                            String.format("draw %s at [%d, %d]", ((tile.isWall())? "wall":"floor"), x, y)
//                    );
                    g.drawImage(tile.img, x * 32, y * 32, null);
                }
            }
        }
    }

    private void drawPlayer(GameState gameState) {
//        System.out.println("adding player");

        Player p = gameState.getPlayer();

        int screenWidthInTiles = (this.getWidth() / 32);
        int screenHeightInTiles = (this.getHeight() / 32);

        int centerX = (screenWidthInTiles / 2);
        int centerY = (screenHeightInTiles / 2);

        Graphics g = workingBuffer.getGraphics();
        g.drawImage(p.tile.img, centerX * 32, centerY * 32, null);

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
