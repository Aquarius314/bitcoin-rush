package com.game.storage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by jakub on 09.06.18.
 */

public class PreferencesManager {

    public static int loadScore() {
        Preferences preferences = Gdx.app.getPreferences("Score");
        return preferences.getInteger("score", 0);
    }

    public static void saveScore(int score) {
        Preferences preferences = Gdx.app.getPreferences("Score");
        preferences.putInteger("score", score);
        preferences.flush();
    }

}
