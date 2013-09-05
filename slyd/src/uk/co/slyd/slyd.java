package uk.co.slyd;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Slyd extends Game {

	public final static int				gridSIZE			= 7;
	public final static boolean			DEBUG				= false;

	public static int					SIZE;
	public static Texture[]				textures;
	public static BitmapFont			font;
	public static SpriteBatch			batch;
	public static Board					board;
	public static OrthographicCamera	camera;

	private static final String			emptyTileColour		= "DCF5F3";
	private static final String			filledTileColour	= "4ECDC4";
	private static final String			selectedColColour	= "FF6B6B";

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
		SIZE = width / gridSIZE;
	}

	private void loadAssets() {
		// Setup Camera
		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		// Load Boards
		BoardManager.readBoards();

		// Initialise SpriteBatch
		batch = new SpriteBatch();
		batch.disableBlending();
		batch.setProjectionMatrix(camera.combined);

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
		Pixmap pixmap = new Pixmap(16, 16, Format.RGBA8888);
		for (int i = 0; i < colours.size(); i++) {
			pixmap.setColor(1f, 1f, 1f, 0f);
			pixmap.fill();
			pixmap.setColor(Color.valueOf(colours.get(i)));
			pixmap.fillRectangle(2, 2, 14, 14);
			textures[i] = new Texture(pixmap);
		}
		pixmap.dispose();
	}
}
