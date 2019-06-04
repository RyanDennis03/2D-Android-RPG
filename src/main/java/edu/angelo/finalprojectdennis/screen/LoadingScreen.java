package edu.angelo.finalprojectdennis.screen;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Screen;

import edu.angelo.finalprojectdennis.Assets;
import edu.angelo.finalprojectdennis.Settings;

public class LoadingScreen extends Screen {

    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();

        //Menu:
        Assets.logo = g.newPixmap("sprite/ui/menu/logo.png", PixmapFormat.RGB565);
        Assets.background = g.newPixmap("sprite/ui/menu/background.png", PixmapFormat.RGB565);

        //Controller:
        Assets.arrows = g.newPixmap("sprite/ui/controller/buttons.png", PixmapFormat.RGB565);
        Assets.a = g.newPixmap("sprite/ui/controller/a.png", PixmapFormat.RGB565);
        Assets.b = g.newPixmap("sprite/ui/controller/b.png", PixmapFormat.RGB565);

        //Character:
        Assets.player_up = g.newPixmap("sprite/model/character/up.png", PixmapFormat.RGB565);
        Assets.player_down = g.newPixmap("sprite/model/character/down.png", PixmapFormat.RGB565);
        Assets.player_left = g.newPixmap("sprite/model/character/left.png", PixmapFormat.RGB565);
        Assets.player_right = g.newPixmap("sprite/model/character/right.png", PixmapFormat.RGB565);

        //NPC:
        //Golbin:
        Assets.golbin_up = g.newPixmap("sprite/model/npc/golbin/up.png", PixmapFormat.RGB565);
        Assets.golbin_down = g.newPixmap("sprite/model/npc/golbin/down.png", PixmapFormat.RGB565);
        Assets.golbin_left = g.newPixmap("sprite/model/npc/golbin/left.png", PixmapFormat.RGB565);
        Assets.golbin_right = g.newPixmap("sprite/model/npc/golbin/right.png", PixmapFormat.RGB565);
        //Muscle Mom
        Assets.muscle_mom_down = g.newPixmap("sprite/model/npc/muscle_mom/down.png", PixmapFormat.RGB565);

        //Item:
        Assets.coin = g.newPixmap("sprite/model/item/coin.png", PixmapFormat.RGB565);

        //Object
        Assets.house = g.newPixmap("sprite/model/object/house.png", PixmapFormat.RGB565);
        Assets.bed = g.newPixmap("sprite/model/object/bed.png", PixmapFormat.RGB565);

        //Tile:
        Assets.grass = g.newPixmap("sprite/model/tile/grass.png", PixmapFormat.RGB565);
        Assets.house_tile = g.newPixmap("sprite/model/tile/house_tile.png", PixmapFormat.RGB565);
        Assets.water = g.newPixmap("sprite/model/object/water.png", PixmapFormat.RGB565);
        Assets.water_up = g.newPixmap("sprite/model/tile/water_up.png", PixmapFormat.RGB565);
        Assets.water_left = g.newPixmap("sprite/model/tile/water_left.png", PixmapFormat.RGB565);


        //Sounds:
        Assets.attack = game.getAudio().newSound("sound/attack.ogg");
        Assets.attacked = game.getAudio().newSound("sound/attacked.ogg");
        Assets.click = game.getAudio().newSound("sound/click.ogg");
        Assets.coin_sound = game.getAudio().newSound("sound/coin.ogg");
        Assets.gameover_sound = game.getAudio().newSound("sound/gameover.ogg");
        Assets.paused = game.getAudio().newSound("sound/paused.ogg");

        //Music:
        Assets.fields = game.getAudio().newMusic("music/Fields.ogg");
        Assets.music_house = game.getAudio().newMusic("music/House.ogg");
        Assets.menu = game.getAudio().newMusic("music/Menu.ogg");

        Assets.mainMenu = g.newPixmap("sprite/ui/menu/mainmenu.png", PixmapFormat.ARGB4444);
        Assets.buttons = g.newPixmap("sprite/ui/buttons.png", PixmapFormat.ARGB4444);
        Assets.help1 = g.newPixmap("sprite/ui/menu/help/help1.png", PixmapFormat.ARGB4444);
        Assets.help2 = g.newPixmap("sprite/ui/menu/help/help2.png", PixmapFormat.ARGB4444);
        Assets.help3 = g.newPixmap("sprite/ui/menu/help/help3.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("sprite/ui/numbers.png", PixmapFormat.ARGB4444);
        Assets.ready = g.newPixmap("sprite/ui/ready.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("sprite/ui/menu/pausemenu.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("sprite/ui/gameover.png", PixmapFormat.ARGB4444);

        Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}
