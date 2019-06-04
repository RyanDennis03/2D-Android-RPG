package edu.angelo.finalprojectdennis.model.object.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.object.GameObject;

/**
 * I decided to make Water into a "GameObject" to avoid clipping
 * issues since "Tiles" are walkable by default in my game.
 */
public class Water extends GameObject {

    public Water(int x, int y) {
        super(x, y);
    }

    @Override
    public Pixmap sprite() {
        return Assets.water;
    }

    @Override
    public int xSize() {
        return 1;
    }

    @Override
    public int ySize() {
        return 1;
    }
}
