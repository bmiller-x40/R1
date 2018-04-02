package org.upstart.r1.audio;

public enum SoundsEnum {

    FOOTSTEPS("sounds/foot.wav"),
    WALL_BUMP("sounds/pop2.wav");

    public final String path;

    SoundsEnum (String aPath){
        this.path = aPath;
    }
}
