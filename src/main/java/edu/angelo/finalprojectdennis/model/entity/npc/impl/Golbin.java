package edu.angelo.finalprojectdennis.model.entity.npc.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.entity.npc.NPC;

/**
 * This is actually named Golbin not Goblin intentionally.
 * In order to kill a golbin, a player must attack him from a direction the golbin is not facing.
 * If the golbin looks at the player when he is one tile away, the player will lose a coin.
 * If the player goes below 0 coins, it is game over.
 * Created by rdennis4 on 12/1/2018.
 */
public class Golbin extends NPC {

    public Golbin(int x, int y) {
        super(x, y);
    }

    @Override
    public Pixmap upSprite() {
        return Assets.golbin_up;
    }

    @Override
    public Pixmap downSprite() {
        return Assets.golbin_down;
    }

    @Override
    public Pixmap leftSprite() {
        return Assets.golbin_left;
    }

    @Override
    public Pixmap rightSprite() {
        return Assets.golbin_right;
    }

}
