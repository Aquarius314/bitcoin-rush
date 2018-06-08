package com.game.elements;

import com.game.Active;
import com.game.JumpGame;
import com.game.utils.Renderer;

import java.util.List;

public abstract class GameElement implements Active {

    protected float x, y;
    protected boolean active = true;
    protected JumpGame game;
    protected String imageName;

    public GameElement(JumpGame game, float x, float y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public void display(Renderer renderer) {
        renderer.image(getImageName(), getCenterX(), getCenterY());
    }

    public void actions(List<GameElement> otherElements) {
        // do nothing by default
    }

    protected String getImageName() {
        return imageName;
    }

    public float getWidth() {
        return game.getRenderer().getImage(getImageName()).getWidth();
    }

    public float getHeight() {
        return game.getRenderer().getImage(getImageName()).getHeight();
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
