package uk.co.slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {

	private final Board	board;
	private Integer		column;

	public InputManager(Board board) {
		this.board = board;
		column = 0;
	}

	public Integer getSelectedColumn() {
		return column;
	}

	private void nextColumn() {
		if (column == 6) {
			column = 0;
		} else {
			column++;
		}
	}

	private void prevColumn() {
		if (column == 0) {
			column = 6;
		} else {
			column--;
		}
	}

	private void shiftUp() {
		board.shiftColumn(column, 1, false);
	}

	private void shiftDown() {
		board.shiftColumn(column, 1, true);
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.RIGHT)
			nextColumn();
		if (keycode == Keys.LEFT)
			prevColumn();
		if (keycode == Keys.DOWN)
			shiftDown();
		if (keycode == Keys.UP)
			shiftUp();
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
		if ((screenX > Gdx.graphics.getWidth() / 2) && (screenY < Gdx.graphics.getHeight() / 2)) {
			nextColumn();
		}
		if ((screenX < Gdx.graphics.getWidth() / 2) && (screenY < Gdx.graphics.getHeight() / 2)) {
			prevColumn();
		}
		if ((screenX > Gdx.graphics.getWidth() / 2) && (screenY > Gdx.graphics.getHeight() / 2)) {
			shiftUp();
		}
		if ((screenX < Gdx.graphics.getWidth() / 2) && (screenY > Gdx.graphics.getHeight() / 2)) {
			shiftDown();
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
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
