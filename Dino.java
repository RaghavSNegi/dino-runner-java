import java.awt.*;

public class Dino {

    int x, y;
    int width = 40, height = 40;

    public Dino(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        // For now, no movement
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // ✅ Blue dinosaur
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}