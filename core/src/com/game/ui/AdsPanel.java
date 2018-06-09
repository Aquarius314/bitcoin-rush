package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.game.utils.Renderer;

public class AdsPanel {

    private final float x, y, width, height;

    public AdsPanel() {
        width = Gdx.graphics.getWidth();
        height = chooseHeight();
        x = 0;
        y = Gdx.graphics.getHeight() - height;
    }

    public void display(Renderer renderer) {
        renderer.changeColor(Color.BLACK);
        renderer.rect(x, y, width, height);
    }

    private float chooseHeight() {
        float screenHeight = Gdx.graphics.getHeight();
        if (screenHeight <= 420) {
            return 60;
        } else if (screenHeight <= 720) {
            return 70;
        } else {
            return 80;
        }
    }

}
