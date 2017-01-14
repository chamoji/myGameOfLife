package gliGOL;

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
	private int MINSPEED;
	public static boolean running;
	public static Random rng;

	public Driver() {
		rng = new Random();
		grid = new Grid(200, 100, 8, 3, 2);
		running = false;
	}

	public static void main(String[] args) {
		Driver d = new Driver();

		JFrame f = new JFrame("gliGOL");
		f.add(d);
		f.addKeyListener(d);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);

		while (true) {
			if (running) {
				d.grid.nextGridGen();
				try {
					Thread.sleep(100);
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
		// TODO Auto-generated method stub

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
			grid = new Grid(200, 100, 8, 3, 2);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
