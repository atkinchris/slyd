package uk.co.slyd;

import com.badlogic.gdx.Gdx;

public class WinScreen extends AbstractScreen {

	private final float	longpress	= 0;

	public WinScreen(Slyd slyd) {
		super(slyd);
	}

	@Override
	public void render(float delta) {
		if (Gdx.input.isTouched())
			slyd.setScreen(new MenuScreen(slyd));

		Slyd.batch.begin();
		Slyd.font.drawMultiLine(Slyd.batch, "Hooray\n" + "Moves " + Slyd.board.moves.toString()
				+ "\nTap to continue...", 10, 200);
		Slyd.batch.end();
	}

}
