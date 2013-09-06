package uk.co.slyd.models;

import java.util.Random;

import uk.co.slyd.Slyd;

public class Board {

	public String		ID;
	public Integer		par;
	public Integer		moves	= 0;
	public Integer[][]	grid	= new Integer[Slyd.gridSIZE][Slyd.gridSIZE];

	public void shiftColumn(int col, boolean down) {
		if (down) {
			int temp = grid[Slyd.gridSIZE - 1][col];
			for (int i = (Slyd.gridSIZE - 1); i > 0; i--)
				grid[i][col] = grid[i - 1][col];
			grid[0][col] = temp;
		} else {
			int temp = grid[0][col];
			for (int i = 0; i < (Slyd.gridSIZE - 1); i++)
				grid[i][col] = grid[i + 1][col];
			grid[Slyd.gridSIZE - 1][col] = temp;
		}
	}

	public void shiftRow(int row, boolean right) {
		if (right) {
			int temp = grid[row][Slyd.gridSIZE - 1];
			for (int i = (Slyd.gridSIZE - 1); i > 0; i--)
				grid[row][i] = grid[row][i - 1];
			grid[row][0] = temp;
		} else {
			int temp = grid[row][0];
			for (int i = 0; i < (Slyd.gridSIZE - 1); i++)
				grid[row][i] = grid[row][i + 1];
			grid[row][Slyd.gridSIZE - 1] = temp;
		}
	}

	private boolean isEmpty(int pos, boolean col) {
		int sum = 0;
		for (int i = 0; i < Slyd.gridSIZE; i++) {
			if (col)
				sum += grid[i][pos];
			else
				sum += grid[pos][i];
		}
		if (sum == 0)
			return true;
		return false;
	}

	public void shuffle() {
		Random random = new Random();
		for (int i = 0; i < par; i++) {
			if (random.nextBoolean() || par < 4) {
				int y = random.nextInt(Slyd.gridSIZE);
				while (isEmpty(y, true))
					y = random.nextInt(Slyd.gridSIZE);
				shiftColumn(y, random.nextBoolean());
			} else {
				int x = random.nextInt(Slyd.gridSIZE);
				while (isEmpty(x, false))
					x = random.nextInt(Slyd.gridSIZE);
				shiftRow(x, random.nextBoolean());
			}
		}
	}
}
