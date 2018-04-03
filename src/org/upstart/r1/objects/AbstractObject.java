package org.upstart.r1.objects;

import org.upstart.r1.display.graphics.Sprite;
import org.upstart.r1.display.graphics.SpriteManager;

import java.io.IOException;

public class AbstractObject {

    public Sprite sprite;

    public AbstractObject(String imgPath) throws IOException {
        this.sprite = SpriteManager.getSprite(imgPath);
    }
}
