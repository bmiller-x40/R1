package org.upstart.r1.display;

import org.upstart.r1.display.logging.GameLogger;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;

public class LogPanel extends JPanel {

    private GameLogger logger = GameLogger.getLogger();

    public LogPanel(int offset_x, int offset_y, int width, int height) {
        super();
//        System.out.println(
//                String.format("offset_x: %d, offset_y: %d, width: %d, height: %d",
//                               offset_x, offset_y, width, height)
//        );
        this.setBounds(offset_x, offset_y, width, height);

        this.setBackground(Color.GRAY);

        this.setBorder(new EtchedBorder());
        this.setLayout(new CardLayout());

        Document logBuffer = logger.getDocument();
        JTextArea textArea = new JTextArea(logBuffer);

        logBuffer.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textArea.setLineWrap(true);
        textArea.setEditable(false); // set textArea non-editable
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
    }
}
