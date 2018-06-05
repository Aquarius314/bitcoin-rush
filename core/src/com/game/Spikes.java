package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.game.utils.Renderer;
import com.jump.JumpGame;

/**
 * Created by jakub on 02.06.18.
 */

public class Spikes extends GameElement {

    public Spikes(JumpGame game, float x, float y) {
        super(game, x, y);
        imageName = "spikes.png";
    }

    @Override
    public void display(Renderer renderer) {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        int displays = height/100 + 1;
        for (int i = 0; i < displays; i++) {
            renderer.image(getImageName(), 0 - this.getWidth()/2, i*100);
            renderer.image(getImageName(), width - this.getWidth()/2, i*100);
        }
    }

}
