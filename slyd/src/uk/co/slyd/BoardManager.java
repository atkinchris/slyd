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
		// createDemoBoards(4);
		// writeBoards();
		readBoards();
	}

	@SuppressWarnings("unused")
	private void createDemoBoards(Integer number) {
		Random random = new Random();
		for (int i = 1; i < number; i++) {
			Board board = new Board();
			board.ID = String.valueOf(i);
			for (int y = 0; y < slyd.gridSIZE; y++) {
				for (int x = 0; x < slyd.gridSIZE; x++) {
					board.grid[x][y] = random.nextInt(1);
				}
			}
			boards.put(board.ID, board);
		}
	}

	@SuppressWarnings("unused")
	private void writeBoards() {
		FileHandle boardsDataFile = Gdx.files.local("boards.dat");
		Json json = new Json();
		String boardsDataText = json.toJson(boards);
		boardsDataFile.writeString(boardsDataText, false);
	}

	@SuppressWarnings("unchecked")
	private void readBoards() {
		FileHandle boardsDataFile = Gdx.files.internal("boards.dat");
		Json json = new Json();
		String boardsDataText = boardsDataFile.readString();
		boards = json.fromJson(HashMap.class, boardsDataText);
	}

	public Board getBoard(String ID) {
		Board board = boards.get(ID);
		return board;
	}
}
