package gliGOL;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Grid {
	private Cell grid[][];
	private int gridWidth;
	private int gridHeight;
	private int cellSize;

	public Grid() {
		setGridWidth(10);
		setGridHeight(10);
		setCellSize(8);
		grid = new Cell[getGridHeight()][getGridWidth()];
		for (int x = 0; x < getGridWidth(); x++) {
			for (int y = 0; y < getGridHeight(); y++) {
				grid[y][x] = new Cell(x, y, cellSize, false);
			}
		}
		for (int x = 0; x < getGridWidth(); x++) {
			for (int y = 0; y < getGridHeight(); y++) {
				Cell currentCell = grid[y][x];
				if ((x - 1) > 0) {
					currentCell.setLeft(grid[y][x - 1]);
				}
				if ((x + 1) < getGridWidth()) {
					currentCell.setRight(grid[y][x + 1]);
				}
				if ((y - 1) > 0) {
					currentCell.setUp(grid[y - 1][x]);
				}
				if ((y + 1) < getGridHeight()) {
					currentCell.setDown(grid[y + 1][x]);
				}
				if (((x - 1) > 0) && ((y - 1) > 0)) {
					currentCell.setUpleft(grid[y - 1][x - 1]);
				}
				if (((x + 1) < getGridWidth()) && ((y - 1) > 0)) {
					currentCell.setUpright(grid[y - 1][x + 1]);
				}
				if (((x - 1) > 0) && ((y + 1) < getGridHeight())) {
					currentCell.setDownleft(grid[y + 1][x - 1]);
				}
				if (((x + 1) < getGridWidth()) && ((y + 1) < getGridHeight())) {
					currentCell.setDownright(grid[y + 1][x + 1]);
				}
			}
		}
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

	public int getCellSize() {
		return cellSize;
	}

	public void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

	}
}
