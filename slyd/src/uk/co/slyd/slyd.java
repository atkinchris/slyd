package uk.co.slyd;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class slyd implements ApplicationListener {

	private static final int	VIRTUAL_WIDTH	= 320;
	private static final int	VIRTUAL_HEIGHT	= 480;
	private static final float	ASPECT_RATIO	= (float) VIRTUAL_WIDTH / (float) VIRTUAL_HEIGHT;

	private OrthographicCamera	camera;
	private Rectangle			viewport;
	private SpriteBatch			batch;
	private Texture[]			textures;
	private BoardManager		boardManager;

	public static final String	colour1			= "69D2E7";
	public static final String	colour2			= "A7DBD8";
	public static final String	colour3			= "E0E4CC";
	public static final String	colour4			= "F38630";
	public static final String	colour5			= "FA6900";

	public static final float	SIZE			= 32f;
	public static final float	PADDING			= 2f;

	@Override
	public void create() {
		camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		camera.setToOrtho(true);
		batch = new SpriteBatch();

		boardManager = new BoardManager();

		textures = new Texture[2];

		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);

		pixmap.setColor(Color.valueOf(colour3));
		pixmap.fill();
		textures[0] = new Texture(pixmap);

		pixmap.setColor(Color.valueOf(colour2));
		pixmap.fill();
		textures[1] = new Texture(pixmap);

		pixmap.dispose();
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
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width, (int) viewport.height);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		Board board = boardManager.getBoard("1");

		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; x++) {
				batch.draw(textures[board.grid[x][y]], y * (SIZE + PADDING), x * (SIZE + PADDING), SIZE, SIZE);
			}
		}

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float) width / (float) height;
		float scale = 1f;
		Vector2 crop = new Vector2(0f, 0f);

		if (aspectRatio > ASPECT_RATIO) {
			scale = (float) height / (float) VIRTUAL_HEIGHT;
			crop.x = (width - VIRTUAL_WIDTH * scale) / 2f;
		} else if (aspectRatio < ASPECT_RATIO) {
			scale = (float) width / (float) VIRTUAL_WIDTH;
			crop.y = (height - VIRTUAL_HEIGHT * scale) / 2f;
		} else {
			scale = (float) width / (float) VIRTUAL_WIDTH;
		}

		float w = VIRTUAL_WIDTH * scale;
		float h = VIRTUAL_HEIGHT * scale;
		viewport = new Rectangle(crop.x, crop.y, w, h);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
