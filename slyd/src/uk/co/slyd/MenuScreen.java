package uk.co.slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreen implements Screen {

	private final Skin	skin;
	private final Stage	stage;
	private final Table	table;
	private final Slyd	slyd;

	public MenuScreen(Slyd slyd) {
		this.slyd = slyd;
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, Slyd.batch);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		table = new Table(skin);
		table.setFillParent(true);
		if (Slyd.DEBUG)
			table.debug();
		stage.addActor(table);

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

		table.add("slyd - a puzzle").spaceBottom(50);
		table.row();

		// register the button "new game"
		TextButton newGameButton = new TextButton("New game", skin);
		newGameButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				slyd.setScreen(new GameScreen());
				return true;
			}
		});
		table.add(newGameButton).size(300, 60).uniform().spaceBottom(10);
		table.row();

		// register the button "load game"
		TextButton loadGameButton = new TextButton("Load game", skin);
		loadGameButton.addListener(new InputListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				// game.setScreen(new LevelScreen(game));
			}
		});
		table.add(loadGameButton).uniform().fill().spaceBottom(10);
		table.row();

		// register the button "options"
		TextButton optionsButton = new TextButton("Options", skin);
		optionsButton.addListener(new InputListener() {

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				// game.setScreen(new LevelScreen(game));
			}
		});
		table.add(optionsButton).uniform().fill();
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
