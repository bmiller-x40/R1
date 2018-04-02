package org.upstart.r1.audio;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class SoundManager {

    private static SoundManager instance = null;
    private static HashMap<SoundsEnum, Sound> cachedSounds = new HashMap<>();
    private static SoundPlayer soundPlayer;

    private SoundManager() {
        soundPlayer = new SoundPlayer();
    }

    public static SoundManager getSoundManager() {
        if(instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public Sound getSound(SoundsEnum aSound) {
        Sound sound = null;
        try {
            sound = cachedSounds.get(aSound);
            if (sound == null) {
                sound = new Sound(aSound);
                cachedSounds.put(aSound, sound);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sound;
    }

    public void play(SoundsEnum aSound) {
        Sound sound = getSound(aSound);
        if(sound != null) {
            soundPlayer.play(sound);
        }
    }
}
