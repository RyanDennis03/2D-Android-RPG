package edu.angelo.finalprojectdennis.model.entity;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.model.Model;
import edu.angelo.finalprojectdennis.model.util.Direction;

/**
 * Entity extends model and is used as a template for the Player & NPCs.
 * Created by rdennis4 on 11/20/2018.
 */
public abstract class Entity extends Model {

    /**
     * The abstract requirements for every unique instance of Entity.
     * upSprite() - The display sprite for when they are directionally up.
     * downSprite() - The display sprite for when they are directionally down.
     * leftSprite() - The display sprite for when they are directionally left.
     * rightSprite() - The display sprite for when they are directionally right.
     * onAttack() - The unique attack for the specific entity.
     * onDeath() - The unique death for the specific entity.
     */
    public abstract Pixmap upSprite();
    public abstract Pixmap downSprite();
    public abstract Pixmap leftSprite();
    public abstract Pixmap rightSprite();

    //Default constructor:
    public Entity() {
        setDirectionalSprite(Direction.DOWN);
        xPos = 5;
        yPos = 6;
    }

    //The facing direction:
    public Direction direction;

    //The current display sprite:
    public Pixmap currentSprite;

    /**
     * Sets the directional sprite for the entity.
     * @param direction The direction.
     */
    public void setDirectionalSprite(Direction direction) {
        this.direction = direction;
        if (direction == Direction.UP) {
            currentSprite = upSprite();
        }
        if (direction == Direction.LEFT) {
            currentSprite = leftSprite();
        }
        if (direction == Direction.DOWN) {
            currentSprite = downSprite();
        }
        if (direction == Direction.RIGHT) {
            currentSprite = rightSprite();
        }
    }

}
