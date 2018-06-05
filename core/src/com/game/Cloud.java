package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jump.JumpGame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jakub on 02.06.18.
 */

public class Cloud extends Flying {

    public Cloud(JumpGame game, int x, int y) {
        super(game, x, y);
        imageName = "cloud1.png";
    }

    @Override
    public void refresh() {
        super.refresh();
    }
}
