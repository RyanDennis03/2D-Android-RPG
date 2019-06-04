package edu.angelo.finalprojectdennis.model.tile;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.model.Model;

/**
 * Created by rdennis4 on 12/2/2018.
 */
public abstract class Tile extends Model {

    public Tile(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public abstract Pixmap sprite();

}
