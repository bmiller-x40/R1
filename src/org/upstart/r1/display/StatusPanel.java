package org.upstart.r1.display;


import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {

    PlayerStatsPanel playerStatsPanel;
    TileInfoPanel tileInfoPanel;
    MiniMapPanel miniMapPanel;

    public StatusPanel(int offset_x, int offset_y, int width, int height) {
        super();
        this.setBounds(offset_x, offset_y, width, height);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        playerStatsPanel = new PlayerStatsPanel(width, height /3);
        tileInfoPanel = new TileInfoPanel(width, height / 3);
        miniMapPanel = new MiniMapPanel(width, height / 3);

        this.add(playerStatsPanel);
        this.add(tileInfoPanel);
        this.add(miniMapPanel);
    }


    public void refresh() {
        playerStatsPanel.refresh();
        tileInfoPanel.refresh();
        miniMapPanel.refresh();
    }
}
