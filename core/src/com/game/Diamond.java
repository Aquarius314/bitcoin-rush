package com.game;

import com.jump.JumpGame;

public class Diamond extends Flying {

    public Diamond(JumpGame game, int x, int y) {
        super(game, x, y);
        imageName = "btc.png";
    }

    @Override
    public void collideWith(Plane plane) {
        plane.getUiManager().getPointsInfo().addPoint();
        active = false;
    }
}
