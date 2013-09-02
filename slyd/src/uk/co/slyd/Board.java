package uk.co.slyd;

import java.util.Random;

public class Board {

	public String		ID;
	public Integer		par		= 6;
	public Integer[][]	grid	= new Integer[slyd.gridSIZE][slyd.gridSIZE];

	public boolean shiftColumn(Integer col, Boolean down) {
		if (down) {
			Integer temp = grid[slyd.gridSIZE - 1][col];
			for (int i = (slyd.gridSIZE - 1); i > 0; i--) {
				grid[i][col] = grid[i - 1][col];
			}
			grid[0][col] = temp;
		} else {
			Integer temp = grid[0][col];
			for (int i = 0; i < (slyd.gridSIZE - 1); i++) {
				grid[i][col] = grid[i + 1][col];
			}
			grid[slyd.gridSIZE - 1][col] = temp;
		}
		return true;
	}

	public boolean shiftRow(Integer row, Boolean right) {
		if (right) {
			Integer temp = grid[row][slyd.gridSIZE - 1];
			for (int i = (slyd.gridSIZE - 1); i > 0; i--) {
				grid[row][i] = grid[row][i - 1];
			}
			grid[row][0] = temp;
		} else {
			Integer temp = grid[row][0];
			for (int i = 0; i < (slyd.gridSIZE - 1); i++) {
				grid[row][i] = grid[row][i + 1];
			}
			grid[row][slyd.gridSIZE - 1] = temp;
		}
		return true;
	}

	private boolean emptyCol(Integer col) {
		Integer sum = 0;
		for (int i = 0; i < slyd.gridSIZE; i++) {
			sum += grid[i][col];
		}
		if (sum == 0)
			return true;
		return false;
	}

	public void shuffle(Integer number) {
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			int x = random.nextInt(slyd.gridSIZE);
			while (emptyCol(x)) {
				x = random.nextInt(slyd.gridSIZE);
			}
			shiftColumn(x, true);
		}
	}
}
