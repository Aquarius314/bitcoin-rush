package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.game.utils.Renderer;
import com.jump.JumpGame;

import java.util.ArrayList;
import java.util.List;

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
        plane.getUiManager().getPointsInfo().clearPoints();
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
