package uk.co.slyd;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputManager implements InputProcessor {

	private final Board		board;
	private final Vector2	cell	= new Vector2();
	public Boolean			touched	= false;

	public InputManager(Board board) {
		this.board = board;
	}

	public Vector2 getCell() {
		return cell;
	}

	private Integer toCell(int pos) {
		/* Takes a screen position in pixels and converts to cell coordinate */
		int cell = (int) Math.ceil(pos / (slyd.SIZE + slyd.PAD));
		return cell;
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.R)
			board.shuffle(board.par);
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
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if (cell.y - toCell(screenY) == 1) {
			board.shiftColumn((int) cell.x, false);
			cell.y--;
		} else if (cell.y - toCell(screenY) == -1) {
			board.shiftColumn((int) cell.x, true);
			cell.y++;
		} else if (cell.x - toCell(screenX) == 1) {
			board.shiftRow((int) cell.x, false);
			cell.x--;
		} else if (cell.x - toCell(screenX) == -1) {
			board.shiftRow((int) cell.x, true);
			cell.x++;
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
