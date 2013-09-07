package uk.co.slyd.screens;

import uk.co.slyd.Slyd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class AbstractScreen implements Screen {

	protected final Skin	skin;
	protected final Stage	stage;
	protected final Table	table;
	protected final Slyd	slyd;
	protected final Integer	width	= 320;
	protected final Integer	height	= 480;

	public AbstractScreen(Slyd slyd) {
		this.slyd = slyd;
		stage = new Stage(width, height, true);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
		table = new Table(skin);
		table.setFillParent(true);
		table.top();
		table.pad(10).defaults().expandX().space(4);
		if (Slyd.DEBUG)
			table.debug();
		stage.addActor(table);
	}

	@Override
	public void render(float delta) {
		if (delta > 0.1f)
			delta = 0.0166f;

		if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
			if (isMenuScreen()) {
				Gdx.app.exit();
			} else {
				slyd.setScreen(new MenuScreen(slyd));
			}
		}

		stage.act(delta);
		stage.draw();
		Table.drawDebug(stage);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

		if (isGameScreen())
			Slyd.camera.setToOrtho(true);
		else
			Slyd.camera.setToOrtho(false);

		stage.setCamera(Slyd.camera);
	}

	@Override
	public void hide() {
		dispose();
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
		skin.dispose();
		stage.dispose();
	}

	protected boolean isMenuScreen() {
		return false;
	}

	protected boolean isGameScreen() {
		return false;
	}

}
