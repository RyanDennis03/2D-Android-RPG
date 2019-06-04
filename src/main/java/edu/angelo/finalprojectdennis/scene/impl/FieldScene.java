package edu.angelo.finalprojectdennis.scene.impl;

import com.badlogic.androidgames.framework.Music;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.World;
import edu.angelo.finalprojectdennis.model.entity.npc.impl.Golbin;
import edu.angelo.finalprojectdennis.model.object.impl.House;
import edu.angelo.finalprojectdennis.model.object.impl.Water;
import edu.angelo.finalprojectdennis.model.tile.impl.Grass;
import edu.angelo.finalprojectdennis.model.tile.impl.WaterEdgeLeft;
import edu.angelo.finalprojectdennis.model.tile.impl.WaterEdgeUp;
import edu.angelo.finalprojectdennis.scene.Scene;

public class FieldScene extends Scene {

    @Override
    public void buildScene() {
        //Tiles:
        //Fill the screen with grass:
        for (int x = 0; x < World.WORLD_WIDTH / 32; x++) {
            for (int y = 0; y < World.WORLD_HEIGHT / 32; y++) {
                tile.add(new Grass(x, y));
            }
        }
        //Clean up the edges of the water:
        for (int y = 0; y < 4; y++) {
            tile.add(new WaterEdgeLeft(4, y));
        }
        //Clean up the bottom of the water:
        for (int x = 0; x < 4; x++) {
            tile.add(new WaterEdgeUp(x, 4));
        }

        //Objects:
        objects.add(new House(7, 1));
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                objects.add(new Water(x, y));
            }
        }

        //NPCs:
        npcs.add(new Golbin(1, 7));
        npcs.add(new Golbin(5, 10));
        npcs.add(new Golbin(8, 7));
        npcs.add(new Golbin(1, 10));
        npcs.add(new Golbin(1, 5));
        npcs.add(new Golbin(5, 6));
    }

    @Override
    public Music backgroundMusic() {
        return Assets.fields;
    }

}
