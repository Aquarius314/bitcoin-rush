package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Created by jakub on 02.06.18.
 */

public abstract class GameElement implements Active {

    protected float x, y;

    public void display(Batch batch) {
        batch.draw(getImage(), getCenterX(), getCenterY());
    }

    public void dispose() {
        getImage().dispose();
    }

    public void actions() {
        // do nothing by default
    }

    protected abstract Texture getImage();

    protected abstract String getTextureFilePath();

    public float getWidth() {
        return getImage().getWidth();
    }

    public float getHeight() {
        return getImage().getHeight();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getCenterX() {
        return (int)(x - getWidth()/2);
    }

    public int getCenterY() {
        return (int)(y - getHeight()/2);
    }
}
