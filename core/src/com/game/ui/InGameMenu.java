package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.game.utils.Renderer;
import com.jump.JumpGame;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by jakub on 03.06.18.
 */

public class InGameMenu implements UserInterface {

    private Color menuColor = new Color(0.2f, 0, 0.9f, 1);

    private InGameMenuInputProcessor inGameMenuInputProcessor;

    private TextSprite dontHitTheSpikes;
    private TextSprite avoidPenguins;
    private boolean hitByPenguin = false;
    private boolean hitBySpikes = false;
    private boolean paused = false;

    private LinkedList<Button> buttons;
    private JumpGame game;

    private float frameSize, frameX, frameY;

    public InGameMenu() {
        float x = Gdx.graphics.getWidth()/2 - 150;
        float y = Gdx.graphics.getHeight()/2 - 150;
        float width = 300;
        float height = 180;
        buttons = new LinkedList<Button>();
        initRetryButton(x, y, width, height);
        inGameMenuInputProcessor = new InGameMenuInputProcessor(this);
        float messageX = x + width/2;
        float messageY = y + height*2 - 100;
        dontHitTheSpikes = new TextSprite("dont_hit_the_spikes.png", messageX, messageY);
        avoidPenguins = new TextSprite("avoid_penguins.png", messageX, messageY);

        frameSize = Gdx.graphics.getWidth()*0.8f;
        frameX = frameSize/8;
        frameY = Gdx.graphics.getHeight()/2 - frameSize/2;
    }

    private void initRetryButton(float x, float y, float width, float height) {
        buttons.add(new Button("retry.png", x, y, width, height) {
            @Override
            public void onClick() {
                game.initGame();
            }
        });
    }

    public void setHitByPenguin(boolean hitByPenguin) {
        this.hitByPenguin = hitByPenguin;
    }

    public void setHitBySpikes(boolean hitBySpikes) {
        this.hitBySpikes = hitBySpikes;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setGame(JumpGame game) {
        this.game = game;
    }

    public InGameMenuInputProcessor getInputProcessor() {
        return inGameMenuInputProcessor;
    }

    @Override
    public void display(final Renderer renderer) {
        renderer.changeColor(menuColor);
        renderer.rect(frameX, frameY, frameSize, frameSize);
        renderer.changeColor(Color.BLACK);
        renderer.frame(frameX, frameY, frameSize, frameSize, 10);
        if (hitByPenguin) {
            avoidPenguins.display(renderer);
        } else if (hitBySpikes){
            dontHitTheSpikes.display(renderer);
        } else if (paused) {
            // TODO message to resume game
        }
        for(Button button : buttons) {
            button.display(renderer);
        }
    }

    public void handleTouch(float touchX, float touchY) {
        for(Button button : buttons) {
            if (button.touched(touchX, touchY)) {
                button.onClick();
            }
        }
    }
}
