package edu.angelo.finalprojectdennis.screen;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input;
import com.badlogic.androidgames.framework.Screen;

import java.util.List;

import edu.angelo.finalprojectdennis.MusicPlayer;
import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.Settings;
import edu.angelo.finalprojectdennis.screen.help.HelpScreen;

public class MainMenuScreen extends Screen {

    public MainMenuScreen(Game game) {
        super(game);
        MusicPlayer.play(Assets.menu);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i += 1) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (!Settings.soundEnabled) {
                        MusicPlayer.stop();
                    } else {
                        MusicPlayer.play(Assets.menu);
                    }
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }
                if (inBounds(event, 64, 220, 192, 42)) {
                    game.setScreen(new GameScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }
                if (inBounds(event, 64, 220 + 42, 192, 42)) {
                    game.setScreen(new HighscoreScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }
                if (inBounds(event, 64, 220 + 84, 192, 42)) {
                    game.setScreen(new HelpScreen(game));
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                    return;
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y && event.y < y + height - 1) {
            return true;
        }
        return false;
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.logo, 32, 20);
        g.drawPixmap(Assets.mainMenu, 64, 220);
        if (Settings.soundEnabled) {
            g.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        } else {
            g.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        }
    }

    public void pause() {
        Settings.save(game.getFileIO());
    }

    public void resume() {
    }

    public void dispose() {
    }
}
