package edu.angelo.finalprojectdennis.model.object;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.model.Model;

/**
 * Created by rdennis4 on 12/2/2018.
 */
public abstract class GameObject extends Model {

    public GameObject(int x, int y) {
        xPos = x;
        yPos = y;
    }

    public abstract Pixmap sprite();
    public abstract int xSize();
    public abstract int ySize();

}

