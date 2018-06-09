package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.game.utils.Renderer;
import com.game.JumpGame;

public class InGameMenu implements UserInterface {

    private Color menuColor = new Color(0.2f, 0, 0.9f, 1);

    private InGameMenuInputProcessor inGameMenuInputProcessor;

    private boolean hitByPenguin = false;
    private boolean hitBySpikes = false;
    private boolean paused = false;

    private JumpGame game;

    private float frameSize, frameX, frameY;
    private float messageY;

    public InGameMenu() {
        float x = Gdx.graphics.getWidth()/2 - 150;
        float y = Gdx.graphics.getHeight()/2 - 150;
        float width = 300;
        float height = 180;
        inGameMenuInputProcessor = new InGameMenuInputProcessor(this);
        messageY = y + height*2 - 160;

        frameSize = Gdx.graphics.getWidth()*0.8f;
        frameX = frameSize/8;
        frameY = Gdx.graphics.getHeight()/2 - frameSize/2;
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
        int score = game.getUiManager().getPointsInfo().getPoints();
        if (hitByPenguin) {
            displayMessage("Avoid penguins!", renderer, messageY + 120);
            displayMessage("Your final score was: " + score, renderer, messageY);
        } else if (hitBySpikes){
            displayMessage("Don't hit the spikes!", renderer, messageY + 120);
            displayMessage("Your final score was: " + score, renderer, messageY);
        }
        displayMessage("Tap to try again!", renderer, messageY - 120);
    }

    private void displayMessage(String message, Renderer renderer, float messageY) {
        renderer.text(message, messageY);
    }

    public void handleTouch() {
        game.initGame();
    }
}
