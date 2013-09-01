package uk.co.slyd;

import java.util.Random;

public class Board {

	public String		ID;
	public Integer[][]	grid	= new Integer[slyd.gridSIZE][slyd.gridSIZE];

	public void shiftColumn(Integer col, Integer moves, Boolean down) {
		/*
		 * MOD the number of moves by the grid size, to return the minimum
		 * number of moves to make. If 0, there's no moves to make.
		 */
		moves = moves % slyd.gridSIZE;
		if (moves == 0)
			return;

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

	}

	public void shuffle(Integer number) {
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			shiftColumn(random.nextInt(slyd.gridSIZE), 1, true);
		}
	}
}
