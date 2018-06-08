package com.game.elements;

import com.game.JumpGame;
import com.game.utils.Renderer;

/**
 * Created by jakub on 03.06.18.
 */

public class Penguin extends Flying {

    private long timing;
    private long frameTime = 1000;
    private int state = 0;
    private int statesNumber = 2;

    public Penguin(JumpGame game, int x, int y) {
        super(game, x, y);
        timing = System.currentTimeMillis();
        imageName = "penguin";
    }

    @Override
    public void collideWith(Plane plane) {
        active = false;
        plane.die();
        game.getSoundManager().getAsset("penguin_rip1.mp3").play();
    }

    @Override
    public void display(Renderer renderer) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - timing > getFloppingRate()) {
            timing = currentTime;
            state++;
            state = state%statesNumber;
        }
        renderer.image(getImageName(), getCenterX(), getCenterY());
    }

    @Override
    protected String getImageName() {
        return imageName + state + ".png";
    }

    private double getFloppingRate() {
        return frameTime/speed;
    }

}
