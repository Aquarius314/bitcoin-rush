package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by jakub on 02.06.18.
 */

public class Spikes extends GameElement {

    private static Texture image;

    @Override
    public void display(Batch batch) {
        if (image == null) {
            loadPicture();
        }
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        int displays = height/100 + 1;
        for (int i = 0; i < displays; i++) {
            batch.draw(image, 0 - this.getWidth()/2, i*100);
            batch.draw(image, width - this.getWidth()/2, i*100);
        }
    }

    @Override
    protected Texture getImage() {
        return image;
    }

    @Override
    protected void loadPicture() {
        image = new Texture("spikes.png");
    }
}
