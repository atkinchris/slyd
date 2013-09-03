package uk.co.slyd;

import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class BoardManager {

	private HashMap<String, Board>	boards;

	public BoardManager() {
		boards = new HashMap<String, Board>();
		if (Slyd.DEBUG) {
			createDemoBoards(4);
			writeBoards();
		}
		readBoards();
	}

	private void createDemoBoards(Integer number) {
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			Board board = new Board();
			board.ID = "level-00" + String.valueOf(i);
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				for (int x = 0; x < Slyd.gridSIZE; x++) {
					board.grid[x][y] = random.nextInt(1);
				}
			}
			boards.put(board.ID, board);
		}
	}

	private void writeBoards() {
		FileHandle boardsDataFile = Gdx.files.local("boards.dat");
		Json json = new Json();
		String boardsDataText = json.toJson(boards);
		boardsDataFile.writeString(boardsDataText, false);
	}

	@SuppressWarnings("unchecked")
	private void readBoards() {
		Gdx.app.log("Board", "Reading boards...");
		FileHandle boardsDataFile = Gdx.files.internal("boards.dat");
		Json json = new Json();
		String boardsDataText = boardsDataFile.readString();
		boards = json.fromJson(HashMap.class, boardsDataText);
		Gdx.app.log("Board", "Reading boards complete.");
	}

	public Board getBoard(String ID) {
		Board board = boards.get(ID);
		return board;
	}
}
