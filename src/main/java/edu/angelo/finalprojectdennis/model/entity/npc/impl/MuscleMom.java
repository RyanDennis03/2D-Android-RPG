package edu.angelo.finalprojectdennis.model.entity.npc.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.entity.npc.NPC;

/**
 * Represents the mother in the house.
 * (All of her sprites are down as she will not walk).
 *
 * Eventually, the player is going to have 4 health hearts.
 * When a golbin looks at the player when he is one tile away,
 * the player will lose a heart.
 *
 * This NPC will offer to trade 10 coins in for one heart.
 * I was not able to finish it in time as it will require a dialogue system.
 * For now, she will just stand peacefully in the house.
 */
public class MuscleMom extends NPC {

    public MuscleMom(int x, int y) {
        super(x, y);
        walks = false;
    }

    @Override
    public Pixmap upSprite() {
        return Assets.muscle_mom_down;
    }

    @Override
    public Pixmap downSprite() {
        return Assets.muscle_mom_down;
    }

    @Override
    public Pixmap leftSprite() {
        return Assets.muscle_mom_down;
    }

    @Override
    public Pixmap rightSprite() {
        return Assets.muscle_mom_down;
    }

}
