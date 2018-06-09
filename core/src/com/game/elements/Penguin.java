package com.game.elements;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.async.AsyncTask;
import com.game.JumpGame;
import com.game.utils.Renderer;

import java.util.Random;

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
        playSound();
    }

    protected void playSound() {
        Sound sound = game.getSoundManager().getAsset("penguin_rip1.mp3");
        long id = sound.play();
        float pitch = (new Random().nextInt()%6)/10.0f;
        sound.setPitch(id, 1.2f + pitch);
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
    public void kill() {
        super.kill();
        playSound();
    }

    @Override
    protected String getImageName() {
        return imageName + state + ".png";
    }

    private double getFloppingRate() {
        return frameTime/speed;
    }

}
