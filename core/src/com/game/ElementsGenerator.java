package com.game;

import com.badlogic.gdx.Gdx;
import com.jump.JumpGame;

import java.util.List;
import java.util.Random;

public class ElementsGenerator {

    private final List<GameElement> elements;
    private Random random;
    private long penguinsTime;
    private final int FIRST_PENGUIN_TIME = 3000;
    private long penguinTimeRate = 6000;
    private int cloudProbability = 1000;
    private JumpGame game;

    public ElementsGenerator(JumpGame game, List<GameElement> elements) {
        this.game = game;
        this.elements = elements;
        random = new Random();
        penguinsTime = System.currentTimeMillis() + FIRST_PENGUIN_TIME;
    }

    public void generate() {
        for(GameElement element : elements) {
            if (element.needsRefreshing()) {
               element.refresh();
            }
        }
        if (random.nextInt()%cloudProbability == 0) {
            elements.add(new Cloud(game, randomX(), randomY()));
            cloudProbability = 500;
        } else if (cloudProbability > 1){
            cloudProbability--;
        }
    }

    public void generatePenguins() {
        elements.add(new Penguin(game, 200, 10000));
    }

    public void generateDiamonds() {
        elements.add(new Diamond(game, 100, 200));
        elements.add(new Diamond(game, 400, 800));
        elements.add(new Diamond(game, 300, 1200));
        elements.add(new Diamond(game, 350, 1400));
    }

    public void generateNewDiamonds(int number) {
        for (int i = 0; i < number; i++) {
            elements.add(new Diamond(game, randomX(), randomY()));
        }
    }

    public void autogenerateNewPenguins() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - penguinsTime > penguinTimeRate) {
            penguinsTime = currentTime;
            penguinTimeRate = random.nextInt()%1000 + 2500;
            elements.add(new Penguin(game, randomX(), randomY()));
        }
    }

    private int randomX() {
        return random.nextInt()%(Gdx.graphics.getWidth() - 200) + 100;
    }

    private int randomY() {
        return random.nextInt()%100 + 100 + Gdx.graphics.getHeight();
    }

}
