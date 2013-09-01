package uk.co.slyd;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class slyd extends Game {

	private static final int	VIRTUAL_WIDTH		= 320;
	private static final int	VIRTUAL_HEIGHT		= 480;

	private OrthographicCamera	camera;
	private SpriteBatch			batch;
	private Texture[]			textures;
	private InputManager		inputManager;
	private BoardManager		boardManager;
	private Board				board;
	private Board				goal;
	private BitmapFont			font;

	public static final String	emptyTileColour		= "E0E4CC";
	public static final String	filledTileColour	= "69D2E7";
	public static final String	selectedColColour	= "F38630";

	public static Integer		SIZE;
	public static Integer		PADDING;
	public static final Integer	gridSIZE			= 7;

	@Override
	public void create() {

		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		camera.setToOrtho(true);
		batch = new SpriteBatch();

		font = new BitmapFont(Gdx.files.internal("basic.fnt"), true);
		font.setColor(Color.BLACK);
		font.setScale(4);

		setupTextures();

		boardManager = new BoardManager();
		board = boardManager.getBoard("1");
		goal = new Board();

		for (int y = 0; y < gridSIZE; y++) {
			for (int x = 0; x < gridSIZE; x++) {
				goal.grid[x][y] = board.grid[x][y];
			}
		}

		board.shuffle(6);

		inputManager = new InputManager(board);
		Gdx.input.setInputProcessor(inputManager);
	}

	@Override
	public void dispose() {
		batch.dispose();
		for (int i = 0; i < textures.length; i++) {
			textures[i].dispose();
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int x = 0; x < gridSIZE; x++) {
			for (int y = 0; y < gridSIZE; y++) {
				batch.draw(textures[board.grid[x][y]], (y * SIZE + (PADDING * y)) + PADDING, (x * SIZE + (PADDING * x))
						+ PADDING, SIZE, SIZE);
			}
		}
		batch.draw(textures[2],
				(inputManager.getSelectedColumn() * SIZE + (PADDING * inputManager.getSelectedColumn())) + PADDING,
				(gridSIZE) * (SIZE + PADDING), SIZE, SIZE);

		if (Arrays.deepEquals(goal.grid, board.grid)) {
			font.draw(batch, "Hooray", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
		}
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		SIZE = width / (gridSIZE + 1);
		PADDING = SIZE / (gridSIZE + 1);
	}

	private void setupTextures() {
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
