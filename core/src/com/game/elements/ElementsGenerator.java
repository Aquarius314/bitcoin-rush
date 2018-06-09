package com.game.elements;

import com.badlogic.gdx.Gdx;
import com.game.JumpGame;

import java.util.List;
import java.util.Random;

public class ElementsGenerator {

    private final List<GameElement> elements;
    private Random random;
    private long diamondsTime;
    private long diamondsTimeRate = 2000;
    private long penguinsTime;
    private long penguinTimeRate = 5000;
    private final int FIRST_PENGUIN_TIME = 6000;
    private final int FIRST_SUPERDIAMOND_TIME = 12000;
    private boolean startGeneratingSuperDiamonds = false;
    private int cloudProbability = 1000;
    private JumpGame game;
    private long creationTime = System.currentTimeMillis();

    public ElementsGenerator(JumpGame game, List<GameElement> elements) {
        this.game = game;
        this.elements = elements;
        random = new Random();
        diamondsTime = System.currentTimeMillis();
        penguinsTime = System.currentTimeMillis() + FIRST_PENGUIN_TIME;
    }

    public void generate() {
        if (random.nextInt()%cloudProbability == 0) {
            elements.add(new Cloud(game, randomX(), randomY()));
            cloudProbability = 400;
        } else if (cloudProbability > 1){
            cloudProbability--;
        }
    }

    public void generatePenguins() {
        elements.add(new Penguin(game, 200, 10000));
    }

    public void generateDiamonds() {
        elements.add(new Diamond(game, 100, 1000));
        elements.add(new Diamond(game, 400, 1800));
    }

    public void generateClouds() {
        elements.add(new Cloud(game, 300, 1200));
    }

    private void generateNewDiamonds(int number) {
        for (int i = 0; i < number; i++) {
            if (random.nextInt()%8 == 1 && startedGeneratingSuperDiamonds()) {
                elements.add(new SuperDiamond(game, randomX(), randomY()));
            } else {
                elements.add(new Diamond(game, randomX(), randomY()));
            }
        }
        diamondsTimeRate = Math.max(diamondsTimeRate - 10, 400);
    }

    public void speedUpPenguinGeneration() {
        penguinTimeRate = Math.max(penguinTimeRate - 100, 400);
    }

    private void generateNewPenguins(int number) {
        for (int i = 0; i < number; i++) {
            elements.add(new Penguin(game, randomX(), randomY()));
        }
    }

    private boolean startedGeneratingSuperDiamonds() {
        if (startGeneratingSuperDiamonds) {
            return true;
        } else {
            if (System.currentTimeMillis() - creationTime > FIRST_SUPERDIAMOND_TIME) {
                startGeneratingSuperDiamonds = true;
                return true;
            }
        }
        return false;
    }

    public void autogenerate() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - penguinsTime > penguinTimeRate) {
            penguinsTime = currentTime;
            generateNewPenguins(1);
        }
        if (currentTime - diamondsTime > diamondsTimeRate) {
            diamondsTime = currentTime;
            generateNewDiamonds(1);
        }
    }

    private int randomX() {
        return random.nextInt()%(Gdx.graphics.getWidth() - 300) + 200;
    }

    private int randomY() {
        return random.nextInt()%100 + 100 + Gdx.graphics.getHeight();
    }

}
