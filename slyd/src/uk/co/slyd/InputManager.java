package uk.co.slyd;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class InputManager implements InputProcessor {

	private final Board		board;
	private final Vector2	touchPos	= new Vector2();
	private final Vector2	cell		= new Vector2();
	private final Vector2	offset		= new Vector2();
	public Boolean			touched		= false;

	public InputManager(Board board) {
		this.board = board;
	}

	public Vector2 getCell() {
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
		touchPos.x = screenX;
		touchPos.y = screenY;
		cell.x = (float) Math.ceil(screenX / (slyd.SIZE + slyd.PAD));
		cell.y = (float) Math.ceil(screenY / (slyd.SIZE + slyd.PAD));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = false;
		touchPos.x = 0;
		touchPos.y = 0;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		offset.x = touchPos.x - screenX;
		offset.y = touchPos.y - screenY;

		if (offset.y > (slyd.SIZE + slyd.PAD)) {
			board.shiftColumn((int) cell.x, 1, false);
		} else if (offset.y < -(slyd.SIZE + slyd.PAD)) {
			board.shiftColumn((int) cell.x, 1, true);
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
