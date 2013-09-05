package uk.co.slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen extends AbstractScreen {

	private final Texture	logoTexture;

	public MenuScreen(Slyd slyd) {
		super(slyd);
		logoTexture = new Texture(Gdx.files.internal("logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void show() {
		super.show();

		Image logoImage = new Image(logoTexture);

		table.add(logoImage).maxSize(width * 0.9f);
		table.row();

		TextButton levelButton = new TextButton("Puzzle", skin);
		levelButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				Slyd.board = BoardManager.getBoard("level001");
				slyd.setScreen(new GameScreen(slyd));
				return true;
			}
		});
		table.add(levelButton).expandX();
		table.row();

		TextButton challengeButton = new TextButton("Challenge", skin);
		challengeButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				Slyd.board = BoardManager.getBoard("challenge");
				slyd.setScreen(new GameScreen(slyd));
				return true;
			}
		});
		table.add(challengeButton).expandX();
	}

	@Override
	protected boolean isMenuScreen() {
		return true;
	}

}
