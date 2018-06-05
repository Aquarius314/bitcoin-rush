package com.game.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.Arrays;

/**
 * Created by jakub on 05.06.18.
 */

public class ImageManager extends AssetManager<Texture> {

    @Override
    protected void loadAssets() {
        for(String name : Arrays.asList(
                "avoid_penguins.png",
                "btc.png",
                "cloud1.png",
                "dont_hit_the_spikes.png",
                "penguin0.png",
                "penguin1.png",
                "plane.png",
                "retry.png",
                "return.png",
                "spikes.png"
                )) {
            loadImage(name);
        }
    }

    public void loadImage(String fileName) {
        try {
            Texture image = new Texture(fileName);
            assets.put(fileName, image);
        } catch (GdxRuntimeException e) {
            throw new ResourceNotFoundException(fileName);
        }
    }
}
