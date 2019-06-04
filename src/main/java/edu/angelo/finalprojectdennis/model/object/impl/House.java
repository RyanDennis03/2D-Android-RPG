package edu.angelo.finalprojectdennis.model.object.impl;

import com.badlogic.androidgames.framework.Pixmap;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.object.GameObject;

/**
 * Created by rdennis4 on 12/2/2018.
 */
public class House extends GameObject {

    public House(int x, int y) {
        super(x, y);
    }

    @Override
    public Pixmap sprite() {
        return Assets.house;
    }

    @Override
    public int xSize() {
        return 3;
    }

    @Override
    public int ySize() {
        return 3;
    }


}
