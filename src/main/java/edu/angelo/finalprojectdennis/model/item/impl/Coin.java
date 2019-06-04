package edu.angelo.finalprojectdennis.model.item.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.item.Item;

/**
 * Represents a coin in the game.
 * If a player picks up a coin, his score goes up by 1.
 */
public class Coin extends Item {

    public Coin(int x, int y) {
        super(x, y);
    }

    @Override
    public Pixmap sprite() {
        return Assets.coin;
    }

}
