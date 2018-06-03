package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jakub on 02.06.18.
 */

public class Cloud extends Flying {

    private static ArrayList<Texture> images;
    private static int typeCounter = 0;
    private int type;

    public Cloud(int x, int y) {
        super(x, y);
        if (images == null) {
            loadPicture();
        }
        type = typeCounter%4;
        typeCounter++;
    }

    @Override
    protected Texture getImage() {
        return images.get(type);
    }

    @Override
    protected void loadPicture() {
        images = new ArrayList<Texture>();
        for(int i = 1; i <= 4; i++) {
            images.add(new Texture("cloud" + Integer.toString(i) + ".png"));
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        type = typeCounter%4;
        typeCounter++;
    }
}
