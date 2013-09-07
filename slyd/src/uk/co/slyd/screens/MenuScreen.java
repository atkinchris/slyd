package uk.co.slyd.screens;

import uk.co.slyd.Slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

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
		TextButton levelButton = new TextButton("Start Game", skin);
		table.add(levelButton).width(260f);
		levelButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				slyd.setScreen(new LevelScreen(slyd));
			}
		});
	}

	@Override
	protected boolean isMenuScreen() {
		return true;
	}

}
