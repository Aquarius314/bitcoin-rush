package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by jakub on 03.06.18.
 */

public class PointsInfo implements UserInterface {

    private int points = 0;
    BitmapFont font;

    public PointsInfo() {
        font = new BitmapFont();
        font.getData().setScale(3);
    }

    public void addPoint() {
        points++;
    }

    @Override
    public void display(ShapeRenderer shapeRenderer, Batch batch) {
        Color color = batch.getColor();
        batch.setColor(Color.YELLOW);
        font.draw(batch, "POINTS: " + points, 10, Gdx.graphics.getHeight() - 100);
        batch.setColor(color);
    }
}
