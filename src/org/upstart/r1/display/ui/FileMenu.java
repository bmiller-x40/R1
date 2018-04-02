package org.upstart.r1.display.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FileMenu extends JMenu {
    public FileMenu(ActionListener actionListener) throws HeadlessException {
        super("File");
        this.add(new SaveMenuItem(actionListener));
        this.add(new LoadMenuItem(actionListener));
    }

    private class SaveMenuItem extends JMenuItem {
        public SaveMenuItem(ActionListener actionListener) throws HeadlessException {
            super("Save", 's');
            this.addActionListener(actionListener);
        }
    }

    private class LoadMenuItem extends JMenuItem {
        public LoadMenuItem(ActionListener actionListener) throws HeadlessException {
            super("Load",'l');
            this.addActionListener(actionListener);
        }
    }

}
