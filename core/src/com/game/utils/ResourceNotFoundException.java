package com.game.utils;

/**
 * Created by jakub on 06.06.18.
 */

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String s) {
        super("Could not find asset " + s);
    }
}
