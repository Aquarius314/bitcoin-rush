package com.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by jakub on 02.06.18.
 */

public class Diamond extends Flying {

    private static Texture image;

    public Diamond(int x, int y) {
        super(x, y);
    }

    @Override
    public void collideWith(Plane plane) {
        plane.getUiManager().getPointsInfo().addPoint();
        active = false;
    }

    @Override
    protected Texture getImage() {
        return image;
    }

    @Override
    protected void loadPicture() {
        image = new Texture("btc.png");
    }
}
