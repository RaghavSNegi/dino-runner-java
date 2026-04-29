import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    Timer timer;
    int speed = 6;
    Dino dino; // ✅ inside class
    Obstacle obstacle;
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
        obstacle.draw(g); // ✅ draw dino
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        dino.update();
        obstacle.update(speed);

        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            dino.jump();
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}