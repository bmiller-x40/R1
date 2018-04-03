package org.upstart.r1.display.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    public final BufferedImage img;

    public Sprite(String path) throws IOException {
        img =  ImageIO.read(new File(path));
    }
}
