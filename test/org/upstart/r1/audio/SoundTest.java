package org.upstart.r1.audio;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class SoundTest {

    @Test
    public void testLoad() {
        Sound sound;
        for(SoundsEnum eachSound : SoundsEnum.values()) {
            sound = SoundManager.getSoundManager().getSound(eachSound);
            assertNotNull(
                    String.format("%s: could not load '%s' ", eachSound.name(), eachSound.path),
                    sound);

        }
    }

    @Test
    public void testPlay() {
        SoundManager.getSoundManager().play(SoundsEnum.FOOTSTEPS);
    }
}
