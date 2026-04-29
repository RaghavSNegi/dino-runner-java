import java.awt.*;
import java.util.Random;

public class Obstacle {

    int x_;
    int y_;
    int h_;
    int p_;
    int scale = 6;//base
    static int jump = 0; // shared movement offset (like original)

    Color colorCactus1 = new Color(34, 139, 34); // green
    Color colorCactus2 = new Color(0, 100, 0);   // darker green

    public Obstacle() {
        generate();
    }

    // generate cactus like original initCactusG
    public void generate() {
        Random r = new Random();
        int step = r.nextInt(10) + 1;

        x_ = 10 * 30 + step * 10 + 600;
        h_ = 8 + (4 * step);
        y_ = 300 - h_;
        p_ = 5 + step / 3;
    }

    // update movement (same as original jump logic)
    public void update(int score) {
        jump += (6 + score / 150);; // speed increases slowly
        if (jump > 1200) {
            jump = 0;
            generate();
        }
    }

    // draw cactus (original logic preserved)
    public void draw(Graphics g) {

        int x = x_;
        int y = y_;
        int h = h_;
        int p = p_;

        int maxH = 180;
        int i = p * 2 + 40;
        int j = p * 2 + 40;
        int y1 = y + 40;
        int y2 = y + 60;

        if (x + j - jump < 0) {
            jump = 0;
        }

        drawPart(g, x - i - jump, y1, h, p);
        drawPart(g, x - jump, y, maxH, p * 2);
        drawPart(g, x + j - jump, y2, h, p);

        drawDetails(g, x - jump, h, p, i, j, y1, y2);
    }

    // main cactus body
    private void drawPart(Graphics g, int x, int y, int h, int p) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(colorCactus1);
        g2.fillRect(x, y, p, h);

        g2.setPaint(colorCactus2);
        g2.fillRect(x + p, y, p, h);

        g2.fillArc(x, y - p, p * 2, p * 2, 1, 90);
        g2.setPaint(colorCactus1);
        g2.fillArc(x, y - p, p * 2, p * 2, 90, 90);

        g2.fillArc(x, y + h - p, p * 2, p * 2, 180, 90);
        g2.setPaint(colorCactus2);
        g2.fillArc(x, y + h - p, p * 2, p * 2, 270, 90);
    }
    public int getScreenX() {
    return x_ - jump;
}

    // cactus arms/details
    private void drawDetails(Graphics g, int x, int h, int p, int i, int j, int y1, int y2) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(colorCactus1);
        g2.fillRect(x - i + p, y1 + h, i, p);

        g2.setPaint(colorCactus2);
        g2.fillRect(x - i + 2 * p, y1 + h - p, i - 2 * p, p);

        g2.setPaint(colorCactus2);
        g2.fillRect(x + p * 2, y2 + h, j - p, p);

        g2.setPaint(colorCactus1);
        g2.fillRect(x + p * 4, y2 + h - p, j - 4 * p, p);
    }

    // collision check helper
    public boolean collidesWith(Rectangle dinoBounds) {

    int x = x_;
    int y = y_;
    int h = h_;
    int p = p_;

    int i = p * 2 + 40;
    int j = p * 2 + 40;

    int y1 = y + 40;
    int y2 = y + 60;

    // LEFT part
    Rectangle left = new Rectangle(
        x - i - jump,
        y1,
        p * 2,
        h
    );

    // CENTER part
    Rectangle center = new Rectangle(
        x - jump,
        y,
        p * 4,
        180
    );

    // RIGHT part
    Rectangle right = new Rectangle(
        x + j - jump,
        y2,
        p * 2,
        h
    );

    return dinoBounds.intersects(left)
        || dinoBounds.intersects(center)
        || dinoBounds.intersects(right);
}
}