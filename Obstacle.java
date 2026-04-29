import java.awt.*;

public class Obstacle {
    
    int x, y;
    int width = 10;   // ✅ thinner cactus
    int height = 40;

    public Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(int speed) {
        x -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN); // ✅ green cactus
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}