package gliGOL;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Grid grid;
	public static boolean running;
	public static Random rng;

	public Driver() {
		rng = new Random();
		grid = new Grid(475, 250, 4);
		running = false;
	}

	public static void main(String[] args) {
		Driver d = new Driver();
		d.setBackground(Color.black);

		JFrame f = new JFrame("gliGOL");
		f.add(d);
		f.addKeyListener(d);
		f.addMouseMotionListener(d.grid);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);

		while (true) {
			if (running) {
				d.grid.nextGridGen();
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			d.repaint();
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		grid.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_INSERT) {
			grid.spawn();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (running) {
				running = false;
			} else {
				running = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			grid.shuffle();
		}

		if (e.getKeyCode() == KeyEvent.VK_HOME) {
			grid.toggle();
		}
		if (e.getKeyCode() == KeyEvent.VK_DELETE) {
			grid.clear();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
