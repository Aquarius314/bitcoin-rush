package com.jump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class JumpGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	AdHandler handler;
	boolean toggle;
	private int x = 0, y = 0;

	public JumpGame(AdHandler handler) {
	    this.handler = handler;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		calculate();
		Gdx.gl.glClearColor(0.4f, 0.2f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}

	private void calculate() {
        x += 1;
        y += 1;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
