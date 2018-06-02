package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Plane extends GameElement {

    private boolean flyingLeft = true;
    protected float speed = 1;
    private static Texture image;

    public Plane(int x, int y) {
        this.x = x;
        this.y = y;
        image = new Texture(getTextureFilePath());
    }

    public void displayCollider(ShapeRenderer renderer) {
        renderer.setColor(0, 1, 0, 1);
        renderer.circle(getCenterX()+getWidth()/2, getCenterY()+getHeight()/2, getWidth()/2);
    }

    @Override
    protected String getTextureFilePath() {
        return "plane.png";
    }

    @Override
    public void actions() {
        x -= flyingLeft ? speed : -speed;
    }

    @Override
    protected Texture getImage() {
        return image;
    }

    public void turn() {
        flyingLeft = !flyingLeft;
    }
}
