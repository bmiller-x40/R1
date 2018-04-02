package org.upstart.r1.display.logging;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;

public class GameLogger {
    private static GameLogger logger = null;

    private PlainDocument document;
    private SimpleAttributeSet defaultAttributeSet;

    public static GameLogger getLogger() {
        if(logger == null) {
            logger = new GameLogger();
        }
        return logger;
    }

    public GameLogger() {
        this.document = new PlainDocument();
        this.defaultAttributeSet = new SimpleAttributeSet();
    }

    public void log(String message) {
//        System.out.println(message);
        try {
            document.insertString(document.getEndPosition().getOffset() -1, message + "\n", defaultAttributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public Document getDocument() {
        return this.getLogger().document;
    }
}
