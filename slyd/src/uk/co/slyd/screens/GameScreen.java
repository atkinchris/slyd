package uk.co.slyd.screens;

import java.util.Arrays;

import uk.co.slyd.Slyd;
import uk.co.slyd.controllers.GameInput;
import uk.co.slyd.models.Board;

import com.badlogic.gdx.Gdx;

public class GameScreen extends AbstractScreen {

	private final GameInput	input;
	private final Board		board;
	private final Board		goal;

	public boolean			won	= false;

	public GameScreen(Slyd slyd) {
		super(slyd);

		board = Slyd.board;
		goal = new Board();

		for (int y = 0; y < Slyd.gridSIZE; y++)
			for (int x = 0; x < Slyd.gridSIZE; x++)
				goal.grid[x][y] = board.grid[x][y];

		board.shuffle();
		input = new GameInput(board);
	}

	@Override
	public void render(float delta) {
		Slyd.batch.begin();
		for (int x = 0; x < Slyd.gridSIZE; x++)
			for (int y = 0; y < Slyd.gridSIZE; y++)
				Slyd.batch.draw(Slyd.textures[board.grid[x][y]], y * Slyd.SIZE, x * Slyd.SIZE, Slyd.SIZE, Slyd.SIZE);

		Slyd.font.draw(Slyd.batch, "Moves " + board.moves.toString(), 10, Slyd.SIZE * Slyd.gridSIZE + 10);

		if (Arrays.deepEquals(goal.grid, board.grid) && !Gdx.input.isTouched())
			slyd.setScreen(new WinScreen(slyd));

		Slyd.batch.end();
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(input);
	}

	@Override
	protected boolean isGameScreen() {
		return true;
	}
}
