package uk.co.slyd;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;

public class GameScreen extends AbstractScreen {

	private final GameInput	input;
	private final Board		board;
	private final Board		goal;

	public Boolean			won	= false;

	public GameScreen(Slyd slyd) {
		super(slyd);
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
		super.render(delta);

		Slyd.batch.begin();
		for (int x = 0; x < Slyd.gridSIZE; x++) {
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				Slyd.batch.draw(Slyd.textures[board.grid[x][y]], y * Slyd.SIZE, x * Slyd.SIZE, Slyd.SIZE, Slyd.SIZE);
			}
		}

		Slyd.font.draw(Slyd.batch, "Moves " + board.moves.toString(), Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);

		if (Arrays.deepEquals(goal.grid, board.grid) && !Gdx.input.isTouched()) {
			slyd.setScreen(new WinScreen(slyd));
		}

		Slyd.batch.end();
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(input);
	}
}
