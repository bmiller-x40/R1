package org.upstart.r1.objects;

import org.upstart.r1.display.graphics.Sprite;
import org.upstart.r1.display.graphics.SpriteManager;

import java.io.IOException;

public class AbstractObject {

    public Sprite sprite;
    private String name;
    private String description = "";

    public AbstractObject(String imgPath) throws IOException {
        this.sprite = SpriteManager.getSprite(imgPath);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
