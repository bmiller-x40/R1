package org.upstart.r1.display;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsPanel extends JPanel {

    public PlayerStatsPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(Integer.parseInt("ba8060", 16)));
        this.add(new JLabel("Stats Panel"));
    }

    public void refresh() {
//        this.update(this.getGraphics());
    }
}
