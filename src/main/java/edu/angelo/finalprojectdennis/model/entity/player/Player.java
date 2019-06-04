package edu.angelo.finalprojectdennis.model.entity.player;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.entity.Entity;
import edu.angelo.finalprojectdennis.model.util.Direction;

/**
 * Represents our player in the game.
 * Created by rdennis4 on 11/20/2018.
 */
public class Player extends Entity {

    /**
     * Our variable used to dictate if the user is holding the move button or not.
     */
    public Direction movingDirection;

    @Override
    public Pixmap upSprite() {
        return Assets.player_up;
    }

    @Override
    public Pixmap downSprite() {
        return Assets.player_down;
    }

    @Override
    public Pixmap leftSprite() {
        return Assets.player_left;
    }

    @Override
    public Pixmap rightSprite() {
        return Assets.player_right;
    }

}
