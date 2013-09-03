package uk.co.slyd;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slyd extends Game {

	public final static Integer	gridSIZE			= 7;
	public final static Boolean	DEBUG				= false;

	public static Integer		SIZE;
	public static Integer		PAD;
	public static Texture[]		textures;
	public static BitmapFont	font;
	public static SpriteBatch	batch;

	private static final String	emptyTileColour		= "E0E4CC";
	private static final String	filledTileColour	= "69D2E7";
	private static final String	selectedColColour	= "F38630";

	@Override
	public void create() {
		super.setScreen(new MenuScreen(this));
		loadAssets();

		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void dispose() {
		for (int i = 0; i < textures.length; i++) {
			textures[i].dispose();
		}
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		SIZE = width / (gridSIZE + 1);
		PAD = SIZE / (gridSIZE + 1);
	}

	private void loadAssets() {
		// Initialise SpriteBatch
		batch = new SpriteBatch();

		// Setup Fonts
		font = new BitmapFont(Gdx.files.internal("basic.fnt"), true);
		font.setColor(Color.BLACK);
		font.setScale(4);

		// Setup Textures
		ArrayList<String> colours = new ArrayList<String>();
		colours.add(emptyTileColour);
		colours.add(filledTileColour);
		colours.add(selectedColColour);
		textures = new Texture[colours.size()];
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		for (int i = 0; i < colours.size(); i++) {
			pixmap.setColor(Color.valueOf(colours.get(i)));
			pixmap.fill();
			textures[i] = new Texture(pixmap);
		}
		pixmap.dispose();
	}
}
