package uk.co.slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen implements Screen {

	private final Skin		skin;
	private final Stage		stage;
	private final Table		table;
	private final Slyd		slyd;
	private final Texture	logoTexture;
	private final Integer	width	= 320;
	private final Integer	height	= 480;

	public MenuScreen(Slyd slyd) {
		this.slyd = slyd;
		stage = new Stage(width, height, true);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		table = new Table(skin);
		table.setFillParent(true);
		if (Slyd.DEBUG)
			table.debug();
		stage.addActor(table);

		logoTexture = new Texture(Gdx.files.internal("logo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

		Image logoImage = new Image(logoTexture);

		table.top();

		table.add(logoImage).maxSize(width * 0.9f);
		table.row();

		TextButton levelButton = new TextButton("Puzzle", skin);
		levelButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				slyd.setScreen(new GameScreen(BoardManager.getBoard("level001")));
				return true;
			}
		});
		table.add(levelButton).size(300, 60).uniform();
		table.row();

		TextButton challengeButton = new TextButton("Challenge", skin);
		challengeButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				super.touchDown(event, x, y, pointer, button);
				// slyd.setScreen(new
				// GameScreen(BoardManager.getBoard("challenge")));
				BoardManager.createDemoBoards(2);
				BoardManager.writeBoards();
				return true;
			}
		});
		table.add(challengeButton).size(300, 60).uniform();
	}

	@Override
	public void render(float delta) {
		if (delta > 0.1f)
			delta = 0.0166f;

		stage.act(delta);

		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
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
