package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.ui.UserInterfaceManager;

import java.util.List;

public class Plane extends GameElement {

    private boolean flyingLeft = true;
    private float speed = 0;
    private final float MAX_ROTATION = 80;
    private static Texture image;
    private UserInterfaceManager uiManager;
    private Sprite imgSprite;

    public Plane(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setUiManager(UserInterfaceManager uiManager) {
        this.uiManager = uiManager;
    }

    public UserInterfaceManager getUiManager() {
        return uiManager;
    }

    @Override
    public void display(Batch batch) {
        if (getImage() == null) {
            loadPicture();
        }
        float rotation = -speed*8;
        if (rotation < -MAX_ROTATION) {
            rotation = -MAX_ROTATION;
        }
        if (rotation > MAX_ROTATION) {
            rotation = MAX_ROTATION;
        }
        imgSprite.setRotation(rotation);
        imgSprite.setPosition(getCenterX(), getCenterY());
        imgSprite.draw(batch);
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
            checkCollisionWith(element);
        }
    }

    private void checkCollisionWith(GameElement element) {
        double elementRadius = (element.getWidth()+element.getHeight())/4d;
        double ownRadius = (getWidth()+getHeight())/4d;
        double distance = Math.sqrt(Math.pow(x - element.getX(), 2) + Math.pow(y - element.getY(), 2));
        if (distance < elementRadius + ownRadius) {
            element.collideWith(this);
        }
    }

//    private void collideWith(GameElement element) {
//        element.setActive(false);
//        uiManager.getPointsInfo().addPoint();
//    }

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
        if (getCenterX() <= 5 || getCenterX() >= Gdx.graphics.getWidth()-getWidth() - 5) {
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
        imgSprite = new Sprite(image);
    }

    public void turn() {
        flyingLeft = !flyingLeft;
    }
}
