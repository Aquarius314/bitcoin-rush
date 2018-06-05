package com.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager extends AssetManager<Sound> {

    @Override
    protected void loadAssets() {
        loadSound("penguin_rip1.mp3");
    }

    private void loadSound(String fileName) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/" + fileName));
        assets.put(fileName, sound);
    }

}
