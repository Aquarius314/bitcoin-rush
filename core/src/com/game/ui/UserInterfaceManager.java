package com.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.Plane;

/**
 * Created by jakub on 03.06.18.
 */

public class UserInterfaceManager {

    private Plane player;
    private PointsInfo pointsInfo;

    public UserInterfaceManager(Plane player) {
        this.player = player;
        pointsInfo = new PointsInfo();
    }

    public void display(ShapeRenderer shapeRenderer, Batch batch) {
        pointsInfo.display(shapeRenderer, batch);
    }

    public PointsInfo getPointsInfo() {
        return pointsInfo;
    }

    public void dispose() {

    }

}
