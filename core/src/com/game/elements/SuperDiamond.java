package com.game.elements;

import com.badlogic.gdx.audio.Sound;
import com.game.JumpGame;

/**
 * Created by jakub on 09.06.18.
 */

public class SuperDiamond extends Diamond {

    private int type = 0;
    private long typeFrameRate = 200;
    private long lastChangeTime = System.currentTimeMillis();
    private final int NUMBER_OF_IMAGES = 5;

    public SuperDiamond(JumpGame game, int x, int y) {
        super(game, x, y);
        imageName = "sbtc0.png";
    }

    @Override
    public String getImageName() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastChangeTime > typeFrameRate) {
            lastChangeTime = currentTime;
            type = (type+1)%NUMBER_OF_IMAGES;
        }
        return "sbtc" + type + ".png";
    }

    @Override
    protected void playSound() {
        game.getSoundManager().playSound("coin.mp3", 0.5f);
    }

    @Override
    public void collideWith(Plane plane) {
        super.collideWith(plane);
        for(Penguin p : game.getPenguins()) {
            p.kill();
        }
    }

}
