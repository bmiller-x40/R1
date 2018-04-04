package org.upstart.r1;

import org.upstart.r1.logic.CommandProcessor;
import org.upstart.r1.logic.Direction;
import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.actions.DropAction;
import org.upstart.r1.logic.actions.GetAction;
import org.upstart.r1.logic.actions.MoveAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Application {

    CommandProcessor commandProcessor;
    GameWindow window;
    GameState gameState;

    private static Application app;
    public static void main(String[] args) {
        try {
            app = new Application();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Application() {
        commandProcessor = CommandProcessor.getInstance();
        window = new GameWindow();

        gameState = GameState.getInstance();

        window.setVisible(true);
        window.refresh(gameState);

        configureKeyBindings(window.mapPanel);
    }

    public static Application getApp() {
        return app;
    }

    public GameWindow getWindow() {
        return window;
    }

    private void configureKeyBindings(JComponent aComponent) {
        InputMap iMap = aComponent.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap aMap = aComponent.getActionMap();

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), Direction.NORTH);
        aMap.put(Direction.NORTH, new MoveAction(Direction.NORTH));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD9, 0), Direction.NORTHEAST);
        aMap.put(Direction.NORTHEAST, new MoveAction(Direction.NORTHEAST));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD6, 0), Direction.EAST);
        aMap.put(Direction.EAST, new MoveAction(Direction.EAST));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD3, 0), Direction.SOUTHEAST);
        aMap.put(Direction.SOUTHEAST, new MoveAction(Direction.SOUTHEAST));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), Direction.SOUTH);
        aMap.put(Direction.SOUTH, new MoveAction(Direction.SOUTH));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD1, 0), Direction.SOUTHWEST);
        aMap.put(Direction.SOUTHWEST, new MoveAction(Direction.SOUTHWEST));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD4, 0), Direction.WEST);
        aMap.put(Direction.WEST, new MoveAction(Direction.WEST));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD7, 0), Direction.NORTHWEST);
        aMap.put(Direction.NORTHWEST, new MoveAction(Direction.NORTHWEST));

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0), "GET");
        aMap.put("GET", new GetAction());

        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "DROP");
        aMap.put("DROP", new DropAction());
    }
}
