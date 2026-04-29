import java.awt.*;

public class Dino {

    static int unit = 6; //base
    Color colorDinosaur = Color.BLUE;

    int currentDinosaurX = 0;
    int currentDinosaurY = 0;

    int x; // base X position
    int y; // base Y (ground level reference)

    public Dino(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, int jumpY) {
        int xDinausor = x;
        int baseY = y - jumpY;

        int step = 1;

        g.setColor(colorDinosaur);

        currentDinosaurX = xDinausor;
        currentDinosaurY = baseY;

        drawRaw(g, xDinausor, baseY, 2, 1);
        drawRaw(g, xDinausor + 4 * unit, baseY, 2, 1);

        drawRaw(g, xDinausor, baseY - step * unit, 1, 1);
        drawRaw(g, xDinausor + 4 * unit, baseY - step * unit, 1, 1);

        step++;
        drawRaw(g, xDinausor, baseY - step * unit, 2, 1);
        drawRaw(g, xDinausor + 3 * unit, baseY - step * unit, 2, 1);

        step++;
        drawRaw(g, xDinausor, baseY - step * unit, 5, 1);

        step++;
        drawRaw(g, xDinausor - unit, baseY - step * unit, 6, 1);

        step++;
        drawRaw(g, xDinausor - 2 * unit, baseY - step * unit, 8, 1);

        step++;
        drawRaw(g, xDinausor - 3 * unit, baseY - step * unit, 10, 1);

        step++;
        drawRaw(g, xDinausor - 4 * unit, baseY - step * unit, 11, 1);
        drawRaw(g, xDinausor + (11 + 1 - 4) * unit, baseY - step * unit, 1, 1);

        step++;
        drawRaw(g, xDinausor - 4 * unit, baseY - step * unit, 3, 1);
        drawRaw(g, xDinausor + (5 - 4) * unit, baseY - step * unit, 8, 1);

        step++;
        drawRaw(g, xDinausor - 4 * unit, baseY - step * unit, 2, 1);
        drawRaw(g, xDinausor + (6 - 4) * unit, baseY - step * unit, 5, 1);

        step++;
        drawRaw(g, xDinausor - 4 * unit, baseY - step * unit, 1, 1);
        drawRaw(g, xDinausor + (7 - 4) * unit, baseY - step * unit, 4, 1);

        step++;
        drawRaw(g, xDinausor - 4 * unit, baseY - step * unit, 1, 1);
        drawRaw(g, xDinausor + (8 - 4) * unit, baseY - step * unit, 7, 1);

        step++;
        drawRaw(g, xDinausor + (8 - 4) * unit, baseY - step * unit, 4, 1);

        step++;
        drawRaw(g, xDinausor + (8 - 4) * unit, baseY - step * unit, 8, 1);

        step++;
        drawRaw(g, xDinausor + (8 - 4) * unit, baseY - step * unit, 2, 1);
        drawRaw(g, xDinausor + (11 - 4) * unit, baseY - step * unit, 5, 1);

        step++;
        drawRaw(g, xDinausor + (8 - 4) * unit, baseY - step * unit, 8, 1);

        step++;
        drawRaw(g, xDinausor + (9 - 4) * unit, baseY - step * unit, 6, 1);
    }

    private void drawRaw(Graphics g, int x, int y, int w, int h) {
        Graphics2D g2 = (Graphics2D) g;
        g2.fillRect(x, y, w * unit, h * unit);
    }

    // used for collision detection
    public Rectangle getBounds(int jumpY) {

    int width = 6 * unit;
    int height = 8 * unit;

    int topY = y - jumpY - height;

    return new Rectangle(
        x + 5,              // small inward offset
        topY + 5,
        width - 10,         // shrink box
        height - 10
    );
}
}