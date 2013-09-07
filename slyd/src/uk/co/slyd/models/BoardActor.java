package uk.co.slyd.models;

import uk.co.slyd.Slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

public class BoardActor extends Actor {

	private final DragListener	input;
	private String				direction;
	private Vector2				touchPos;
	private Vector2				deltaPos;

	public BoardActor() {
		input = new DragListener();
		addListener(input);
		touchPos = new Vector2();
		deltaPos = new Vector2();
	}

	@Override
	public void act(float delta) {
		if (!input.isDragging()) {
			direction = "";
			return;
		}

		touchPos.x = input.getTouchDownX();
		touchPos.y = input.getTouchDownY();
		touchPos = screenToLocalCoordinates(touchPos);

		deltaPos.x = input.getDeltaX();
		deltaPos.y = input.getDeltaY();
		deltaPos = screenToLocalCoordinates(deltaPos);

		Gdx.app.log("touch", touchPos.toString());
		Gdx.app.log("delta", deltaPos.toString());

		if (deltaPos.y % 1 == 0 && direction != "x") {
			direction = "y";
			Slyd.board.shiftColumn(toCell(touchPos.x), deltaPos.y > 0 ? true : false);
			return;
		}

		if (deltaPos.x % 1 == 0 && direction != "y") {
			direction = "x";
			Slyd.board.shiftColumn(toCell(touchPos.y), deltaPos.x > 0 ? true : false);
		}
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		for (int x = 0; x < Slyd.gridSIZE; x++) {
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				batch.draw(Slyd.textures[Slyd.board.grid[x][y]], getX() + x * Slyd.SIZE, getY() + y * Slyd.SIZE,
						Slyd.SIZE, Slyd.SIZE);
			}
		}
	}

	private Integer toCell(float x) {
		int cell = (int) Math.ceil(x / Slyd.SIZE);
		return cell;
	}
}
