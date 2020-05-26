package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.view.MenuScreen;

public class Main extends Game {

	public static float lastClick = 1000;
	public static boolean isClickAvalible(Float delta) {
		if (lastClick >= 50) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}

	@Override
	public void dispose() {
	}
}