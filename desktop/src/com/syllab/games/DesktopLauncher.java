package com.syllab.games;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.syllab.games.Zeldalus;
import com.syllab.games.zeldalus.Daedalus;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Zeldalus");
		config.setWindowedMode(Daedalus.SCREEN_WIDTH, Daedalus.SCREEN_HEIGHT);
		new Lwjgl3Application(new Zeldalus(), config);
	}
}
