package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.elements.ElementsGenerator;
import com.game.elements.GameElement;
import com.game.elements.Penguin;
import com.game.elements.Plane;
import com.game.elements.Spikes;
import com.game.storage.PreferencesManager;
import com.game.ui.GameInputProcessor;
import com.game.ui.UserInterfaceManager;
import com.game.utils.Renderer;
import com.game.utils.SoundManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JumpGame extends ApplicationAdapter {

    private Renderer renderer;

    private AdHandler handler;
    private com.game.elements.ElementsGenerator generator;
    private int gameState = 1;  // 1 - GAME, 2 - MAIN MENU, 3 - DEAD, 4 - PAUSED
	private List<com.game.elements.GameElement> gameElements;

    private com.game.elements.Plane plane;
    private UserInterfaceManager uiManager;
    private SoundManager soundManager;
	private GameInputProcessor gameInputProcessor;

	private boolean started = false;

	private int bestScore;
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
        bestScore = PreferencesManager.loadScore();
	    gameState = 1;
        gameElements = new ArrayList<com.game.elements.GameElement>();
        plane = new Plane(this, Gdx.graphics.getWidth()/2, 50);
        gameElements.add(plane);
        gameElements.add(new Spikes(this, 0, 0));
        generator = new com.game.elements.ElementsGenerator(this, gameElements);
        generator.generatePenguins();
        generator.generateDiamonds();
        generator.generateClouds();

        uiManager = new UserInterfaceManager(this);
        uiManager.getInGameMenu().setGame(this);
        plane.setUiManager(uiManager);

        started = false;
    }

	@Override
	public void render () {
        controllGameState();
    }

	private void controllGameState() {
        if (gameState == 1 && plane != null && !plane.isActive()) {
            finishGame();
        }
        processGame();
    }

    private void finishGame() {
        Gdx.input.setInputProcessor(uiManager.getInGameMenu().getInputProcessor());
        gameState = 3;
        int currentScore = uiManager.getPointsInfo().getPoints();
        if (currentScore > bestScore) {
            PreferencesManager.saveScore(currentScore);
        }
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
                gamePausedProcess();
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
        renderGame();
        uiManager.getInGameMenu().display(renderer);
    }

    private void gamePausedProcess() {
        renderGame();
    }

    public void pauseGame() {
        gameState = 4;
    }

    public UserInterfaceManager getUiManager() {
	    return uiManager;
    }

    public int getGameState() {
	    return gameState;
    }

    public void resumeGame() {
	    gameState = 1;
	    Gdx.input.setInputProcessor(gameInputProcessor);
    }

    private void renderGame() {
        Gdx.gl.glClearColor(0.4f, 0.4f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        for(com.game.elements.GameElement element : gameElements) {
            element.display(renderer);
        }
        uiManager.display(renderer);
    }

	private void calculate() {
        for (com.game.elements.GameElement element : gameElements) {
            element.actions(gameElements);
        }
        generator.generate();
        removeInactiveElements();
	}

	private void removeInactiveElements() {
	    ArrayList<GameElement> inactives = new ArrayList<com.game.elements.GameElement>();
	    for (GameElement element : gameElements) {
	        if (element.needsRefreshing() || !element.isActive()) {
	            inactives.add(element);
            }
        }
        gameElements.removeAll(inactives);
	    generator.autogenerate();
    }

    public SoundManager getSoundManager() {
	    return soundManager;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Plane getPlane() {
	    return plane;
    }

    public List<Penguin> getPenguins() {
        LinkedList<Penguin> penguins = new LinkedList<Penguin>();
	    for(GameElement g : gameElements) {
	        if (g.getClass().equals(Penguin.class)) {
	            penguins.add((Penguin)g);
            }
        }
        return penguins;
    }

    public int getBestScore() {
	    return bestScore;
    }

    public ElementsGenerator getElementsGenerator() {
	    return generator;
    }

    public boolean isStarted() {
	    return started;
    }

    public void setStarted() {
	    started = true;
    }

	@Override
	public void dispose () {
	    renderer.dispose();
        uiManager.dispose();
		soundManager.dispose();
	}
}
