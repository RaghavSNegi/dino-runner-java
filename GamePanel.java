import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {

    Dino dino; // ✅ inside class
    Obstacle obstacle;
    public GamePanel() {
        setPreferredSize(new Dimension(800, 400)); // ✅ correct syntax
        setBackground(Color.WHITE);

        dino = new Dino(100, 300);
        obstacle = new Obstacle(600, 300); // ✅ correct constructor
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
}