package uk.co.slyd;

import java.util.Random;

public class Board {

	public String		ID;
	public Integer		par		= 6;
	public Integer[][]	grid	= new Integer[Slyd.gridSIZE][Slyd.gridSIZE];

	public void shift(Integer pos, Integer direction) {
		if (direction == 3) { // Column Down
			Integer temp = grid[Slyd.gridSIZE - 1][pos];
			for (int i = (Slyd.gridSIZE - 1); i > 0; i--) {
				grid[i][pos] = grid[i - 1][pos];
			}
			grid[0][pos] = temp;
		}
		if (direction == 1) { // Column Up
			Integer temp = grid[0][pos];
			for (int i = 0; i < (Slyd.gridSIZE - 1); i++) {
				grid[i][pos] = grid[i + 1][pos];
			}
			grid[Slyd.gridSIZE - 1][pos] = temp;
		}
		if (direction == 2) { // Row Right
			Integer temp = grid[pos][Slyd.gridSIZE - 1];
			for (int i = (Slyd.gridSIZE - 1); i > 0; i--) {
				grid[pos][i] = grid[pos][i - 1];
			}
			grid[pos][0] = temp;
		}
		if (direction == 4) { // Row Left
			Integer temp = grid[pos][0];
			for (int i = 0; i < (Slyd.gridSIZE - 1); i++) {
				grid[pos][i] = grid[pos][i + 1];
			}
			grid[pos][Slyd.gridSIZE - 1] = temp;
		}
	}

	public void shiftColumn(Integer col, Boolean down) {
		if (down) {
			Integer temp = grid[Slyd.gridSIZE - 1][col];
			for (int i = (Slyd.gridSIZE - 1); i > 0; i--) {
				grid[i][col] = grid[i - 1][col];
			}
			grid[0][col] = temp;
		} else {
			Integer temp = grid[0][col];
			for (int i = 0; i < (Slyd.gridSIZE - 1); i++) {
				grid[i][col] = grid[i + 1][col];
			}
			grid[Slyd.gridSIZE - 1][col] = temp;
		}
	}

	public void shiftRow(Integer row, Boolean right) {
		if (right) {
			Integer temp = grid[row][Slyd.gridSIZE - 1];
			for (int i = (Slyd.gridSIZE - 1); i > 0; i--) {
				grid[row][i] = grid[row][i - 1];
			}
			grid[row][0] = temp;
		} else {
			Integer temp = grid[row][0];
			for (int i = 0; i < (Slyd.gridSIZE - 1); i++) {
				grid[row][i] = grid[row][i + 1];
			}
			grid[row][Slyd.gridSIZE - 1] = temp;
		}
	}

	private boolean emptyCol(Integer col) {
		Integer sum = 0;
		for (int i = 0; i < Slyd.gridSIZE; i++) {
			sum += grid[i][col];
		}
		if (sum == 0)
			return true;
		return false;
	}

	public void shuffle(Integer number) {
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			int x = random.nextInt(Slyd.gridSIZE);
			while (emptyCol(x)) {
				x = random.nextInt(Slyd.gridSIZE);
			}
			shiftColumn(x, true);
		}
	}
}
