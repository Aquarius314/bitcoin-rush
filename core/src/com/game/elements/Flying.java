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

    @Override
    public void refresh() {
        Random random = new Random();
        y = Gdx.graphics.getHeight() + random.nextInt()%100 + getHeight();
        x = random.nextInt()%(Gdx.graphics.getWidth() - 100) + 50;
        speed = random.nextInt()%4 + 7;
    }

}
