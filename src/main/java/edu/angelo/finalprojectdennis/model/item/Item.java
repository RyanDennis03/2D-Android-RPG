package edu.angelo.finalprojectdennis.model.item;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.model.Model;

/**
 * Represents an item in the game.
 */
public abstract class Item extends Model {

    //default constructor:
    public Item(int x, int y) {
        xPos = x;
        yPos = y;
    }

    //The item sprite:
    public abstract Pixmap sprite();

}
