import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Timer timer;
    int speed = 6;
    Dino dino; // ✅ inside class
    Obstacle obstacle;
    boolean gameOver = false;
    public GamePanel() {
    setPreferredSize(new Dimension(800, 400));
    setBackground(Color.WHITE);

    dino = new Dino(100, 300);
    obstacle = new Obstacle(600, 300);

    timer = new Timer(20, this); // game loop
    timer.start();

    addKeyListener(this);
    setFocusable(true);
}
    private void drawSun(Graphics g) {
    g.setColor(Color.YELLOW); // ✅ bright yellow sun
    g.fillOval(650, 50, 60, 60);
}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSun(g);
        dino.draw(g);
        obstacle.draw(g);

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", 280, 150);

            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Press SPACE to Restart", 250, 200);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            dino.update();
            obstacle.update(speed);

            // 💥 collision check
            if (dino.getBounds().intersects(obstacle.getBounds())) {
                gameOver = true;
        }
        }

        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            if (gameOver) {
            resetGame(); // 🔁 restart
            } else {
                dino.jump(); // 🦖 jump
            }
        }
    }
    private void resetGame() {
        dino = new Dino(100, 300);
        obstacle = new Obstacle(600, 300);
        gameOver = false;
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}