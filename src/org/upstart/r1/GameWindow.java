package org.upstart.r1;

import org.upstart.r1.display.MapPanel;
import org.upstart.r1.display.LogPanel;
import org.upstart.r1.display.StatusPanel;
import org.upstart.r1.display.ui.FileMenu;
import org.upstart.r1.display.ui.UiActionListener;
import org.upstart.r1.logic.GameState;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private int WIN_WIDTH = 1920;
    private int WIN_HEIGHT = 1080;

    private int MAP_WIDTH = 32 * 40;
    private int MAP_HEIGHT = 32 * 25;
    private int MAP_X = 0;
    private int MAP_Y = 0;

    private int STATS_WIDTH = WIN_WIDTH - MAP_WIDTH;
    private int STATS_HEIGHT = MAP_HEIGHT;
    private int STATS_X = MAP_WIDTH + 1;
    private int STATS_Y = 0;

    private int LOG_WIDTH = WIN_WIDTH;
    private int LOG_HEIGHT = WIN_HEIGHT - MAP_HEIGHT;
    private int LOG_X = 0;
    private int LOG_Y = MAP_HEIGHT + 1;

    MapPanel mapPanel;

    public GameWindow() throws HeadlessException {
        super();
        this.setTitle("App GameWindow");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(GameWindow.EXIT_ON_CLOSE);
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.setLayout(new CardLayout());
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new FileMenu(UiActionListener.getListener()));
        menuBar.setVisible(true);
        this.setJMenuBar(menuBar);

        mapPanel = new MapPanel(MAP_X, MAP_Y, MAP_WIDTH, MAP_HEIGHT);
//        this.add(mapPanel, BorderLayout.CENTER);

        StatusPanel sp = new StatusPanel(STATS_X, STATS_Y, STATS_WIDTH, STATS_HEIGHT);
//        this.add(sp, BorderLayout.EAST);

        JSplitPane eastWestSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, mapPanel, sp);
        eastWestSplitPane.setDividerLocation(MAP_WIDTH);

        LogPanel lp = new LogPanel(LOG_X, LOG_Y, LOG_WIDTH, LOG_HEIGHT);
//        this.add(lp, BorderLayout.SOUTH);

        JSplitPane northSouthSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, eastWestSplitPane, lp);
        northSouthSplitPane.setDividerLocation(MAP_HEIGHT);
        this.add(northSouthSplitPane);

        this.setFocusable(true);
    }

    public void refresh(GameState gameState) {
        mapPanel.refresh(gameState);
    }
}
