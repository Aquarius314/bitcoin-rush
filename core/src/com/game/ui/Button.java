package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.utils.Renderer;

/**
 * Created by jakub on 03.06.18.
 */

public abstract class Button {

    private float x, y, width, height;
    private final String text;

    public Button(String text, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public boolean touched(float clickX, float clickY) {
        // transform because it's upside-down
        clickY = Gdx.graphics.getHeight() - clickY;
        if (clickX > x && clickX < x + width) {
            if (clickY > y && clickY < y + height) {
                return true;
            }
        }
        return false;
    }

    public void display(Renderer renderer) {
        renderer.changeColor(Color.BLACK);
        renderer.frame(x, y, width, height, 6);
        // text
        renderer.textInBounds(text, y + 65, x, width);
    }

    public abstract void onClick();

}
