package gliGOL;

import java.awt.Color;

public class Cell {
	private int x;
	private int y;
	private int cellSize;
	private boolean isAlive;
	private boolean isAliveNext;
	private Color colorAlive;
	private Color colorDead;
	private Color colorGrid;

	public Cell(int x, int y, int cellSize, boolean isAlive) {
		this.setX(x);
		this.setY(y);
		this.setAlive(isAlive);
		this.setAliveNext(false);
		this.setCellSize(cellSize);
		this.setColorAlive(Color.white);
		this.setColorDead(Color.black);
		this.setColorGrid(Color.black);
	}

	private void setColorGrid(Color c) {
		this.colorGrid = c;
	}

	private void setColorDead(Color c) {
		this.colorDead = c;
	}

	private void setColorAlive(Color c) {
		this.colorAlive = c;

	}

	private void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Color getColorAlive() {
		return colorAlive;
	}

	public Color getColorDead() {
		return colorDead;
	}

	public Color getColorGrid() {
		return colorGrid;
	}

	public boolean isAliveNext() {
		return isAliveNext;
	}

	public void setAliveNext(boolean isAliveNext) {
		this.isAliveNext = isAliveNext;
	}

	public void evolve() {
		this.setAlive(this.isAliveNext());

	}

}
