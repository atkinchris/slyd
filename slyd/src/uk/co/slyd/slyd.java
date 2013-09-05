package uk.co.slyd;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;

public class Slyd extends Game {

	public final static int				gridSIZE	= 7;
	public final static boolean			DEBUG		= false;

	public static int					SIZE;
	public static Texture[]				textures;
	public static BitmapFont			font;
	public static SpriteBatch			batch;
	public static Board					board;
	public static OrthographicCamera	camera;

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
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		SIZE = width / gridSIZE;
	}

	private void loadAssets() {
		// Setup camera
		camera = new OrthographicCamera();
		camera.setToOrtho(true);

		// Load boards
		BoardManager.readBoards();

		// Load colours
		FileHandle coloursDataFile = Gdx.files.internal("colours");
		Json json = new Json();
		String coloursDataText = coloursDataFile.readString();
		String[] colours = json.fromJson(String[].class, coloursDataText);

		// Initialise SpriteBatch
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);

		// Load & setup fonts
		font = new BitmapFont(Gdx.files.internal("basic.fnt"), true);
		font.setColor(Color.BLACK);
		font.setScale(4);

		// Setup textures
		textures = new Texture[colours.length];
		Pixmap pixmap = new Pixmap(16, 16, Format.RGBA8888);
		for (int i = 0; i < colours.length; i++) {
			pixmap.setColor(1f, 1f, 1f, 0f);
			pixmap.fill();
			pixmap.setColor(Color.valueOf(colours[i]));
			pixmap.fillRectangle(2, 2, 14, 14);
			textures[i] = new Texture(pixmap);
		}
		pixmap.dispose();
	}
}
