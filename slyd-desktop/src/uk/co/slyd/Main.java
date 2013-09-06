package uk.co.slyd;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class Main {

	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "slyd";
		cfg.useGL20 = false;
		cfg.width = 320;
		cfg.height = 480;

		TexturePacker2.process("../assets/skin", "../slyd-android/assets/skin", "uiskin.atlas");
		new LwjglApplication(new Slyd(), cfg);
	}
}