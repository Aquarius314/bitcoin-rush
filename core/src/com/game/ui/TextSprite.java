package com.game.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.utils.Renderer;

/**
 * Created by jakub on 04.06.18.
 */

public class TextSprite {

    private float centerX, centerY;
    private String filename;

    public TextSprite(String filename, float centerX, float centerY) {
        this.filename = filename;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void display(Renderer renderer) {
        float x = centerX - renderer.getImage(filename).getWidth()/2;
        float y = centerY - renderer.getImage(filename).getHeight()/2;
        renderer.image(filename, x, y);
    }

}
