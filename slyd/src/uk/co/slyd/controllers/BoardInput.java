package uk.co.slyd.controllers;

import uk.co.slyd.Slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class BoardInput extends InputListener {

	private final Vector2	cell	= new Vector2();
	private Boolean			dragged	= false;
	private String			direction;

	private Integer toCell(float x) {
		int cell = (int) Math.ceil(x / Slyd.SIZE);
		return cell;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		super.touchDown(event, x, y, pointer, button);
		Gdx.app.log("touchDown", x + "," + y);
		cell.x = toCell(x);
		cell.y = toCell(y);
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		super.touchUp(event, x, y, pointer, button);
		Gdx.app.log("touchUp", x + "," + y);
		cell.x = 0;
		cell.y = 0;
		direction = null;
		if (dragged)
			Slyd.board.moves++;
		dragged = false;
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		super.touchDragged(event, x, y, pointer);
		Gdx.app.log("touchDown", x + "," + y);
		if (cell.y - toCell(y) == 1 && direction != "x") {
			Slyd.board.shiftColumn((int) cell.x, false);
			cell.y--;
			direction = "y";
			dragged = true;
		} else if (cell.y - toCell(y) == -1 && direction != "x") {
			Slyd.board.shiftColumn((int) cell.x, true);
			cell.y++;
			direction = "y";
			dragged = true;
		} else if (cell.x - toCell(x) == 1 && direction != "y") {
			Slyd.board.shiftRow((int) cell.y, false);
			cell.x--;
			direction = "x";
			dragged = true;
		} else if (cell.x - toCell(x) == -1 && direction != "y") {
			Slyd.board.shiftRow((int) cell.y, true);
			cell.x++;
			direction = "x";
			dragged = true;
		}
	}
}
