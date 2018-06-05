package com.jump;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.ElementsGenerator;
import com.game.GameElement;
import com.game.Plane;
import com.game.Spikes;
import com.game.ui.GameInputProcessor;
import com.game.ui.UserInterfaceManager;
import com.game.utils.Renderer;
import com.game.utils.SoundManager;

import java.util.ArrayList;
import java.util.List;

public class JumpGame extends ApplicationAdapter {

    private Renderer renderer;

    AdHandler handler;
    private ElementsGenerator generator;
    private int gameState = 1;  // 1 - GAME, 2 - MAIN MENU, 3 - DEAD, 4 - PAUSED
	private List<GameElement> gameElements;

    private Plane plane;
    private UserInterfaceManager uiManager;
    private SoundManager soundManager;
	private GameInputProcessor gameInputProcessor;

	public JumpGame(AdHandler handler) {
	    this.handler = handler;
    }

	@Override
	public void create () {
        gameInputProcessor = new GameInputProcessor(this);
        soundManager = new SoundManager();
	    initGame();
		renderer = new Renderer(new SpriteBatch(), new ShapeRenderer());
	}

	public void initGame() {
        Gdx.input.setInputProcessor(gameInputProcessor);
	    gameState = 1;
        gameElements = new ArrayList<GameElement>();
        plane = new Plane(this, Gdx.graphics.getWidth()/2, 50);
        gameElements.add(plane);
        gameElements.add(new Spikes(this, 0, 0));
        generator = new ElementsGenerator(this, gameElements);
        generator.generatePenguins();
        generator.generateDiamonds();

        uiManager = new UserInterfaceManager(plane);
        uiManager.getInGameMenu().setGame(this);
        plane.setUiManager(uiManager);
    }

	@Override
	public void render () {
        controllGameState();
	}

	private void controllGameState() {
        if (gameState == 1 && plane != null && !plane.isActive()) {
            gameState = 3;
            Gdx.input.setInputProcessor(uiManager.getInGameMenu().getInputProcessor());
        }
        processGame();
    }

    private void processGame() {
        switch (gameState) {
            case 1: // normal game
                normalGameProcess();
                break;
            case 2: // main menu
                mainMenuProcess();
                break;
            case 3: // dead
                playerDeadProcess();
                break;
            case 4: // paused
                gamePauseProcess();
                break;
        }
    }

    private void normalGameProcess() {
	    renderGame();
	    calculate();
    }

    private void mainMenuProcess() {

    }

    private void playerDeadProcess() {
        System.out.println("PROCESS");
        renderGame();
        uiManager.getInGameMenu().display(renderer);
    }

    private void gamePauseProcess() {
        renderGame();
    }

    private void renderGame() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(GameElement element : gameElements) {
            element.display(renderer);
        }
        uiManager.display(renderer);
    }

	private void calculate() {
        for (GameElement element : gameElements) {
            element.actions(gameElements);
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
	    generator.autogenerateNewPenguins();
    }

	public void handleTouch() {
	    plane.turn();
    }

    public SoundManager getSoundManager() {
	    return soundManager;
    }

    public Renderer getRenderer() {
        return renderer;
    }

	@Override
	public void dispose () {
	    renderer.dispose();
        uiManager.dispose();
		soundManager.dispose();
	}
}
