package org.upstart.r1.logic.actions;

import org.upstart.r1.logic.CommandProcessor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public abstract class GameAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        CommandProcessor.getInstance().setAction(this);
    }

    public abstract void doAction();
}
