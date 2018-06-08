package com.game.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.game.JumpGame;
import com.game.ui.UserInterfaceManager;
import com.game.utils.Renderer;

import java.util.List;

public class Plane extends GameElement {

    private boolean flyingLeft = true;
    private float speed = 0;
    private final float MAX_ROTATION = 80;
    private UserInterfaceManager uiManager;
    private Sprite imgSprite;
    private JumpGame game;

    public Plane(JumpGame game, int x, int y) {
        super(game, x, y);
        imageName = "plane.png";
    }

    public void setUiManager(UserInterfaceManager uiManager) {
        this.uiManager = uiManager;
    }

    public UserInterfaceManager getUiManager() {
        return uiManager;
    }

    @Override
    public void display(Renderer renderer) {
        float rotation = -speed*8;
        if (rotation < -MAX_ROTATION) {
            rotation = -MAX_ROTATION;
        } else if (rotation > MAX_ROTATION) {
            rotation = MAX_ROTATION;
        }
        if (imgSprite == null) {
            imgSprite = new Sprite(renderer.getImage(imageName));
        }
        imgSprite.setRotation(rotation);
        imgSprite.setPosition(getCenterX(), getCenterY());
        renderer.sprite(imgSprite);
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
            hitTheWall();
        }
    }

    private void hitTheWall() {
        uiManager.getInGameMenu().setHitBySpikes(true);
        active = false;
    }

    public void turn() {
        flyingLeft = !flyingLeft;
    }

    public void die() {
        active = false;
        getUiManager().getInGameMenu().setHitByPenguin(true);
    }
}
