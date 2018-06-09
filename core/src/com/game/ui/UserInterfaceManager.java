package com.game.ui;

import com.badlogic.gdx.Gdx;
import com.game.JumpGame;
import com.game.elements.Plane;
import com.game.utils.Renderer;

import java.util.LinkedList;
import java.util.List;

public class UserInterfaceManager {

    private final JumpGame game;
    private PointsInfo pointsInfo;
    private InGameMenu inGameMenu;
    private List<Button> buttons;

    public UserInterfaceManager(final JumpGame game) {
        this.game = game;
        pointsInfo = new PointsInfo();
        inGameMenu = new InGameMenu();
        buttons = new LinkedList<Button>();
        float x = Gdx.graphics.getWidth() - 200;
        float y = Gdx.graphics.getHeight() - 180;
        buttons.add(new Button("STOP", x, y, 200, 100) {
            @Override
            public void onClick() {
                if(game.getGameState() == 1) {
                    game.pauseGame();
                } else if (game.getGameState() == 4){
                    game.resumeGame();
                }
            }
        });
    }

    public void display(Renderer renderer) {
        pointsInfo.display(renderer);
        for (Button b : buttons) {
            b.display(renderer);
        }
    }

    public boolean checkButtonsClick(float x, float y) {
        boolean anyClick = false;
        for (Button b : buttons) {
            if (b.touched(x, y)) {
                b.onClick();
                anyClick = true;
            }
        }
        return anyClick;
    }

    public PointsInfo getPointsInfo() {
        return pointsInfo;
    }

    public InGameMenu getInGameMenu() {
        return inGameMenu;
    }

    public void dispose() {

    }

}
