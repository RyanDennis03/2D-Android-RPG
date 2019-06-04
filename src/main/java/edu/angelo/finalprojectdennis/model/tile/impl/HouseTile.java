package edu.angelo.finalprojectdennis.model.tile.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.tile.Tile;

/**
 * Created by rdennis4 on 12/2/2018.
 */
public class HouseTile extends Tile {

    public HouseTile(int x, int y) {
        super(x, y);
    }

    @Override
    public Pixmap sprite() {
        return Assets.house_tile;
    }

}
