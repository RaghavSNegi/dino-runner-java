import java.awt.*;

public class Dino {

    int x, y;
    int width = 40, height = 40;

    double velocityY = 0;
    double gravity = 1;

    boolean onGround = true;

    public Dino(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if (!onGround) {
            velocityY += gravity;
            y += velocityY;

            if (y >= 300) { // ground level
                y = 300;
                onGround = true;
                velocityY = 0;
            }
        }
    }

    public void jump() {
        if (onGround) {
            velocityY = -15;
            onGround = false;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}