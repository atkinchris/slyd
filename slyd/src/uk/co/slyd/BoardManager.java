package uk.co.slyd;

import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class BoardManager {

	private static HashMap<String, Board>	boards;

	public static void createDemoBoards(Integer number) {
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			Board board = new Board();
			board.ID = "level00" + String.valueOf(i);
			for (int y = 0; y < Slyd.gridSIZE; y++) {
				for (int x = 0; x < Slyd.gridSIZE; x++) {
					board.grid[x][y] = random.nextInt(2);
				}
			}
			boards.put(board.ID, board);
		}
	}

	public static void writeBoards() {
		FileHandle boardsDataFile = Gdx.files.local("boards.dat");
		Json json = new Json();
		String boardsDataText = json.prettyPrint(boards);
		boardsDataText = boardsDataText.replaceAll("\n\t\t\t", "");
		boardsDataText = boardsDataText.replaceAll("\n\t\t]", "]");
		boardsDataFile.writeString(boardsDataText, false);
	}

	@SuppressWarnings("unchecked")
	public static void readBoards() {
		FileHandle boardsDataFile = Gdx.files.internal("boards.dat");
		Json json = new Json();
		String boardsDataText = boardsDataFile.readString();
		boards = json.fromJson(HashMap.class, boardsDataText);
	}

	public static Board getBoard(String ID) {
		Board board = boards.get(ID);
		return board;
	}
}
