package org.upstart.r1.display;


import javax.swing.*;
import java.awt.*;

public class StatusPanel extends JPanel {
    public StatusPanel(int offset_x, int offset_y, int width, int height) {
        super(true);
        this.setBounds(offset_x, offset_y, width, height);

        this.setBackground(Color.LIGHT_GRAY);

        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
    }
}
