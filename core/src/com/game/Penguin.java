package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jakub on 03.06.18.
 */

public class Penguin extends Flying {

    private long timing;
    private long frameTime = 500;
    private int state = 0;
    private static List<Texture> images;

    public Penguin(int x, int y) {
        super(x, y);
        timing = System.currentTimeMillis();
        if (images == null) {
            loadPicture();
        }
    }

    @Override
    public void collideWith(Plane plane) {
        plane.getUiManager().getPointsInfo().clearPoints();
        active = false;
    }

    @Override
    public void display(Batch batch) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - timing > frameTime) {
            timing = currentTime;
            state++;
            state = state%images.size();
        }
        batch.draw(getImage(), getCenterX(), getCenterY());
    }

    @Override
    protected Texture getImage() {
        return images.get(state);
    }

    @Override
    protected void loadPicture() {
        images = new ArrayList<Texture>();
        images.add(new Texture("penguin1.png"));
        images.add(new Texture("penguin2.png"));
    }
}
