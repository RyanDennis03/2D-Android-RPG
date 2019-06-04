package edu.angelo.finalprojectdennis.model.tile.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.tile.Tile;

public class WaterEdgeUp extends Tile {

    public WaterEdgeUp(int x, int y) {
        super(x, y);
    }

    @Override
    public Pixmap sprite() {
        return Assets.water_up;
    }
}
