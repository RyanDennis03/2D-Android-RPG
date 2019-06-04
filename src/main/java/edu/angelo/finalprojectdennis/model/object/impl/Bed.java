package edu.angelo.finalprojectdennis.model.object.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.object.GameObject;

/**
 * Created by rdennis4 on 12/2/2018.
 */
public class Bed extends GameObject {

    public Bed(int x, int y) {
        super(x, y);
    }

    @Override
    public Pixmap sprite() {
        return Assets.bed;
    }

    @Override
    public int xSize() {
        return 1;
    }

    @Override
    public int ySize() {
        return 2;
    }

}
