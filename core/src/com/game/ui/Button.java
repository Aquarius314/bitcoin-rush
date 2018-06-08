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
    private TextSprite text;
    private BitmapFont font = new BitmapFont();

    public Button(String textFileName, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        font.setColor(Color.BLACK);
        font.getData().setScale(3);
        this.text = new TextSprite(textFileName, (x + width/2), (y + height/2));
    }

    public boolean touched(float clickX, float clickY) {
        // transform because it's upside-down
        clickY = Gdx.graphics.getHeight() - clickY;
        System.out.println("");
        System.out.println(clickX);
        System.out.println(clickY);
        System.out.println("Space for button:");
        System.out.println(x + " " + (x+width));
        System.out.println(y + " " + (y+height));
        if (clickX > x && clickX < x + width) {
            if (clickY > y && clickY < y + height) {
                System.out.println("YES");
                return true;
            }
        }
        System.out.println("NOPE");
        return false;
    }

    public void display(Renderer renderer) {
        renderer.changeColor(Color.BLACK);
        renderer.frame(x, y, width, height, 6);
        // text
        text.display(renderer);
    }

    public abstract void onClick();

}
