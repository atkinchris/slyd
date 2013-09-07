package uk.co.slyd.models;

import uk.co.slyd.Slyd;

import com.badlogic.gdx.scenes.scene2d.Group;

public class BoardActor extends Group {

	public BoardActor() {
		Slyd.board.shuffle();

		for (int x = 0; x < Slyd.gridSIZE; x++)
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				CellActor cell = new CellActor(x, y);
				cell.setSize(Slyd.SIZE, Slyd.SIZE);
				addActor(cell);
			}
	}

	// @Override
	// public void draw(SpriteBatch batch, float parentAlpha) {
	// for (int x = 0; x < Slyd.gridSIZE; x++)
	// for (int y = 0; y < Slyd.gridSIZE; y++) {
	// int Y = y * Slyd.SIZE;
	// int X = x * Slyd.SIZE;
	// batch.draw(Slyd.textures[Slyd.board.grid[x][y]], Y, X, Slyd.SIZE,
	// Slyd.SIZE);
	// }
	// }
}
