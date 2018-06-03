package com.jump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.Cloud;
import com.game.ElementsGenerator;
import com.game.GameElement;
import com.game.Plane;
import com.game.Spikes;
import com.game.ui.UserInterface;
import com.game.ui.UserInterfaceManager;

import java.util.ArrayList;
import java.util.List;

public class JumpGame extends ApplicationAdapter {

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	AdHandler handler;
	private final boolean DISPLAY_COLLIDERS = false;
	private ElementsGenerator generator;

	private List<GameElement> gameElements;
	private Plane plane;
	private UserInterfaceManager uiManager;

	public JumpGame(AdHandler handler) {
	    this.handler = handler;
    }
	
	@Override
	public void create () {
        gameElements = new ArrayList<GameElement>();
        plane = new Plane(Gdx.graphics.getWidth()/2, 50);
        gameElements.add(plane);
        gameElements.add(new Spikes());
        generator = new ElementsGenerator(gameElements);
        generator.generateClouds();
        generator.generateDiamonds();

		uiManager = new UserInterfaceManager(plane);
		plane.setUiManager(uiManager);
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		for(GameElement element : gameElements) {
            element.display(batch);
        }
        uiManager.display(shapeRenderer, batch);
		batch.end();

		if (DISPLAY_COLLIDERS) {
            plane.displayCollider(shapeRenderer);
        }
        shapeRenderer.end();
        calculate();
	}

	private void calculate() {
        for (GameElement element : gameElements) {
            element.actions(gameElements);
        }
        if (Gdx.input.justTouched()) {
            handleTouch();
        }
        generator.generate();
        removeInactiveElements();
	}

	private void removeInactiveElements() {
	    ArrayList<GameElement> inactives = new ArrayList<GameElement>();
	    for (GameElement element : gameElements) {
	        if (!element.isActive()) {
	            inactives.add(element);
            }
        }
        gameElements.removeAll(inactives);
	    generator.generateNewDiamonds(inactives.size());
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
        uiManager.dispose();
	}
}
