package edu.angelo.finalprojectdennis.scene.impl;

import com.badlogic.androidgames.framework.Music;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.model.entity.npc.impl.MuscleMom;
import edu.angelo.finalprojectdennis.model.object.impl.Bed;
import edu.angelo.finalprojectdennis.model.tile.impl.HouseTile;
import edu.angelo.finalprojectdennis.scene.Scene;

public class HouseScene extends Scene {

    //Constant what we can so that we save memory:
    //(We could cache the tiles in an array but it is overkill for the small impact to really cache any of these...)
    private static final MuscleMom mom = new MuscleMom(5, 4);
    private static final Bed bed = new Bed(2, 3);

    @Override
    public void buildScene() {
        //Tile:
        for (int x = 2; x < 8; x++) {
            for (int y = 3; y < 9; y++) {
                tile.add(new HouseTile(x, y));
            }
        }

        //NPCs:
        npcs.add(mom);

        //Objects:
        objects.add(bed);
    }

    @Override
    public Music backgroundMusic() {
        return Assets.music_house;
    }

}
