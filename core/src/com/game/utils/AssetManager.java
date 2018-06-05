package com.game.utils;

import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

public abstract class AssetManager<T extends Disposable> {

    protected final HashMap<String, T> assets;

    public AssetManager() {
        assets = new HashMap<String, T>();
        loadAssets();
    }

    protected abstract void loadAssets();

    public T getAsset(String filePath) {
        if (assets.containsKey(filePath)) {
            return assets.get(filePath);
        } else {
            throw new ResourceNotFoundException(filePath);
        }
    }

    public void dispose() {
        for(Disposable d : assets.values()) {
            d.dispose();
        }
    }

}
