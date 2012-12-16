package com.ladybug.vilain;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "LadybugVilain";
		cfg.useGL20 = false;
		cfg.width = 340;
		cfg.height = 200;
		
		new LwjglApplication(new LadybugVilain(), cfg);
	}
}
