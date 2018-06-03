package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.List;

/**
 * Created by jakub on 02.06.18.
 */

public abstract class GameElement implements Active {

    protected float x, y;
    protected boolean active = true;

    public void display(Batch batch) {
        if (getImage() == null) {
            loadPicture();
        }
        batch.draw(getImage(), getCenterX(), getCenterY());
    }

    public void dispose() {
        getImage().dispose();
    }

    public void actions(List<GameElement> otherElements) {
        // do nothing by default
    }

    protected abstract Texture getImage();

    protected abstract void loadPicture();

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

    public boolean needsRefreshing() {
        return getY() + getHeight() < 0;
    }

    public void refresh() {
        // nothing by default
    }

    public void collideWith(Plane plane) {
        // nothing by default
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
