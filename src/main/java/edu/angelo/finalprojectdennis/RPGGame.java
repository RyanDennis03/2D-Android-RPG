package edu.angelo.finalprojectdennis;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

import edu.angelo.finalprojectdennis.screen.LoadingScreen;

public class RPGGame extends AndroidGame {

    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
}
