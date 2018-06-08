package com.game;

import java.util.List;

/**
 * Created by jakub on 02.06.18.
 */

public interface Active {

    void actions(List<com.game.elements.GameElement> otherElements);
}
