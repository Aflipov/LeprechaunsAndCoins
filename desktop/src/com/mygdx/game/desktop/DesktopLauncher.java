package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 2960;
		config.height = 1440;
		config.title = "Leprechauns&Coins";
		//config.fullscreen = true;
		config.foregroundFPS = 120;

		new LwjglApplication(new Main(), config);
	}
}