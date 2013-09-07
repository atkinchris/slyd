package uk.co.slyd.screens;

import uk.co.slyd.BoardManager;
import uk.co.slyd.Slyd;
import uk.co.slyd.models.Board;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class LevelScreen extends AbstractScreen {

	public LevelScreen(Slyd slyd) {
		super(slyd);
	}

	@Override
	public void show() {
		super.show();
		for (final Board board : BoardManager.getBoards().values()) {
			TextButton levelButton = new TextButton("Puzzle " + board.ID, skin);
			table.add(levelButton).width(260f);
			levelButton.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					Slyd.board = BoardManager.getBoard(board.ID);
					slyd.setScreen(new GameScreen(slyd));
				}
			});

			table.row();
		}
	}
}
