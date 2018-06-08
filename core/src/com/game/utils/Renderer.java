package com.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by jakub on 04.06.18.
 */

public class Renderer {

    private Batch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private ImageManager imagesManager;

    public Renderer(Batch batch, ShapeRenderer shapeRenderer) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        imagesManager = new ImageManager();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    public void changeColor(Color color) {
        shapeRenderer.setColor(color);
    }

    public void image(String imageName, float x, float y) {
        batch.begin();
        batch.draw(imagesManager.getAsset(imageName), x, y);
        batch.end();
    }

    public Texture getImage(String imageName) {
        return imagesManager.getAsset(imageName);
    }

    public void sprite(Sprite sprite) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void rect(float x, float y, float width, float height) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    public void changeFontSize(float newSize) {
        font.getData().setScale(newSize);
    }

    public void text(String text, float x, float y) {
        batch.begin();
        font.draw(batch, text, x, y);
        batch.end();
    }

    public void text(String text, float x, float y, float scale) {
        font.getData().setScale(scale);
        text(text, x, y);
    }

    public void frame(float x, float y, float width, float height, int lineWidth) {
        rect(x, y, width, lineWidth);
        rect(x, y, lineWidth, height);
        rect(x, y+height-lineWidth, width, lineWidth);
        rect(x+width-lineWidth, y, lineWidth, height);
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        imagesManager.dispose();
    }

}
