package uk.co.slyd;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameScreen implements Screen {

	private final Stage		stage;
	private final GameInput	input;
	private final Board		board;
	private final Board		goal;
	private final Slyd		slyd;

	public Boolean			won	= false;

	public GameScreen(Slyd slyd) {
		this.slyd = slyd;

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
		stage.draw();
		Table.drawDebug(stage);

		Slyd.batch.begin();
		for (int x = 0; x < Slyd.gridSIZE; x++) {
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				Slyd.batch.draw(Slyd.textures[board.grid[x][y]], y * Slyd.SIZE, x * Slyd.SIZE, Slyd.SIZE, Slyd.SIZE);
			}
		}

		Slyd.font.draw(Slyd.batch, "Moves " + input.moves.toString(), Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);

		if (Arrays.deepEquals(goal.grid, board.grid) && !Gdx.input.isTouched()) {
			slyd.setScreen(new WinScreen(slyd, input.moves));
		}

		Slyd.batch.end();
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
