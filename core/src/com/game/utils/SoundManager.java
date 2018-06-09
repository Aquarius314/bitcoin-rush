package com.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import java.util.Arrays;

public class SoundManager extends AssetManager<Sound> {

    @Override
    protected void loadAssets() {
        for(String name : Arrays.asList("penguin_rip1.mp3", "coin.mp3", "you_died.mp3")) {
            loadSound(name);
        }
    }

    private void loadSound(String fileName) {
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("sound/" + fileName));
        assets.put(fileName, sound);
    }

    public void playSound(String soundName) {
        playSound(soundName, 1.0f);
    }

    public void playSound(String soundName, float pitch) {
        Sound sound = assets.get(soundName);
        long id = sound.play();
        sound.setPitch(id, pitch);
    }

}
