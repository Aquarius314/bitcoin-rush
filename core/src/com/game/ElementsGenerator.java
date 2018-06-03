package com.game;

import com.badlogic.gdx.Gdx;

import java.util.List;
import java.util.Random;

/**
 * Created by jakub on 02.06.18.
 */

public class ElementsGenerator {

    private final List<GameElement> elements;
    private Random random;

    public ElementsGenerator(List<GameElement> elements) {
        this.elements = elements;
        random = new Random();
    }

    public void generate() {
        for(GameElement element : elements) {
            if (element.needsRefreshing()) {
               element.refresh();
            }
        }
    }

    public void generateClouds() {
//        don't
//        elements.add(new Cloud(100, 2000));
//        elements.add(new Cloud(200, -200));
//        elements.add(new Cloud(300, 800));
//        elements.add(new Cloud(400, 1400));
    }

    public void generateDiamonds() {
        elements.add(new Diamond(100, 200));
        elements.add(new Diamond(400, 800));
        elements.add(new Diamond(300, 1200));
        elements.add(new Diamond(200, 1400));
    }

    public void generateNewDiamonds(int number) {
        for (int i = 0; i < number; i++) {
            elements.add(new Diamond(randomX(), randomY()));
        }
    }

    private int randomX() {
        return random.nextInt()%(Gdx.graphics.getWidth() - 200) + 100;
    }

    private int randomY() {
        return random.nextInt()%100 + 100 + Gdx.graphics.getHeight();
    }

}
