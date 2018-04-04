package org.upstart.r1.display;

import org.upstart.r1.display.ui.FileMenu;
import org.upstart.r1.display.ui.UiActionListener;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private int WIN_WIDTH = 1280;
    private int WIN_HEIGHT = 800;

    private int MAP_WIDTH = (int)(WIN_WIDTH * .8);
    private int MAP_HEIGHT = (int)(WIN_HEIGHT * .8);

    private int STATS_WIDTH = WIN_WIDTH - MAP_WIDTH;
    private int STATS_HEIGHT = WIN_HEIGHT;
    private int STATS_X = MAP_WIDTH + 1;
    private int STATS_Y = 0;

    private int LOG_WIDTH = MAP_WIDTH;
    private int LOG_HEIGHT = WIN_HEIGHT - MAP_HEIGHT;
    private int LOG_X = 0;
    private int LOG_Y = MAP_HEIGHT + 1;

    private MapPanel mapPanel;
    private StatusPanel statusPanel;
    private LogPanel logPanel;

    public GameWindow() throws HeadlessException {
        super();
        this.setTitle("App GameWindow");
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        this.setDefaultCloseOperation(GameWindow.EXIT_ON_CLOSE);
        this.setBackground(Color.GRAY);
        this.setResizable(false);
        this.setLayout(new CardLayout());
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new FileMenu(UiActionListener.getListener()));
        menuBar.setVisible(true);
        this.setJMenuBar(menuBar);

        mapPanel = new MapPanel(MAP_WIDTH, MAP_HEIGHT);
        statusPanel = new StatusPanel(STATS_X, STATS_Y, STATS_WIDTH, STATS_HEIGHT);
        logPanel = new LogPanel(LOG_X, LOG_Y, LOG_WIDTH, LOG_HEIGHT);

        JSplitPane northSouthSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mapPanel, logPanel);
        northSouthSplitPane.setDividerLocation(MAP_HEIGHT);

        JSplitPane eastWestSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, northSouthSplitPane, statusPanel);
        eastWestSplitPane.setDividerLocation(MAP_WIDTH);


        this.add(eastWestSplitPane);

        this.setFocusable(true);
    }

    public void refresh() {
        mapPanel.refresh();
        statusPanel.refresh();
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }
}
