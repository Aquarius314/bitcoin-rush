package com.game.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.Plane;
import com.game.utils.Renderer;

/**
 * Created by jakub on 03.06.18.
 */

public class UserInterfaceManager {

    private Plane player;
    private PointsInfo pointsInfo;
    private InGameMenu inGameMenu;

    public UserInterfaceManager(Plane player) {
        this.player = player;
        pointsInfo = new PointsInfo();
        inGameMenu = new InGameMenu();
    }

    public void display(Renderer renderer) {
        pointsInfo.display(renderer);
    }

    public PointsInfo getPointsInfo() {
        return pointsInfo;
    }

    public InGameMenu getInGameMenu() {
        return inGameMenu;
    }

    public void dispose() {

    }

}
