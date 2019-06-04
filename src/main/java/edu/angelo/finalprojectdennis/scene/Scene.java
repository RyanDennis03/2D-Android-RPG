package edu.angelo.finalprojectdennis.scene;

import com.badlogic.androidgames.framework.Music;

import java.util.ArrayList;

import edu.angelo.finalprojectdennis.model.entity.npc.NPC;
import edu.angelo.finalprojectdennis.model.item.Item;
import edu.angelo.finalprojectdennis.model.object.GameObject;
import edu.angelo.finalprojectdennis.model.tile.Tile;
import edu.angelo.finalprojectdennis.scene.impl.FieldScene;
import edu.angelo.finalprojectdennis.scene.impl.HouseScene;

public abstract class Scene {

    /**
     * Our instaces of scene:
     */
    public static final FieldScene FIELD = new FieldScene();
    public static final HouseScene HOUSE = new HouseScene();

    /**
     * Our scene building arraylists.
     * These are static so that if a player quits to the main
     * menu and goes back in game, it will not create a new arraylist.
     *
     * It is also going to be more memory efficient to load and clear the
     * array list per scene (10x12) rather than trying to create one big scene (ex 100x100)
     * to teleport the player around and constantly index through large amounts of models.
     *
     * Created by rdennis4 on 12/3/2018.
     */
    public static ArrayList<Tile> tile = new ArrayList<>();
    public static ArrayList<NPC> npcs = new ArrayList<>();
    public static ArrayList<GameObject> objects = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();

    /**
     * Clears the scene completely.
     */
    public void clearScene() {
        tile.clear();
        npcs.clear();
        objects.clear();
        items.clear();
    }

    /**
     * Used to build the scene.
     */
    public abstract void buildScene();

    /**
     * The background music for our given scene.
     * @return The background music.
     */
    public abstract Music backgroundMusic();


}
