package uk.co.slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class WinScreen implements Screen {

	@SuppressWarnings("unused")
	private final Slyd		slyd;
	private final Integer	moves;

	public WinScreen(Slyd slyd, Integer moves) {
		this.slyd = slyd;
		this.moves = moves;
	}

	@Override
	public void render(float delta) {
		Slyd.batch.begin();
		Slyd.font.drawMultiLine(Slyd.batch, "Hooray\n" + "Moves " + moves.toString(), Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2);
		Slyd.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
