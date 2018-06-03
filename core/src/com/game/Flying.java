package com.game;

import com.badlogic.gdx.Gdx;

import java.util.List;
import java.util.Random;

/**
 * Created by jakub on 02.06.18.
 */

public abstract class Flying extends GameElement {

    protected int speed = 7;

    public Flying(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void actions(List<GameElement> otherElements) {
        this.y -= speed;
    }

    @Override
    public void refresh() {
        Random random = new Random();
        y = Gdx.graphics.getHeight() + random.nextInt()%100 + getHeight();
        x = (int)(random.nextInt()%(Gdx.graphics.getWidth() - 100)) + 50;
        speed = random.nextInt()%4 + 6;
    }

}
