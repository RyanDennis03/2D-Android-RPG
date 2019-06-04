package edu.angelo.finalprojectdennis.model.entity.npc;

import edu.angelo.finalprojectdennis.model.entity.Entity;

/**
 * Represents an NPC in the game.
 * Created by rdennis4 on 11/20/2018.
 */
public abstract class NPC extends Entity {

    public boolean walks;
    public boolean isDead;

    public NPC(int x, int y) {
        walks = true;
        isDead = false;
        xPos = x;
        yPos = y;
    }

}
