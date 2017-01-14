package gliGOL;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Grid {
	private Cell grid[][];
	private Cell nextGrid[][];
	private int gridWidth;
	private int gridHeight;
	private int cellSize;
	private int sustainLife;
	private int createLife;

	private Random rng;
	private int OFFSET_X = 36;
	private int OFFSET_Y = 48;

	public Grid(int width, int height, int size) {
		rng = new Random();
		boolean nextBoolean;

		setGridWidth(width);
		setGridHeight(height);
		setCellSize(size);
		setSustainLife(2);
		setCreateLife(3);
		grid = new Cell[getGridHeight()][getGridWidth()];
		for (int x = 0; x < getGridWidth(); x++) {
			for (int y = 0; y < getGridHeight(); y++) {
				nextBoolean = rng.nextBoolean();
				grid[y][x] = new Cell(x, y, cellSize, nextBoolean);
			}
		}
	}

	public Grid(int width, int height, int size, int create, int sustain) {
		rng = new Random();
		boolean nextBoolean;
		setCreateLife(create);
		setSustainLife(sustain);

		setGridWidth(width);
		setGridHeight(height);
		setCellSize(size);
		grid = new Cell[getGridHeight()][getGridWidth()];
		for (int x = 0; x < getGridWidth(); x++) {
			for (int y = 0; y < getGridHeight(); y++) {
				nextBoolean = rng.nextBoolean();
				grid[y][x] = new Cell(x, y, cellSize, nextBoolean);
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
		int cx = 0;
		int cy = 0;
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				cx = grid[y][x].getX() * cellSize;
				cy = grid[y][x].getY() * cellSize;
				cx += OFFSET_X;
				cy += OFFSET_Y;

				if ((grid[y][x]).isAlive()) {
					g2.setColor(grid[y][x].getColorAlive());
					g2.fillRect(cx, cy, cellSize, cellSize);
				} else {
					g2.setColor(grid[y][x].getColorDead());
					g2.fillRect(cx, cy, cellSize, cellSize);
				}
				g2.setColor(grid[y][x].getColorGrid());
				g2.drawRect(cx, cy, cellSize, cellSize);
			}
		}
		g2.dispose();
	}

	public void nextGridGen() {
		// calculate all cell's next generation state
		// do not modify current state until all cells have been checked
		int aliveCount = 0;
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				aliveCount = 0;

				if (((x - 1) > 0) && ((grid[y][x - 1].isAlive()))) {
					aliveCount++;
				}
				if (((x + 1) < gridWidth) && ((grid[y][x + 1].isAlive()))) {
					aliveCount++;
				}
				if (((y - 1) > 0) && ((grid[y - 1][x].isAlive()))) {
					aliveCount++;
				}
				if (((y + 1) < gridHeight) && ((grid[y + 1][x]).isAlive())) {
					aliveCount++;
				}
				if (((y - 1) > 0) && ((x - 1) > 0) && ((grid[y - 1][x - 1]).isAlive())) {
					aliveCount++;
				}
				if (((y - 1) > 0) && ((x + 1) < gridWidth) && ((grid[y - 1][x + 1]).isAlive())) {
					aliveCount++;
				}
				if (((y + 1) < gridHeight) && ((x - 1) > 0) && ((grid[y + 1][x - 1]).isAlive())) {
					aliveCount++;
				}
				if (((y + 1) < gridHeight) && ((x + 1) < gridWidth) && ((grid[y + 1][x + 1]).isAlive())) {
					aliveCount++;
				}

				boolean currentCellIsAlive = (grid[y][x]).isAlive();
				if (currentCellIsAlive) {
					if (aliveCount < getSustainLife()) {
						(grid[y][x]).setAliveNext(false);
					}
					if ((aliveCount == getSustainLife()) || (aliveCount == getSustainLife() + 1)) {
						(grid[y][x]).setAliveNext(true);
					}
					if ((aliveCount > getSustainLife() + 1)) {
						(grid[y][x]).setAliveNext(false);
					}
				}
				if (!currentCellIsAlive) {
					if (aliveCount == getCreateLife()) {
						(grid[y][x]).setAliveNext(true);
					}
				}
			}
		}
		this.setNextGridGen();
	}

	public void setNextGridGen() {
		for (int x = 0; x < gridWidth; x++) {
			for (int y = 0; y < gridHeight; y++) {
				grid[y][x].evolve();
			}
		}
	}

	public int getSustainLife() {
		return sustainLife;
	}

	public void setSustainLife(int sustainLife) {
		this.sustainLife = sustainLife;
	}

	public int getCreateLife() {
		return createLife;
	}

	public void setCreateLife(int createLife) {
		this.createLife = createLife;
	}

	public void shuffle() {
		boolean nextBoolean;
		for (int x = 0; x < getGridWidth(); x++) {
			for (int y = 0; y < getGridHeight(); y++) {
				nextBoolean = rng.nextBoolean();
				grid[y][x] = new Cell(x, y, cellSize, nextBoolean);
			}
		}
	}
}
