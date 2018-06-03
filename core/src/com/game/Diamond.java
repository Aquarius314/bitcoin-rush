package com.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by jakub on 02.06.18.
 */

public class Diamond extends Flying {

    private static Texture image;

    public Diamond(int x, int y) {
        super(x, y);
        collectable = true;
    }

    @Override
    protected Texture getImage() {
        return image;
    }

    @Override
    protected void loadPicture() {
        image = new Texture("diamond.png");
    }
}
