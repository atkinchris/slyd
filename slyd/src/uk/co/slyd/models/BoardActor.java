package uk.co.slyd.models;

import uk.co.slyd.Slyd;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class BoardActor extends Actor {

	private final DragListener	input;
	private String				direction;
	private Vector2				touchPos;
	private final Vector2		deltaPos;
	private int					last;

	public BoardActor() {
		input = new DragListener();
		addListener(input);
		touchPos = new Vector2();
		deltaPos = new Vector2();
	}

	@Override
	public void act(float delta) {
		if (!input.isDragging()) {
			direction = "u";
			last = 0;
			return;
		}

		touchPos.x = input.getTouchDownX();
		touchPos.y = input.getTouchDownY();
		touchPos = screenToLocalCoordinates(touchPos);

		deltaPos.x = input.getTouchDownX() - input.getDeltaX();
		deltaPos.y = input.getTouchDownY() - input.getDeltaY();

		if (Math.abs(deltaPos.y) > Slyd.SIZE && direction != "x") {
			direction = "y";
			touchPos.y = 0;
			deltaPos.x = 0;
		} else if (Math.abs(deltaPos.x) > Slyd.SIZE && direction != "y") {
			direction = "x";
			touchPos.x = 0;
			deltaPos.y = 0;
		}

		if (toCell(deltaPos.y) > 0 && direction == "y" && toCell(deltaPos.y) != last) {
			last = toCell(deltaPos.y);
			Slyd.board.shiftColumn(toCell(touchPos.x) - 1, deltaPos.y > 0 ? true : false);
		}

		if (toCell(deltaPos.x) > 0 && direction == "x" && toCell(deltaPos.x) != last) {
			last = toCell(deltaPos.x);
			Slyd.board.shiftRow(toCell(touchPos.y) - 1, deltaPos.x > 0 ? true : false);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		for (int x = 0; x < Slyd.gridSIZE; x++) {
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				batch.draw(Slyd.textures[Slyd.board.grid[x][y]], getX() + y * Slyd.SIZE, getY() + (x * Slyd.SIZE),
						Slyd.SIZE, Slyd.SIZE);
			}
		}
		Slyd.font.draw(batch, direction + ":" + toCell(touchPos.x) + "," + toCell(touchPos.y) + ":"
				+ toCell(deltaPos.x) + "," + toCell(deltaPos.y), getX(), getY());
	}

	private int toCell(float x) {
		int cell = (int) Math.ceil(Math.abs(x) / Slyd.SIZE);
		return cell;
	}
}
