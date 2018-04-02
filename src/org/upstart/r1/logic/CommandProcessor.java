package org.upstart.r1.logic;

import org.upstart.r1.logic.actions.GameAction;

public class CommandProcessor {

    private static CommandProcessor instance;

    private CommandProcessor() {
    }

    public static CommandProcessor getInstance() {
        if(instance == null) {
            instance = new CommandProcessor();
        }
        return instance;
    }

    public void setAction(GameAction action) {
        CommandDoer doer = new CommandDoer(action);
        doer.start();
    }

    private class CommandDoer extends Thread {
        GameAction action;

        public CommandDoer(GameAction action) {
            this.action = action;
        }

        @Override
        public void run() {
            action.doAction();
        }
    }

}
