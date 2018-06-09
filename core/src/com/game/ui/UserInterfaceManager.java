package com.game.ui;

import com.game.JumpGame;
import com.game.utils.Renderer;

public class UserInterfaceManager {

    private PointsInfo pointsInfo;
    private InGameMenu inGameMenu;
    private AdsPanel adsPanel;

    public UserInterfaceManager(JumpGame game) {
        pointsInfo = new PointsInfo(game.getBestScore());
        inGameMenu = new InGameMenu();
        adsPanel = new AdsPanel();
    }

    public void display(Renderer renderer) {
        pointsInfo.display(renderer);
        adsPanel.display(renderer);
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
