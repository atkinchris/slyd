package uk.co.slyd;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class GameInput implements InputProcessor {

	private final Board		board;
	private final Vector2	cell	= new Vector2();
	private Boolean			dragged	= false;
	private String			direction;

	public Boolean			touched	= false;
	public Integer			moves	= 0;

	public GameInput(Board board) {
		this.board = board;
	}

	public Vector2 getCell() {
		return cell;
	}

	private Integer toCell(int pos) {
		int cell = (int) Math.ceil(pos / Slyd.SIZE);
		return cell;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touched = true;
		cell.x = toCell(screenX);
		cell.y = toCell(screenY);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = false;
		cell.x = 0;
		cell.y = 0;
		direction = null;
		if (dragged)
			moves++;
		dragged = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (cell.y - toCell(screenY) == 1 && direction != "x") {
			board.shiftColumn((int) cell.x, false);
			cell.y--;
			direction = "y";
			dragged = true;
		} else if (cell.y - toCell(screenY) == -1 && direction != "x") {
			board.shiftColumn((int) cell.x, true);
			cell.y++;
			direction = "y";
			dragged = true;
		} else if (cell.x - toCell(screenX) == 1 && direction != "y") {
			board.shiftRow((int) cell.y, false);
			cell.x--;
			direction = "x";
			dragged = true;
		} else if (cell.x - toCell(screenX) == -1 && direction != "y") {
			board.shiftRow((int) cell.y, true);
			cell.x++;
			direction = "x";
			dragged = true;
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
