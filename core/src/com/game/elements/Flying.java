package com.game.elements;

import com.badlogic.gdx.Gdx;
import com.game.JumpGame;

import java.util.List;
import java.util.Random;

public abstract class Flying extends GameElement {

    protected int speed = 7;

    public Flying(JumpGame game, int x, int y) {
        super(game, x, y);
    }

    @Override
    public void actions(List<GameElement> otherElements) {
        this.y -= speed;
    }

}
