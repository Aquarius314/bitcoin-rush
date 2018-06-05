package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.game.utils.Renderer;

public class PointsInfo implements UserInterface {

    private int points = 0;

    public void addPoint() {
        points++;
    }

    public void clearPoints() {
        points = 0;
    }

    @Override
    public void display(Renderer renderer) {
        renderer.text("$: " + points, 10, Gdx.graphics.getHeight() - 100);
    }
}
