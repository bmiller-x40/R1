package org.upstart.r1.display;

import javax.swing.*;
import java.awt.*;

public class MiniMapPanel extends JPanel {
    public MiniMapPanel(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(Integer.parseInt("ba6960", 16)));
        this.add(new JLabel("MiniMap"));
    }

    public void refresh() {
//        this.update(this.getGraphics());
    }
}
