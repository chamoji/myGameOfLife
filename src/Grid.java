package gliGOL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

public class Grid implements MouseMotionListener {
	private Cell grid[][];
	private int gridWidth;
	private int gridHeight;
	private int cellSize;
	private int[] sustainLife;
	private int[] createLife;

	private Random rng;
	private int OFFSET_X = 0;
	private int OFFSET_Y = 0;

	private int mouseX;
	private int mouseY;

	private boolean toggle;

	public Grid(int width, int height, int size) {
		rng = new Random();
		boolean nextBoolean;
		toggle = false;

		setGridWidth(width);
		setGridHeight(height);
		setCellSize(size);
		setSustainLife(new int[] { 2, 3 });
		setCreateLife(new int[] { 3 });
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
				int[] sustain = getSustainLife();
				int[] create = getCreateLife();
				ArrayList<Integer> listSustain = new ArrayList<Integer>();
				for (int i = 0; i < sustain.length; i++) {
					listSustain.add(new Integer(sustain[i]));
				}
				ArrayList<Integer> listCreate = new ArrayList<Integer>();
				for (int i = 0; i < create.length; i++) {
					listCreate.add(new Integer(create[i]));
				}

				if (currentCellIsAlive) {
					if (listSustain.contains(new Integer(aliveCount))) {
						grid[y][x].setAliveNext(true);
					} else {
						grid[y][x].setAliveNext(false);
					}
				} else {
					if (listCreate.contains(new Integer(aliveCount))) {
						grid[y][x].setAliveNext(true);
					} else {
						grid[y][x].setAliveNext(false);
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

	public int[] getSustainLife() {
		return sustainLife;
	}

	public void setSustainLife(int[] is) {
		this.sustainLife = is;
	}

	public int[] getCreateLife() {
		return createLife;
	}

	public void setCreateLife(int[] is) {
		this.createLife = is;
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

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setMouseX((int) e.getX());
		setMouseY((int) e.getY());

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
				} else {
					g2.setColor(grid[y][x].getColorDead());
				}

				g2.fillRect(cx, cy, cellSize, cellSize);

				g2.setColor(grid[y][x].getColorGrid());
				g2.drawRect(cx, cy, cellSize, cellSize);
			}
		}
		g2.setColor(Color.black);
		g2.drawString("(" + getMouseX() + "," + getMouseY() + ")", 36, (gridHeight * cellSize) + (cellSize * 9));
		g2.dispose();
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX - (cellSize * 1);
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY - (cellSize * 4);
	}

	public void clear() {
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridHeight; j++) {
				grid[j][i].setAlive(false);
			}
		}
	}

	public void spawn() {
		grid[rng.nextInt(gridHeight)][rng.nextInt(gridWidth)].setAlive(true);
	}

	public void toggle() {
		if (toggle) {
			setSustainLife(new int[] { 2, 3 });
			setCreateLife(new int[] { 3 });
			toggle = false;
		} else {
			setSustainLife(new int[] { 1, 2, 3, 4, 5 });
			setCreateLife(new int[] { 1, 2 });
			toggle = true;
		}
	}

}
