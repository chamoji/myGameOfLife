package gliGOL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Cell {
	private int x;
	private int y;
	private int cellSize;
	private boolean isAlive;
	private Cell left, right, up, down, upleft, upright, downleft, downright;
	private Color color;

	public Cell(int x, int y, int cellSize, boolean isAlive) {
		this.setX(x);
		this.setY(y);
		this.setAlive(isAlive);
		this.setCellSize(cellSize);
		this.setColor(Color.black);
		initNeighbors();
	}

	private void setCellSize(int cellSize) {
		this.cellSize = cellSize;
	}

	public void initNeighbors() {
		setNeighbors(null, null, null, null, null, null, null, null);
	}

	public void setNeighbors(Cell left, Cell right, Cell up, Cell down, Cell upleft, Cell upright, Cell downleft,
			Cell downright) {
		setLeft(left);
		setRight(right);
		setUp(up);
		setDown(down);
		setUpleft(upleft);
		setUpright(upright);
		setDownleft(downleft);
		setDownright(downright);
	}

	public int getNumberOfAliveNeighbors() {
		int aliveCount = 0;
		if (null != left && left.isAlive()) {
			aliveCount++;
		}
		if (null != right && right.isAlive()) {
			aliveCount++;
		}
		if (null != up && up.isAlive()) {
			aliveCount++;
		}
		if (null != down && down.isAlive()) {
			aliveCount++;
		}
		if (null != upleft && upleft.isAlive()) {
			aliveCount++;
		}
		if (null != upright && upright.isAlive()) {
			aliveCount++;
		}
		if (null != downleft && downleft.isAlive()) {
			aliveCount++;
		}
		if (null != downright && downright.isAlive()) {
			aliveCount++;
		}
		return aliveCount;
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

	public Cell getLeft() {
		return left;
	}

	public void setLeft(Cell left) {
		this.left = left;
	}

	public Cell getRight() {
		return right;
	}

	public Cell setRight(Cell right) {
		this.right = right;
		return right;
	}

	public Cell getUp() {
		return up;
	}

	public Cell setUp(Cell up) {
		this.up = up;
		return up;
	}

	public Cell getDown() {
		return down;
	}

	public Cell setDown(Cell down) {
		this.down = down;
		return down;
	}

	public Cell getUpleft() {
		return upleft;
	}

	public Cell setUpleft(Cell upleft) {
		this.upleft = upleft;
		return upleft;
	}

	public Cell getUpright() {
		return upright;
	}

	public Cell setUpright(Cell upright) {
		this.upright = upright;
		return upright;
	}

	public Cell getDownleft() {
		return downleft;
	}

	public Cell setDownleft(Cell downleft) {
		this.downleft = downleft;
		return downleft;
	}

	public Cell getDownright() {
		return downright;
	}

	public Cell setDownright(Cell downright) {
		this.downright = downright;
		return downright;
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.drawRect((x / cellSize) * cellSize, ((y / cellSize) * cellSize), cellSize, cellSize);
		if (isAlive()) {
			g2.fillRect((x / cellSize) * cellSize, ((y / cellSize) * cellSize), cellSize, cellSize);
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		this.color = c;
	}
}
