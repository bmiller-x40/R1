package org.upstart.r1.audio;

import java.io.File;
import java.io.FileNotFoundException;

public class Sound {
    public final File soundFile;

    public Sound(SoundsEnum aSound) throws FileNotFoundException {
        soundFile = new File(aSound.path);
        if(!soundFile.exists()) {
            throw new FileNotFoundException(aSound + ": " + aSound.path + " not found.");
        }
    }
}
