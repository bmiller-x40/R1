package org.upstart.r1.display.ui;

import java.awt.Color;
import org.upstart.r1.Application;
import org.upstart.r1.logic.GameState;
import org.upstart.r1.logic.Inventory;
import org.upstart.r1.objects.items.InventoryObject;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InventoryPopupMenu extends JPopupMenu implements KeyListener {
    private final SelectionHandler selectionHandler;
    Inventory inventory;
    int selection = 0;
    int maxIndex = 0;
//    Sprite background, tLeft, tRight, bLeft, bRight;

    ArrayList<InventoryObject> staticItemList = new ArrayList<>(5);

    public InventoryPopupMenu(Inventory inventory, SelectionHandler selectionHandler) {
        super("Select item");
//        try {
//            background = SpriteManager.getSprite("images/adom/nterface/window/text/bg_txt.png");
//            tLeft = SpriteManager.getSprite("images/adom/nterface/window/text/c_top_left.png");
//            tRight = SpriteManager.getSprite("images/adom/nterface/window/text/c_top_right.png");
//            bLeft = SpriteManager.getSprite("images/adom/nterface/window/text/c_bot_l.png");
//            bRight = SpriteManager.getSprite("images/adom/nterface/window/text/c_bot_r.png");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        this.inventory = inventory;
        staticItemList.addAll(inventory);
        this.selectionHandler = selectionHandler;
        addMenuItems();
        this.addKeyListener(this);
//        this.setPreferredSize(new Dimension(100, 200));
        JPanel mapPanel = Application.getApp().getWindow().getMapPanel();
        mapPanel.add(this);
        positionMenu();
        highlight(selection);
        grabFocus();
        GameState.getInstance().menuOpen = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        if (keyChar >= 'a' && keyChar <= 'z') {
            int itemIndex = keyChar - 'a';
            selectAndContinue(itemIndex);
        } else {
            doMenuAction(keyCode);
        }
        e.consume();
    }

    private void doMenuAction(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_NUMPAD8:
            case KeyEvent.VK_UP:
                System.out.println("key = UP");
                if (selection >= 0) {
                    moveSelectionUp();
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_NUMPAD2:
                System.out.println("key = DOWN");
                if (selection < maxIndex) {
                    moveSelectionDown();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                closeMenu();
                break;
            case KeyEvent.VK_SPACE:
            case KeyEvent.VK_ENTER:
                selectItem();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

//    private void bindKeys() {
//        InputMap iMap = this.getInputMap(JComponent.WHEN_FOCUSED);
//        ActionMap aMap = this.getActionMap();
//
//        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD8, 0), "ITEM_UP");
//        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "ITEM_UP");
//        aMap.put("ITEM_UP", this);
//
//        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_NUMPAD2, 0), "ITEM_DOWN");
//        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "ITEM_DOWN");
//        aMap.put("ITEM_DOWN", this);
//
//        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "SELECT");
//        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "SELECT");
//        aMap.put("SELECT", this);
//
//        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CLOSE");
//        aMap.put("CLOSE", this);
//    }


//    @Override
//    protected void paintComponent(Graphics g) {
//        int width = this.getWidth();
//        int height = this.getHeight();
//
//        g.drawImage(background.img, 0, 0, null);
//        g.drawImage(tLeft.img, 0, 0, null);
//        g.drawImage(tRight.img, width - tRight.img.getWidth(), 0, null);
//        g.drawImage(bLeft.img, 0, height - bLeft.img.getHeight(), null);
//        g.drawImage(bRight.img, width - bRight.img.getWidth(), height - bLeft.img.getHeight(), null);
//        super.paintComponent(g);
//    }

    private void addMenuItems() {
        char quickKeyChar = 'a';
        for (InventoryObject eachObject : inventory) {
            JMenuItem menuItem = new JMenuItem(quickKeyChar++ + ": " + eachObject.getName());
            add(menuItem);
            maxIndex++;
        }
    }

    private void positionMenu() {
        JPanel mapPanel = Application.getApp().getWindow().getMapPanel();
        int mapWidth = mapPanel.getWidth();
        int mapHeight = mapPanel.getHeight();
        int menuWidth = this.getWidth();
        int menuHeight = this.getHeight();

        int left = (mapWidth / 2) - (menuWidth / 2);
        int top = (mapHeight / 2) - (menuHeight / 2);

        mapPanel.add(this);
        this.show(mapPanel, left, top);

    }

    private void selectAndContinue(int itemIndex) {
        if(this.getComponent(itemIndex).isEnabled()) {
            selectionHandler.itemSelected(getInventoryIndexOf(itemIndex));
            if (inventory.isEmpty()) {
                closeMenu();
            } else {
                this.getComponent(itemIndex).setEnabled(false);
            }
        }
    }

    private int getInventoryIndexOf(int itemNum) {
        return inventory.indexOf(staticItemList.get(itemNum));
    }

    private void selectAndClose(int itemIndex) {
        selectionHandler.itemSelected(getInventoryIndexOf(itemIndex));
        closeMenu();
    }

    private void highlight(int index) {
        JMenuItem menuItem = (JMenuItem) getComponent(index);
        menuItem.setForeground(Color.BLUE);
        repaint();
    }

    private void unhighlight(int index) {
        JMenuItem menuItem = (JMenuItem) getComponent(index);
        menuItem.setForeground(Color.BLACK);
        repaint();
    }

    private void selectItem() {
        selectAndClose(selection);
    }

    private void closeMenu() {
        GameState.getInstance().menuOpen = false;
        this.setVisible(false);
        Application.getApp().getWindow().refresh();
    }

    private void moveSelectionDown() {
        unhighlight(selection);
        selection++;
        highlight(selection);
    }

    private void moveSelectionUp() {
        unhighlight(selection);
        selection--;
        highlight(selection);
    }


}
