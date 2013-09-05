package uk.co.slyd;

import com.badlogic.gdx.Gdx;

public class WinScreen extends AbstractScreen {

	public WinScreen(Slyd slyd) {
		super(slyd);
	}

	@Override
	public void render(float delta) {
		Slyd.batch.begin();
		Slyd.font.drawMultiLine(Slyd.batch, "Hooray\n" + "Moves " + Slyd.board.moves.toString(),
				Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		Slyd.batch.end();
	}

}
