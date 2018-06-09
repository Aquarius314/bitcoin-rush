package com.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jakub on 04.06.18.
 */

public class Renderer {

    private Batch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    private Map<String, Float> textPositions;
    private final float FONT_SCALE = 2.5f;
    private final int SCREEN_WIDTH, SCREEN_HEIGHT;

    private ImageManager imagesManager;

    public Renderer(Batch batch, ShapeRenderer shapeRenderer) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        imagesManager = new ImageManager();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(FONT_SCALE);
        glyphLayout = new GlyphLayout();
        textPositions = new HashMap<String, Float>();
        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
    }

    public void changeColor(Color color) {
        shapeRenderer.setColor(color);
    }

    public void image(String imageName, float x, float y) {
        // render only if visible
        Texture texture = imagesManager.getAsset(imageName);
        if (isVisible(texture, x, y)) {
            batch.begin();
            batch.draw(texture, x, y);
            batch.end();
        }
    }

    private boolean isVisible(Texture texture, float x, float y) {
        if (x > SCREEN_WIDTH) return false;
        if (y > SCREEN_HEIGHT) return false;
        if (x + texture.getWidth() < 0) return false;
        if (y + texture.getHeight() < 0) return false;
        return true;
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

    public void text(String text, float x, float y) {
        batch.begin();
        font.draw(batch, text, x, y);
        batch.end();
    }

    public void textInBounds(String text, float y, float boundX, float boundWidth) {
        float x = getTextAutoX(text, boundX, boundWidth);
        text(text, x, y);
    }

    public void text(String text, float y) {
        // auto scale and auto x
        float x = getTextAutoX(text, 0, Gdx.graphics.getWidth());
        text(text, x, y);
    }

    private float getTextAutoX(String text, float boundX, float boundWidth) {
        if (textPositions.containsKey(text)) {
            return textPositions.get(text);
        } else {
            glyphLayout.setText(font, text);
            float width = glyphLayout.width;
            float x = (boundWidth - width)/2 + boundX;
            textPositions.put(text, x);
            return x;
        }
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
