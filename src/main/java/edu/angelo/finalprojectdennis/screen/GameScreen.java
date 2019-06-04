package edu.angelo.finalprojectdennis.screen;

import android.graphics.Color;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Pixmap;
import com.badlogic.androidgames.framework.Screen;

import java.util.List;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.Settings;
import edu.angelo.finalprojectdennis.World;
import edu.angelo.finalprojectdennis.model.util.Direction;

public class GameScreen extends Screen {

    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";

    public GameScreen(Game game) {
        super(game);
        world = new World();
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if (state == GameState.Ready) {
            updateReady(touchEvents);
        }
        if (state == GameState.Running) {
            updateRunning(touchEvents, deltaTime);
        }
        if (state == GameState.Paused) {
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver) {
            updateGameOver(touchEvents);
        }
    }

    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0) {
            state = GameState.Running;
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
        //The default running:
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                world.player.movingDirection = null;
                if (event.x < 64 && event.y < 64) {
                    if (Settings.soundEnabled) {
                        Assets.paused.play(1); //change click to paused to play pause noise.
                    }
                    state = GameState.Paused;
                    return;
                }
            }
            if (event.type == TouchEvent.TOUCH_DOWN) {
                //Up Arrow:
                if (event.x > 53 && event.x < 71 && event.y > 385 + 16 && event.y < 408 + 16) {
                    world.player.movingDirection = Direction.UP;
                }
                //Down Arrow:
                if (event.x > 53 && event.x < 71 && event.y > 422 + 16 && event.y < 444 + 16) {
                    world.player.movingDirection = Direction.DOWN;
                }
                //Left Arrow:
                if (event.x > 31 && event.x < 56 && event.y > 420 && event.y < 440) {
                    world.player.movingDirection = Direction.LEFT;
                }
                //Right Arrow:
                if (event.x > 69 && event.x < 92 && event.y > 420 && event.y < 440) {
                    world.player.movingDirection = Direction.RIGHT;
                }
                //A Button:
                if (event.x > 226 && event.x < 252 && event.y > 401 && event.y < 429) {
                    world.playerAttack();
                    if (Settings.soundEnabled) {
                        Assets.attack.play(1);
                    }
                }
            }
            //System.out.println(" PLAYER TILE " + world.player.xPos + " " + world.player.yPos);
            //System.out.println(event.x + " WAS X");
            //System.out.println(event.y + " WAS Y");
        }
        world.update(deltaTime);
        if (world.gameOver) {
            if (Settings.soundEnabled) {
                Assets.gameover_sound.play(1);
            }
            state = GameState.GameOver;
        }
        if (oldScore != world.score && state != GameState.GameOver) {
            oldScore = world.score;
            score = "" + oldScore;
            if (Settings.soundEnabled) {
                Assets.coin_sound.play(1);
            }
        }
    }

    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        state = GameState.Running;
                        return;
                    }
                    //In the updatePaused method, add code that, whenever the user chooses the quit option,
                    //adds the score to the high scores and saves them to file.
                    //Hint: Look at the pause method and see what the code does when the game is over.
                    if (event.y > 148 && event.y < 196) {
                        Settings.addScore(world.score);
                        Settings.save(game.getFileIO());
                        if (Settings.soundEnabled) {
                            Assets.click.play(1);
                        }
                        game.setScreen(new MainMenuScreen(game));
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);

        drawRunningUI(); //Always draw this one as it shows the controls.
        if (state == GameState.Ready) {
            drawReadyUI();
        }
        if (state == GameState.Running) {
            //drawRunningUI();
        }
        if (state == GameState.Paused) {
            drawPausedUI();
        }
        if (state == GameState.GameOver) {
            drawGameOverUI();
        }
        //Look at the call to drawText in the present method; the last two arguments give values of x and y to write to.
        //Change the x argument to g.getWidth() - score.length() * 20 and the y argument to 0 to write the score in the upper right of the screen instead of the lower middle.
        //System.out.println("score was " + score);
        drawText(g, score, g.getWidth() - score.length() * 20, 0);
    }

    private void drawWorld(World world) {
        final Graphics g = game.getGraphics();
        final Pixmap playerSprite = world.player.currentSprite;
        int x;
        int y;
        Pixmap pixmap;

        //Draw our black background:
        for (int width = 0; width < World.WORLD_WIDTH / 32; width++) {
            for (int height = 0; height < World.WORLD_HEIGHT / 32; height++) {
                g.drawRect(0, 0, World.WORLD_WIDTH + 1, World.WORLD_HEIGHT + 1, Color.BLACK); //Black background
            }
        }

        //Draw our tiles:
        for (int i = 0; i < world.scene.tile.size(); i++) {
            x = world.scene.tile.get(i).xPos * 32 + 16;
            y = world.scene.tile.get(i).yPos * 32 + 16;
            pixmap = world.scene.tile.get(i).sprite();
            g.drawPixmap(pixmap, x - pixmap.getWidth() / 2, y - pixmap.getHeight() / 2);
        }

        //Draw our objects:
        for (int i = 0; i < world.scene.objects.size(); i++) {
            x = world.scene.objects.get(i).xPos * 32 + 16;
            y = world.scene.objects.get(i).yPos * 32 + 16;
            pixmap = world.scene.objects.get(i).sprite();
            g.drawPixmap(pixmap, x - pixmap.getWidth() / 2, y - pixmap.getHeight() / 2);
        }

        //Draw our player:
        x = world.player.xPos * 32 + 16;
        y = world.player.yPos * 32 + 16;
        g.drawPixmap(playerSprite, x - playerSprite.getWidth() / 2, y - playerSprite.getHeight() / 2);

        //Draw our npcs:
        for (int i = 0; i < world.scene.npcs.size(); i++) {
            x = world.scene.npcs.get(i).xPos * 32 + 16;
            y = world.scene.npcs.get(i).yPos * 32 + 16;
            pixmap = world.scene.npcs.get(i).currentSprite;
            g.drawPixmap(pixmap, x - pixmap.getWidth() / 2, y - pixmap.getHeight() / 2);
        }

        //Draw our items:
        for (int i = 0; i < world.scene.items.size(); i++) {
            x = world.scene.items.get(i).xPos * 32 + 16;
            y = world.scene.items.get(i).yPos * 32 + 16;
            pixmap = world.scene.items.get(i).sprite();
            g.drawPixmap(pixmap, x - pixmap.getWidth() / 2, y - pixmap.getHeight() / 2);
        }
    }

    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.ready, 47, 100);
    }

    private void drawRunningUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);

        //Draw the controller:
        g.drawRect(0, 32 * 12, World.WORLD_WIDTH + 1, World.WORLD_HEIGHT / 5 + 1, Color.BLACK); //Black background
        g.drawPixmap(Assets.arrows, 32, World.WORLD_HEIGHT - 64 - 32 + 16); //The arrows
        g.drawPixmap(Assets.a, 32 * 7, World.WORLD_HEIGHT - 64 - 32 + 16); //The a button
        g.drawPixmap(Assets.b, 32 * 8 + 8, World.WORLD_HEIGHT - 64 - 32 + 32 + 16);    //The b button
    }

    //In the drawPausedUI method, remove the code that draws black lines.
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.pause, 80, 100);
    }

    //In the drawGameOverUI method, remove the code that draws black lines.
    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i += 1) {
            char character = line.charAt(i);
            if (character == ' ') {
                x += 20;
                continue;
            }
            int srcX;
            int srcWidth;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    @Override
    public void pause() {
        if (state == GameState.Running) {
            state = GameState.Paused;
        }
        if (world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
