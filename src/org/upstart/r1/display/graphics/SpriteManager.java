package org.upstart.r1.display.graphics;

import java.io.IOException;
import java.util.HashMap;

public class SpriteManager {
    private static HashMap<String, Sprite> sprites = new HashMap<>();

    public static Sprite getSprite(String aPath) throws IOException {

        Sprite sprite = sprites.get(aPath);
        if(sprite == null) {
            sprite = new Sprite(aPath);
        }

        return sprite;
    }

}
