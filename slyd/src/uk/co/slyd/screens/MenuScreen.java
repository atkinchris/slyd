package uk.co.slyd.screens;

import uk.co.slyd.Slyd;
import uk.co.slyd.models.BoardManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen extends AbstractScreen {

	private final Texture	logoTexture;

	public MenuScreen(Slyd slyd) {
		super(slyd);
		logoTexture = new Texture(Gdx.files.internal("logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		skin.add("default", new BitmapFont(true));

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.GREEN);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

	}

	@Override
	public void show() {
		super.show();

		Image logoImage = new Image(logoTexture);

		table.add(logoImage).maxSize(width * 0.9f);

		table.row();
		TextButton levelButton = new TextButton("Puzzle", skin);
		table.add(levelButton);
		levelButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Slyd.board = BoardManager.getBoard("level001");
				slyd.setScreen(new GameScreen(slyd));
			}
		});

		table.row();
		TextButton challengeButton = new TextButton("Challenge", skin);
		table.add(challengeButton).expandX();
		challengeButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Slyd.board = BoardManager.getBoard("challenge");
				slyd.setScreen(new GameScreen(slyd));
			}
		});
	}

	@Override
	protected boolean isMenuScreen() {
		return true;
	}

}
