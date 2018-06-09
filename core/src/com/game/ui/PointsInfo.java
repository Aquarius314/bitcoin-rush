package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.game.utils.Renderer;

public class PointsInfo implements UserInterface {

    private int bestScore;
    private int points = 0;

    public PointsInfo(int bestScore) {
        this.bestScore = bestScore;
    }

    public void addPoint() {
        points++;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void display(Renderer renderer) {
        renderer.text("SCORE: " + points + "   BEST: " + bestScore, 10, Gdx.graphics.getHeight() - 100);
    }
}
