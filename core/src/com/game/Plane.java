package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.ui.UserInterfaceManager;

import java.util.List;

public class Plane extends GameElement {

    private boolean flyingLeft = true;
    protected float speed = 0;
    private static Texture image;
    private UserInterfaceManager uiManager;

    public Plane(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setUiManager(UserInterfaceManager uiManager) {
        this.uiManager = uiManager;
    }

    public void displayCollider(ShapeRenderer renderer) {
        renderer.setColor(0, 1, 0, 1);
        renderer.circle(getCenterX()+getWidth()/2, getCenterY()+getHeight()/2, getWidth()/2);
    }

    @Override
    public void actions(List<GameElement> otherElements) {
        move();
        checkWalls();
        for(GameElement element : otherElements) {
            if (element.isCollectable()) {
                checkCollisionWith(element);
            }
        }
    }

    private void checkCollisionWith(GameElement element) {
        double elementRadius = (element.getWidth()+element.getHeight())/4d;
        double ownRadius = (getWidth()+getHeight())/4d;
        double distance = Math.sqrt(Math.pow(x - element.getX(), 2) + Math.pow(y - element.getY(), 2));
        if (distance < elementRadius + ownRadius) {
            collect(element);
        }
    }

    private void collect(GameElement element) {
        element.setActive(false);
        uiManager.getPointsInfo().addPoint();
    }

    private void move() {
        float breakingSpeed = 0.2f;
        float accelerateSpeed = 0.1f;
        x += speed;
        if (flyingLeft) {
            if (speed >= 0) {   // flying left, but speed to right: breaking
                speed -= breakingSpeed;
            } else {    // flying left, accelerating
                speed -= accelerateSpeed;
            }
        } else {
            if (speed <= 0 ) {  // flying right, but speed to left: breaking
                speed += breakingSpeed;
            } else {    // flying right, accelerating
                speed += accelerateSpeed;
            }
        }
    }

    private void checkWalls() {
        if (getCenterX() <= 0 || getCenterX() >= Gdx.graphics.getWidth()-getWidth()) {
            x = Gdx.graphics.getWidth()/2;
        }
    }

    @Override
    protected Texture getImage() {
        return image;
    }

    @Override
    protected void loadPicture() {
        image = new Texture("plane.png");
    }

    public void turn() {
        flyingLeft = !flyingLeft;
    }
}
