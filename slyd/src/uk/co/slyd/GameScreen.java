package uk.co.slyd;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen implements Screen {

	private final Stage		stage;
	private final GameInput	input;
	private final Board		board;
	private final Board		goal;

	public GameScreen(Slyd slyd) {
		stage = new Stage();
		stage.setCamera(Slyd.camera);

		this.board = Slyd.board;
		goal = new Board();

		for (int y = 0; y < Slyd.gridSIZE; y++) {
			for (int x = 0; x < Slyd.gridSIZE; x++) {
				goal.grid[x][y] = board.grid[x][y];
			}
		}

		board.shuffle(board.par);

		input = new GameInput(board);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Slyd.batch.begin();
		for (int x = 0; x < Slyd.gridSIZE; x++) {
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				Slyd.batch.draw(Slyd.textures[board.grid[x][y]], y * Slyd.SIZE, x * Slyd.SIZE, Slyd.SIZE, Slyd.SIZE);
			}
		}

		if (Arrays.deepEquals(goal.grid, board.grid)) {
			Slyd.font.draw(Slyd.batch, "Hooray", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
		}

		Slyd.batch.end();

		stage.draw();
		Table.drawDebug(stage);

	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(input);
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
