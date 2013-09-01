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

public class slyd implements ApplicationListener {

	private OrthographicCamera	camera;
	private SpriteBatch			batch;
	private Texture[]			textures;
	private Integer[][]			grid;

	public static final String	colour1	= "69D2E7";
	public static final String	colour2	= "A7DBD8";
	public static final String	colour3	= "E0E4CC";
	public static final String	colour4	= "F38630";
	public static final String	colour5	= "FA6900";

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		grid = new Integer[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };

		camera = new OrthographicCamera(1, h / w);
		batch = new SpriteBatch();

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
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				Gdx.app.log("Grid", x + "," + y + "-" + grid[x][y]);
			}
		}

		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
