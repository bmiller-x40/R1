package org.upstart.r1.display.ui;

import org.upstart.r1.display.logging.GameLogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UiActionListener implements ActionListener {

    private GameLogger logger = GameLogger.getLogger();
    private static UiActionListener listener = null;

    public static UiActionListener getListener() {
        if(listener == null) {
            listener = new UiActionListener();
        }
        return listener;
    }

    private UiActionListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        logger.log(e.getActionCommand());
    }
}
