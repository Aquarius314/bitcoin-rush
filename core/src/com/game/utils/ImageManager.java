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
                "btc.png",
                "sbtc0.png",
                "sbtc1.png",
                "sbtc2.png",
                "sbtc3.png",
                "sbtc4.png",
                "cloud1.png",
                "penguin0.png",
                "penguin1.png",
                "plane.png",
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
