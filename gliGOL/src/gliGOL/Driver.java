package gliGOL;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Driver extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Grid grid;

	public Driver() {
		grid = new Grid();
	}

	public static void main(String[] args) {
		Driver d = new Driver();
		d.setBackground(Color.white);

		JFrame f = new JFrame("gliGOL");
		f.add(d);
		f.setSize(800, 600);
		f.setVisible(true);
		f.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		grid.draw(g);
	}

}
