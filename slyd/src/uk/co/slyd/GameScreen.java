package uk.co.slyd;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {

	private final OrthographicCamera	camera;
	private final GameInput			input;
	private final BoardManager			boardManager;
	private final Board					board;
	private final Board					goal;

	public GameScreen() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true);

		boardManager = new BoardManager();
		board = boardManager.getBoard("1");
		goal = new Board();

		for (int y = 0; y < Slyd.gridSIZE; y++) {
			for (int x = 0; x < Slyd.gridSIZE; x++) {
				goal.grid[x][y] = board.grid[x][y];
			}
		}

		board.shuffle(board.par);

		input = new GameInput(board);
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Slyd.batch.setProjectionMatrix(camera.combined);
		Slyd.batch.begin();
		for (int x = 0; x < Slyd.gridSIZE; x++) {
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				Slyd.batch.draw(Slyd.textures[board.grid[x][y]], y * (Slyd.SIZE + Slyd.PAD) + Slyd.PAD, x
						* (Slyd.SIZE + Slyd.PAD) + Slyd.PAD, Slyd.SIZE, Slyd.SIZE);
			}
		}
		if (input.touched)
			Slyd.batch.draw(Slyd.textures[2], input.getCell().x * (Slyd.SIZE + Slyd.PAD) + Slyd.PAD, input.getCell().y
					* (Slyd.SIZE + Slyd.PAD) + Slyd.PAD, Slyd.SIZE, Slyd.SIZE);

		if (Arrays.deepEquals(goal.grid, board.grid)) {
			Slyd.font.draw(Slyd.batch, "Hooray", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
		}
		Slyd.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
