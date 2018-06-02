package com.jump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.GameElement;
import com.game.Plane;
import com.game.Spikes;

import java.util.ArrayList;
import java.util.List;

public class JumpGame extends ApplicationAdapter {

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	AdHandler handler;
	private final boolean DISPLAY_COLLIDERS = false;

	private List<GameElement> gameElements;
	private Plane plane;

	public JumpGame(AdHandler handler) {
	    this.handler = handler;
    }
	
	@Override
	public void create () {
        gameElements = new ArrayList<GameElement>();
        plane = new Plane(Gdx.graphics.getWidth()/2, 50);
        gameElements.add(plane);
        gameElements.add(new Spikes());

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		calculate();
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for(GameElement element : gameElements) {
            element.display(batch);
        }
		batch.end();
		if (DISPLAY_COLLIDERS) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            plane.displayCollider(shapeRenderer);
            shapeRenderer.end();
        }
	}

	private void calculate() {
        for (GameElement element : gameElements) {
            element.actions();
        }
        if (Gdx.input.justTouched()) {
            handleTouch();
        }
	}

	private void handleTouch() {
	    plane.turn();
    }
	
	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		for (GameElement element : gameElements) {
		    element.dispose();
        }
	}
}
