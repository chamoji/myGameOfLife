import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Driver extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    public GridGOL grid;
    public static boolean running;
    public static final String titleString = "Conway's Game of Life";
    private static final Color COLOR_GB_SCREEN = new Color(155,188,15);
    private static final Color COLOR_GB_SCREEN_DARKEST = new Color(15,56,15);

    public Driver() {
        grid = new GridGOL(80,72,4);
        // grid = new GridGOL(10,22,24);
        running = true;
    }

    public static void main(String[] args) {
        Driver d = new Driver();
        d.setBackground(COLOR_GB_SCREEN_DARKEST);

        JFrame f = new JFrame(titleString);
        f.add(d);
        f.addKeyListener(d);
        d.addMouseMotionListener(d.grid);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(320,310);
        f.setResizable(false);
        f.setVisible(true);

        final double UPS = 60.0;
        final double FPS = 60.0;
        // start of game loop
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0;
        double deltaF = 0;
        int frames = 0;
        int ticks = 0;
        long timer = System.currentTimeMillis();
        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                //getInput();
                if (running) {
                    d.grid.nextGridGen();
                }
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                d.repaint();
                //render();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    // end of game loop

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        grid.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            running = !running;
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            grid.shuffle();
        }
        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            grid.clear();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
