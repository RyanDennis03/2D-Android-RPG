package edu.angelo.finalprojectdennis;

import java.util.ArrayList;

import edu.angelo.finalprojectdennis.model.entity.Entity;
import edu.angelo.finalprojectdennis.model.entity.npc.NPC;
import edu.angelo.finalprojectdennis.model.entity.npc.impl.Golbin;
import edu.angelo.finalprojectdennis.model.entity.player.Player;
import edu.angelo.finalprojectdennis.model.item.Item;
import edu.angelo.finalprojectdennis.model.item.impl.Coin;
import edu.angelo.finalprojectdennis.model.tile.Tile;
import edu.angelo.finalprojectdennis.model.tile.impl.Grass;
import edu.angelo.finalprojectdennis.model.util.Direction;
import edu.angelo.finalprojectdennis.scene.Scene;
import edu.angelo.finalprojectdennis.util.MathUtils;

public class World {

    /**
     * Constants:
     */
    public static final int WORLD_WIDTH = 320;
    public static final int WORLD_HEIGHT = 480;
    static final int SCORE_INCREMENT = 1;
    static final int SCORE_DECREMENT = 1;
    static final float TICK_INITIAL = 0.3f; //.5

    /**
     * Game tracking:
     */
    public boolean gameOver = false;
    public int score = 0;
    public Player player;
    public Scene scene;

    /**
     * Engine tracking:
     */
    float tickTime = 0;
    float tick = TICK_INITIAL;

    /**
     * Default constructor:
     */
    public World() {
        //Player:
        player = new Player();
        //Set the scene to the field and position the player in the middle:
        changeScenes(Scene.FIELD, 7, 3);
    }

    /**
     * Our update function that handles the game ticks.
     * @param deltaTime The deltaTime.
     */
    public void update(float deltaTime) {
        if (gameOver) {
            return;
        }
        tickTime += deltaTime;
        while (tickTime > tick) {
            tickTime -= tick;
            handleTickActionsPlayer();
            handleTickActionsNPC();
        }
    }

    /**
     * ----------------------------
     * My entity related functions:
     * ----------------------------
     */

    /**
     * Moves the entity in a direction.
     * @param direction The direction.
     */
    public void move(Entity entity, Direction direction) {
        //Fail-safe for if they teleport and it clears the direction or if they die when moving:
        if (direction == null) {
            return;
        }
        //If the direction they are moving is NOT the direction they are facing, make the entity face first:
        if (entity.direction != direction) {
            entity.setDirectionalSprite(direction);
            return;
        }
        //MoveX: The next x position based on the direction:
        int moveX = entity.xPos;
        //MoveY: The next y position based on the direction:
        int moveY = entity.yPos;
        //if player can move:
        if (direction == Direction.UP) {
            moveY -= 1;
        }
        if (direction == Direction.LEFT) {
            moveX -= 1;
        }
        if (direction == Direction.DOWN) {
            moveY += 1;
        }
        if (direction == Direction.RIGHT) {
            moveX += 1;
        }
        if (moveX < 0) {
            moveX = 9;
        }
        if (moveX > 9) {
            moveX = 0;
        }
        if (moveY < 0) {
            moveY = 11;
        }
        if (moveY > 11) {
            moveY = 0;
        }
        entity.setDirectionalSprite(direction);
        if (canMove(moveX, moveY)) {
            entity.xPos = moveX;
            entity.yPos = moveY;
        }
    }

    /**
     * Used to check if the entity can move based on the global mappings:
     * @param x The x position.
     * @param y The y position.
     * @return True if yes, false otherwise.
     */
    public boolean canMove(int x, int y) {
        //To prevent NPC's walking into the player:
        if (player.xPos == x && player.yPos == y) {
            return false;
        }
        //NPCs:
        for (int i = 0; i < scene.npcs.size(); i++) {
            if (scene.npcs.get(i).xPos == x && scene.npcs.get(i).yPos == y) {
                return false;
            }
        }
        //Objects:
        for (int i = 0; i < scene.objects.size(); i++) {
            int offsetX = 0;
            int offsetY = 0;
            //If the size is greater than or equal to 3, we must have an offset that centers it.
            if (scene.objects.get(i).xSize() >= 3) {
                offsetX = -1;
            }
            if (scene.objects.get(i).ySize() >= 3) {
                offsetY = -1;
            }
            for (int xSize = offsetX; xSize < scene.objects.get(i).xSize() + offsetX; xSize++) {
                for (int ySize = offsetY; ySize < scene.objects.get(i).ySize() + offsetY; ySize++) {
                    if (scene.objects.get(i).xPos == x + xSize && scene.objects.get(i).yPos == y + ySize) {
                        return false;
                    }
                }
            }
        }
        //Tiles:
        //If there is a floor tile, we can now walk.
        for (int i = 0; i < scene.tile.size(); i++) {
            if (scene.tile.get(i).xPos == x && scene.tile.get(i).yPos == y) {
                return true;
            }
        }
        //False otherwise:
        return false;
    }

    /**
     * Used to set the Entity to a clipped random position:
     */
    public void randomPosition(Entity entity) {
        //We will use grass as all fighting monsters stand on it.
        //This could definitely be improved for performance as soon as I find a better way.
        ArrayList<Grass> grassTiles = new ArrayList();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 12; y++) {
                if (canMove(x, y)) {
                    grassTiles.add(new Grass(x, y));
                }
            }
        }
        final Tile tile = grassTiles.get(MathUtils.random(0, grassTiles.size() - 1));
        entity.xPos = tile.xPos;
        entity.yPos = tile.yPos;
    }

    /**
     * ----------------------------
     * My player related functions:
     * ----------------------------
     */

    /**
     * Moves the player in the direction he is heading and checks for a coin on the way.
     */
    private void handleTickActionsPlayer() {
        if (player.movingDirection != null) {
            onTile(player.xPos, player.yPos, player.direction);
            //player.move(player.movingDirection);
            move(player, player.movingDirection);
            checkForCoin();
        }
    }

    /**
     * Checks if the player is on a coin or not.
     */
    private void checkForCoin() {
        for (int i = 0; i < scene.items.size(); i++) {
            final Item item = scene.items.get(i);
            if (player.xPos == item.xPos && player.yPos == item.yPos) {
                scene.items.remove(item);
                score += SCORE_INCREMENT;
            }
        }
    }

    public void playerAttack() {
        int attackX = player.xPos;
        int attackY = player.yPos;
        //if player can move:
        if (player.direction == Direction.UP) {
            attackY -= 1;
        }
        if (player.direction == Direction.LEFT) {
            attackX -= 1;
        }
        if (player.direction == Direction.DOWN) {
            attackY += 1;
        }
        if (player.direction == Direction.RIGHT) {
            attackX += 1;
        }
        //If the tile is off the screen:
        if (attackX < 0 || attackX > 9 || attackY < 0 || attackY > 11) {
            return;
        }
        for (int i = 0; i < scene.npcs.size(); i++) {
            //Allows for stacking:
            if (scene.npcs.get(i).xPos == attackX && scene.npcs.get(i).yPos == attackY) {
                scene.npcs.get(i).isDead = true;
            }
        }
    }

    /**
     * Changes the scene for the player:
     * @param newScene The scene.
     * @param x The x position to place the player at.
     * @param y The y position to place the player at.
     */
    public void changeScenes(Scene newScene, int x, int y) {
        scene = newScene;
        scene.clearScene(); //Clear the map for fail-safe (in-case they went to main menu and came back).
        scene.buildScene();
        MusicPlayer.play(scene.backgroundMusic()); //Set up the music player.
        player.movingDirection = null;
        player.xPos = x;
        player.yPos = y;
    }

    /**
     * Handles actions on a given tile for a given scene:
     * @param x The x position.
     * @param y The y position.
     * @param direction The player's facing direction.
     */
    public void onTile(int x, int y, Direction direction) {
        //The Field scene:
        if (scene == Scene.FIELD) {
            //House Entrance:
            if (x == 7 && y == 3 && direction == Direction.UP) {
                changeScenes(Scene.HOUSE, 5, 8);
            }
            return;
        }
        //The house scene:
        if (scene == Scene.HOUSE) {
            if (y == 8 && direction == Direction.DOWN) {
                changeScenes(Scene.FIELD, 7, 3);
            }
            return;
        }
    }

    /**
     * ----------------------------
     * My npc related functions:
     * ----------------------------
     */

    /**
     * Handles our NPC's tick actions.
     * Moves our NPC's randomly.
     */
    private void handleTickActionsNPC() {
        for (int i = 0; i < scene.npcs.size(); i++) {
            final NPC npc = scene.npcs.get(i);
            if (npc instanceof Golbin && npc.isDead) {
                //Golbin drops a single coin on death:
                scene.items.add(new Coin(npc.xPos, npc.yPos));
                //It will then teleport to a random location appearing that there is a newly respawned goblin.
                randomPosition(npc);
                npc.isDead = false;
                continue;
            }
            //Moves our NPC's randomly.
            randomMove(npc);
            //Check if the player is in front.
            checkForNPCAttack(npc);
        }
    }

    /**
     * Used to move the NPC randomly:
     */
    private void randomMove(NPC npc) {
        if (!npc.walks) {
            return;
        }
        //Gives a 1/6 chance to walk:
        if (MathUtils.random(0, 5) == 0) {
            //This makes an npc walk in the direction he is facing 25% of the time.
            if (MathUtils.random(0, 3) == 0) {
                //Move them in their current direction:
                move(npc, npc.direction);
                return;
            }
            //Pick a random direction:
            switch (MathUtils.random(0, 3)) {
                case 0:
                    move(npc, Direction.UP);
                    break;
                case 1:
                    move(npc, Direction.DOWN);
                    break;
                case 2:
                    move(npc, Direction.LEFT);
                    break;
                case 3:
                    move(npc, Direction.RIGHT);
                    break;
            }
        }
    }

    /**
     * Checks if an NPC can attack the player.
     */
    private void checkForNPCAttack(NPC npc) {
        if (npc instanceof Golbin && isFacingPlayer(npc)) {
            if (Settings.soundEnabled) {
                Assets.attacked.play(1);
            }
            score -= SCORE_DECREMENT;
            if (score < 0) {
                gameOver = true;
            }
            //If the player gets looked at, we scramble his position.
            randomPosition(player);
        }
    }

    /**
     * Checks if a given NPC is facing the player.
     * @param npc Our NPC.
     * @return True if yes, false otherwise.
     */
    private boolean isFacingPlayer(NPC npc) {
        int faceX;
        int faceY;
        Direction direction;
        faceX = npc.xPos;
        faceY = npc.yPos;
        direction = npc.direction;
        if (direction == Direction.UP) {
            faceY -= 1;
        }
        if (direction == Direction.LEFT) {
            faceX -= 1;
        }
        if (direction == Direction.DOWN) {
            faceY += 1;
        }
        if (direction == Direction.RIGHT) {
            faceX += 1;
        }
        if (player.xPos == faceX && player.yPos == faceY) {
            return true;
        }
        return false;
    }

}
