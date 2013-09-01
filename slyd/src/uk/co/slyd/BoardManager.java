package uk.co.slyd;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class BoardManager {

	private HashMap<String, Board>	boards;

	public BoardManager() {
		boards = new HashMap<String, Board>();
		createDemoBoards(4);
		writeBoards();
		readBoards();
	}

	private void createDemoBoards(Integer number) {
		for (int i = 1; i < number; i++) {
			Board board = new Board();
			board.ID = String.valueOf(i);
			board.grid = new Integer[][] { { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 },
					{ 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0 } };
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
		FileHandle boardsDataFile = Gdx.files.local("boards.dat");
		Json json = new Json();
		String boardsDataText = boardsDataFile.readString();
		boards = json.fromJson(HashMap.class, boardsDataText);
	}

	public Board getBoard(String ID) {
		Board board = boards.get(ID);
		return board;
	}
}
