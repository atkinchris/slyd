package uk.co.slyd.screens;

import java.util.Arrays;

import uk.co.slyd.Slyd;
import uk.co.slyd.models.Board;
import uk.co.slyd.models.BoardActor;

import com.badlogic.gdx.Gdx;

public class GameScreen extends AbstractScreen {

	private final Board	goal;

	public GameScreen(Slyd slyd) {
		super(slyd);

		goal = new Board();
		for (int y = 0; y < Slyd.gridSIZE; y++)
			for (int x = 0; x < Slyd.gridSIZE; x++)
				goal.grid[x][y] = Slyd.board.grid[x][y];

		Slyd.board.shuffle();

		BoardActor boardActor = new BoardActor();
		boardActor.size(Slyd.gridSIZE * Slyd.SIZE);

		table.add(boardActor);

	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (Arrays.deepEquals(goal.grid, Slyd.board.grid) && !Gdx.input.isTouched())
			slyd.setScreen(new WinScreen(slyd));
	}

	@Override
	public boolean isGameScreen() {
		return true;
	}
}
